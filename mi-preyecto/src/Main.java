import modelo.UsuarioDAO;
import controlador.UsuarioControlador;
import vista.UsuarioVista;

public class Main {
    public static void main(String[] args) {
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        UsuarioVista usuarioVista = new UsuarioVista();
        new UsuarioControlador(usuarioDAO, usuarioVista);
    }
}