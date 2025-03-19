package vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class UsuarioVista {

    private JFrame frame;
    private JTextField txtNombre;
    private JTextField txtEmail;
    private JTextArea txtAreaUsuarios;
    private JButton btnAgregar;
    private JButton btnListar;
    private JButton btnBuscar;
    private JButton btnEliminar;
    private JTextField txtId;

    public UsuarioVista() {
        frame = new JFrame("Sistema de Gestión de Usuarios");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new FlowLayout());

        // Campos de entrada
        txtNombre = new JTextField(20);
        txtEmail = new JTextField(20);
        txtId = new JTextField(10);

        // Botones
        btnAgregar = new JButton("Agregar Usuario");
        btnListar = new JButton("Listar Usuarios");
        btnBuscar = new JButton("Buscar Usuario");
        btnEliminar = new JButton("Eliminar Usuario");

        // Área de texto para mostrar usuarios
        txtAreaUsuarios = new JTextArea(10, 30);
        txtAreaUsuarios.setEditable(false);

        // Añadir componentes al marco
        frame.add(new JLabel("Nombre:"));
        frame.add(txtNombre);
        frame.add(new JLabel("Email:"));
        frame.add(txtEmail);
        frame.add(btnAgregar);
        frame.add(btnListar);
        frame.add(new JLabel("ID del usuario a buscar/eliminar:"));
        frame.add(txtId);
        frame.add(btnBuscar);
        frame.add(btnEliminar);
        frame.add(new JScrollPane(txtAreaUsuarios));

        frame.setVisible(true);
    }

    public String getNombre() {
        return txtNombre.getText();
    }

    public String getEmail() {
        return txtEmail.getText();
    }

    public String getId() {
        return txtId.getText();
    }

    public void setAreaUsuarios(String usuarios) {
        txtAreaUsuarios.setText(usuarios);
    }

    public void agregarListenerAgregarUsuario(ActionListener actionListener) {
        btnAgregar.addActionListener(actionListener);
    }

    public void agregarListenerListarUsuarios(ActionListener actionListener) {
        btnListar.addActionListener(actionListener);
    }

    public void agregarListenerBuscarUsuario(ActionListener actionListener) {
        btnBuscar.addActionListener(actionListener);
    }

    public void agregarListenerEliminarUsuario(ActionListener actionListener) {
        btnEliminar.addActionListener(actionListener);
    }
}