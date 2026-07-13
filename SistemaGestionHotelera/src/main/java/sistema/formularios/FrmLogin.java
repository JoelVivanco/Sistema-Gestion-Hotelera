package sistema.formularios;

import javax.swing.*;
import java.awt.*;

public class FrmLogin extends javax.swing.JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JComboBox<String> cboRol;
    private JButton btnIngresar;

    public FrmLogin() {
        // configuracion de la pantalla de acceso
        setTitle("Acceso al Sistema Hotelero");
        setSize(380, 360);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(248, 250, 252));
        setLayout(new BorderLayout());

        // panel superior decorativo
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(30, 41, 59));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        JLabel lblTitulo = new JLabel("INICIAR SESIÓN");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        panelHeader.add(lblTitulo);

        // panel central con los campos
        JPanel panelCampos = new JPanel(new GridLayout(3, 2, 10, 20));
        panelCampos.setBackground(Color.WHITE);
        panelCampos.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1, true),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        Font fuenteLabel = new Font("Segoe UI", Font.CENTER_BASELINE, 12);
        Color colorTexto = new Color(71, 85, 105);

        JLabel lblUser = new JLabel("Usuario:"); lblUser.setFont(fuenteLabel); lblUser.setForeground(colorTexto);
        txtUsuario = new JTextField();

        JLabel lblPass = new JLabel("Contraseña:"); lblPass.setFont(fuenteLabel); lblPass.setForeground(colorTexto);
        txtPassword = new JPasswordField();

        JLabel lblRol = new JLabel("Rol de Acceso:"); lblRol.setFont(fuenteLabel); lblRol.setForeground(colorTexto);
        String[] roles = {"Personal", "Administración"};
        cboRol = new JComboBox<>(roles);
        cboRol.setBackground(Color.WHITE);

        panelCampos.add(lblUser);  panelCampos.add(txtUsuario);
        panelCampos.add(lblPass);  panelCampos.add(txtPassword);
        panelCampos.add(lblRol);   panelCampos.add(cboRol);

        // panel inferior para el boton
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelBoton.setBackground(new Color(248, 250, 252));
        panelBoton.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        btnIngresar = new JButton("Ingresar al Panel");
        btnIngresar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnIngresar.setBackground(new Color(30, 41, 59));
        btnIngresar.setForeground(Color.WHITE);
        btnIngresar.setFocusPainted(false);
        btnIngresar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIngresar.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

        panelBoton.add(btnIngresar);

        JPanel contenedor = new JPanel(new BorderLayout(15, 15));
        contenedor.setBackground(new Color(248, 250, 252));
        contenedor.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        contenedor.add(panelCampos, BorderLayout.CENTER);
        contenedor.add(panelBoton, BorderLayout.SOUTH);

        add(panelHeader, BorderLayout.NORTH);
        add(contenedor, BorderLayout.CENTER);

        // logica de validacion al dar clic
        btnIngresar.addActionListener(e -> validarAcceso());
    }

    private void validarAcceso() {
        String usuario = txtUsuario.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();
        String rolSeleccionado = cboRol.getSelectedItem().toString();

        if (rolSeleccionado.equals("Personal") && usuario.equals("personal") && password.equals("123")) {
            // Seteamos el rol en el Proxy para que restrinja accesos
            sistema.controlador.HuespedControladorProxy.setRolUsuarioActual("Personal");
            new FrmMenu("Personal").setVisible(true);
            this.dispose();
        } else if (rolSeleccionado.equals("Administración") && usuario.equals("admin") && password.equals("123")) {
            // Seteamos el rol en el Proxy
            sistema.controlador.HuespedControladorProxy.setRolUsuarioActual("Administracion");
            new FrmMenu("Administracion").setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Credenciales incorrectas para el rol seleccionado.", "Error de Acceso", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new FrmLogin().setVisible(true);
        });
    }
}