package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    //ATRIBUTOS DE CONEXIÓN A LA BBDD
    //jdbc:mysql://dominio:puerto/basededatos
    private String URL  = "jdbc:mysql://brewh18lmobqktjfqcxk-mysql.services.clever-cloud.com:3306/brewh18lmobqktjfqcxk";
    //jdbc:mysql://localhost:3306/miBaseDatos
    private String USER = "ueqo6eebp3gv6vqo";
    private String PASSWORD = "Qv3MRPabriXCb4iZpq5S";

    //Método para conectarnos a la BBDD
    private Connection conectar() throws SQLException{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    };



    //Crear Insert
    public void InsertarUsuario(String nombre, String email){
        String sql = "INSERT INTO usuarios(nombre,email) VALUES(?,?)";
        try(
                Connection conexionInterna = conectar();
                PreparedStatement solicitud = conexionInterna.prepareStatement(sql)){
            //Asignar valores a las incógnitas
            solicitud.setString(1, nombre);
            solicitud.setString(2, email);

            //Ejecución de la solicitud
            solicitud.executeUpdate();
            System.out.println("Usuario ingresado de manera exitosa!");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    //Leer (SELECT)
    //select * from usuarios;
    public List<Usuario> obtenerUsuarios() {
        String sql = "SELECT * FROM usuarios";
        List<Usuario> listaUsuarios = new ArrayList<>();
        try (
                Connection conexionInterna = conectar();
                PreparedStatement solicitud = conexionInterna.prepareStatement(sql);
                ResultSet resultado = solicitud.executeQuery();) {
            while (resultado.next()) {
                int id = resultado.getInt("id");
                String nombre = resultado.getString("nombre");
                String email = resultado.getString("email");
                listaUsuarios.add(new Usuario(id, nombre, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaUsuarios;
    }

    //Actualizar (UPDATE)
    //update usuarios set nombre = "Pepito", email="pepito@campuslands.com" where id = 3;
    //upadte usuarios set nombre = ?, email = ?, where id = ?;

    public void actualizarUsuario(int id, String nombre, String email){
        String sql = "UPDATE usuarios SET nombre = ?, email = ? WHERE id = ?";
        try(
                Connection conexionInterna = conectar();
                PreparedStatement solicitud = conexionInterna.prepareStatement(sql)){
            //Asignar valores a las incógnitas
            solicitud.setString(1, nombre);
            solicitud.setString(2, email);
            solicitud.setInt(3, id);

            //Ejecución de la solicitud
            int filas = solicitud.executeUpdate();
            if(filas > 0){
                System.out.println("Usuario actualizado exitosamente!");
            }else{
                System.out.println("No se pudo actualizar el usuario con id: " + id);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    // Método para buscar un usuario por ID
    public String buscarUsuarioPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        String usuario = null;
        try (
                Connection conexionInterna = conectar();
                PreparedStatement solicitud = conexionInterna.prepareStatement(sql)) {
            solicitud.setInt(1, id);
            ResultSet resultado = solicitud.executeQuery();
            if (resultado.next()) {
                usuario = resultado.getInt("id") + " - " + resultado.getString("nombre") + " - " + resultado.getString("email");
            } else {
                System.out.println("No se encontró un usuario con id: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    // Método para eliminar un usuario por ID
    public void eliminarUsuario(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (
                Connection conexionInterna = conectar();
                PreparedStatement solicitud = conexionInterna.prepareStatement(sql)) {
            solicitud.setInt(1, id);
            int filas = solicitud.executeUpdate();
            if (filas > 0) {
                System.out.println("Usuario eliminado exitosamente!");
            } else {
                System.out.println("No se pudo eliminar el usuario con id: " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}