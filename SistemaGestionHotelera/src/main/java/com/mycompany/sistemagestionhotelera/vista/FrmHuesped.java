package com.mycompany.sistemagestionhotelera.vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.mycompany.sistemagestionhotelera.controlador.HuespedControlador;

public class FrmHuesped extends javax.swing.JFrame {

    // Componentes del Formulario
    private JTextField txtNombre;
    private JTextField txtDocumento;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JComboBox<String> cboTipo;
    private JButton btnRegistrar;
    private JButton btnModificar;
    private JButton btnEliminar;
    private JButton btnLimpiar;

    // Componentes de la Tabla
    private JTable tblHuespedes;
    private DefaultTableModel modeloTabla;

    // Variable de Control Interno
    private int idSeleccionado = -1;

    public FrmHuesped() {
        // Configuracion de la ventana estilo ERP Moderno
        setTitle("Módulo de Administración: Gestión de Huéspedes");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(248, 250, 252));
        setLayout(new BorderLayout(15, 15));

        Font fuenteLabel = new Font("Segoe UI", Font.CENTER_BASELINE, 12);
        Color colorTextoLabel = new Color(71, 85, 105);

        // --- 1. PANEL SUPERIOR: FORMULARIO DE ENTRADA ---
        JPanel panelFormulario = new JPanel(new GridLayout(3, 4, 15, 15));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblNombre = new JLabel("Nombre Completo:");
        lblNombre.setFont(fuenteLabel); lblNombre.setForeground(colorTextoLabel);
        txtNombre = new JTextField();

        JLabel lblDoc = new JLabel("Doc. Identidad (DNI/PAS):");
        lblDoc.setFont(fuenteLabel); lblDoc.setForeground(colorTextoLabel);
        txtDocumento = new JTextField();

        JLabel lblTel = new JLabel("Teléfono:");
        lblTel.setFont(fuenteLabel); lblTel.setForeground(colorTextoLabel);
        txtTelefono = new JTextField();

        JLabel lblEmail = new JLabel("Correo Electrónico:");
        lblEmail.setFont(fuenteLabel); lblEmail.setForeground(colorTextoLabel);
        txtEmail = new JTextField();

        JLabel lblTipo = new JLabel("Tipo de Cliente:");
        lblTipo.setFont(fuenteLabel); lblTipo.setForeground(colorTextoLabel);
        String[] tipos = {"Regular", "Frecuente", "Corporativo"};
        cboTipo = new JComboBox<>(tipos);
        cboTipo.setBackground(Color.WHITE);

        // Agregar los elementos al Grid del formulario
        panelFormulario.add(lblNombre);   panelFormulario.add(txtNombre);
        panelFormulario.add(lblDoc);      panelFormulario.add(txtDocumento);
        panelFormulario.add(lblTel);      panelFormulario.add(txtTelefono);
        panelFormulario.add(lblEmail);    panelFormulario.add(txtEmail);
        panelFormulario.add(lblTipo);     panelFormulario.add(cboTipo);
        panelFormulario.add(new JLabel(""));
        panelFormulario.add(new JLabel(""));

        // --- 2. PANEL CENTRAL: TABLA DE REGISTROS ---
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblTablaTitulo = new JLabel("Huéspedes Registrados en el Sistema");
        lblTablaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTablaTitulo.setForeground(new Color(51, 65, 85));
        lblTablaTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panelTabla.add(lblTablaTitulo, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombre Completo", "Documento", "Teléfono", "Correo", "Tipo Cliente"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tblHuespedes = new JTable(modeloTabla);
        tblHuespedes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblHuespedes.getTableHeader().setReorderingAllowed(false);
        tblHuespedes.setRowHeight(22);
        JScrollPane scrollTabla = new JScrollPane(tblHuespedes);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        // --- 3. PANEL INFERIOR: BOTONES DE ACCIÓN (Estilo Flat UI) ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        panelBotones.setBackground(new Color(248, 250, 252));

        btnLimpiar = new JButton("Limpiar Campos");
        estiloBoton(btnLimpiar, new Color(226, 232, 240), new Color(71, 85, 105));

        btnModificar = new JButton("Modificar");
        estiloBoton(btnModificar, new Color(71, 85, 105), Color.WHITE); 

        btnEliminar = new JButton("Eliminar");
        estiloBoton(btnEliminar, new Color(239, 68, 68), Color.WHITE); 

        btnRegistrar = new JButton("Guardar Nuevo");
        estiloBoton(btnRegistrar, new Color(30, 41, 59), Color.WHITE); 

        panelBotones.add(btnLimpiar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRegistrar);

        // ENSAMBLE FINAL
        JPanel contenedorMargen = new JPanel(new BorderLayout(15, 15));
        contenedorMargen.setBackground(new Color(248, 250, 252));
        contenedorMargen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        contenedorMargen.add(panelFormulario, BorderLayout.NORTH);
        contenedorMargen.add(panelTabla, BorderLayout.CENTER);
        contenedorMargen.add(panelBotones, BorderLayout.SOUTH);

        add(contenedorMargen, BorderLayout.CENTER);

        // MANEJO DE EVENTOS INTACTO
        btnRegistrar.addActionListener(e -> ejecutarRegistro());
        btnModificar.addActionListener(e -> ejecutarModificacion());
        btnEliminar.addActionListener(e -> ejecutarEliminacion());
        btnLimpiar.addActionListener(e -> limpiarCampos());

        tblHuespedes.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tblHuespedes.getSelectedRow();
                if (filaSeleccionada != -1) {
                    idSeleccionado = Integer.parseInt(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
                    txtNombre.setText(modeloTabla.getValueAt(filaSeleccionada, 1).toString());
                    txtDocumento.setText(modeloTabla.getValueAt(filaSeleccionada, 2).toString());
                    txtTelefono.setText(modeloTabla.getValueAt(filaSeleccionada, 3).toString());
                    txtEmail.setText(modeloTabla.getValueAt(filaSeleccionada, 4).toString());
                    cboTipo.setSelectedItem(modeloTabla.getValueAt(filaSeleccionada, 5).toString());
                }
            }
        });

        cargarDatosTabla();
    }

    private void estiloBoton(JButton btn, Color fondo, Color texto) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(fondo);
        btn.setForeground(texto);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
    }

    private void cargarDatosTabla() {
        modeloTabla.setRowCount(0);
        HuespedControlador controlador = new HuespedControlador();
        java.util.List<Object[]> filas = controlador.obtenerListaHuespedes();
        for (Object[] fila : filas) {
            modeloTabla.addRow(fila);
        }
    }

    private void ejecutarRegistro() {
        String nombre = txtNombre.getText().trim();
        String documento = txtDocumento.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String email = txtEmail.getText().trim();
        String tipo = cboTipo.getSelectedItem().toString();

        HuespedControlador controlador = new HuespedControlador();
        boolean exito = controlador.registrarHuesped(nombre, documento, telefono, email, tipo);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Registro almacenado con éxito en la base de datos.", "Operación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            cargarDatosTabla();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo completar el registro.\nAsegúrese de llenar los campos obligatorios.", "Error de Consistencia", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ejecutarModificacion() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un huésped de la tabla para modificar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String nombre = txtNombre.getText().trim();
        String documento = txtDocumento.getText().trim();
        String telefono = txtTelefono.getText().trim();
        String email = txtEmail.getText().trim();
        String tipo = cboTipo.getSelectedItem().toString();

        HuespedControlador controlador = new HuespedControlador();
        boolean exito = controlador.modificarHuesped(idSeleccionado, nombre, documento, telefono, email, tipo);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Datos actualizados correctamente en MySQL.", "Modificación Exitosa", JOptionPane.INFORMATION_MESSAGE);
            cargarDatosTabla();
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void ejecutarEliminacion() {
        if (idSeleccionado == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un huésped de la tabla para eliminar.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }
        int respuesta = JOptionPane.showConfirmDialog(this, "¿Está seguro de eliminar permanentemente este huésped?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (respuesta == JOptionPane.YES_OPTION) {
            HuespedControlador controlador = new HuespedControlador();
            boolean exito = controlador.eliminarHuesped(idSeleccionado);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Registro removido de la base de datos.", "Eliminación Exitosa", JOptionPane.INFORMATION_MESSAGE);
                cargarDatosTabla();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el registro (puede tener reservas activas asociadas).", "Restricción de Integridad", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDocumento.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        cboTipo.setSelectedIndex(0);
        idSeleccionado = -1;
        txtNombre.requestFocus();
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FrmHuesped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new FrmHuesped().setVisible(true);
        });
    }
}