package controlador;

import modelo.UsuarioDAO;
import vista.UsuarioVista;
import modelo.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsuarioControlador {

    private UsuarioDAO usuarioDAO;
    private UsuarioVista usuarioVista;

    public UsuarioControlador(UsuarioDAO usuarioDAO, UsuarioVista usuarioVista) {
        this.usuarioDAO = usuarioDAO;
        this.usuarioVista = usuarioVista;

        // Añadir listeners a los botones
        this.usuarioVista.agregarListenerAgregarUsuario(new AgregarUsuarioListener());
        this.usuarioVista.agregarListenerListarUsuarios(new ListarUsuariosListener());
        this.usuarioVista.agregarListenerBuscarUsuario(new BuscarUsuarioListener());
        this.usuarioVista.agregarListenerEliminarUsuario(new EliminarUsuarioListener());
    }

    private class AgregarUsuarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nombre = usuarioVista.getNombre();
            String email = usuarioVista.getEmail();
            usuarioDAO.InsertarUsuario(nombre, email);
        }
    }

    private class ListarUsuariosListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            StringBuilder usuarios = new StringBuilder();
            for (Usuario usuario : usuarioDAO.obtenerUsuarios()) {
                usuarios.append(usuario.getId()).append(" - ")
                        .append(usuario.getNombre()).append(" - ")
                        .append(usuario.getEmail()).append("\n");
            }
            usuarioVista.setAreaUsuarios(usuarios.toString());
        }
    }

    private class BuscarUsuarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(usuarioVista.getId());
            String resultado = usuarioDAO.buscarUsuarioPorId(id);
            usuarioVista.setAreaUsuarios(resultado != null ? resultado : "No se encontró el usuario.");
        }
    }

    private class EliminarUsuarioListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(usuarioVista.getId());
            usuarioDAO.eliminarUsuario(id);
        }
    }
}