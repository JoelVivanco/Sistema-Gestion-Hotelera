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
    private int idSeleccionado = -1; // Almacena el ID de la fila clickeada

    public FrmHuesped() {
        // Configuración de la ventana estilo ERP
        setTitle("Módulo de Administración: Gestión de Huéspedes");
        setSize(850, 580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // --- 1. PANEL SUPERIOR: FORMULARIO DE ENTRADA ---
        JPanel panelFormulario = new JPanel(new GridLayout(3, 4, 15, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                " Datos del Huésped ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12)
        ));

        Font fuenteLabel = new Font("Segoe UI", Font.PLAIN, 12);

        JLabel lblNombre = new JLabel("Nombre Completo:");
        lblNombre.setFont(fuenteLabel);
        txtNombre = new JTextField();

        JLabel lblDoc = new JLabel("Doc. Identidad (DNI/PAS):");
        lblDoc.setFont(fuenteLabel);
        txtDocumento = new JTextField();

        JLabel lblTel = new JLabel("Teléfono:");
        lblTel.setFont(fuenteLabel);
        txtTelefono = new JTextField();

        JLabel lblEmail = new JLabel("Correo Electrónico:");
        lblEmail.setFont(fuenteLabel);
        txtEmail = new JTextField();

        JLabel lblTipo = new JLabel("Tipo de Cliente:");
        lblTipo.setFont(fuenteLabel);
        String[] tipos = {"Regular", "Frecuente", "Corporativo"};
        cboTipo = new JComboBox<>(tipos);
        cboTipo.setBackground(Color.WHITE);

        // Agregar los elementos al Grid del formulario
        panelFormulario.add(lblNombre);
        panelFormulario.add(txtNombre);
        panelFormulario.add(lblDoc);
        panelFormulario.add(txtDocumento);
        panelFormulario.add(lblTel);
        panelFormulario.add(txtTelefono);
        panelFormulario.add(lblEmail);
        panelFormulario.add(txtEmail);
        panelFormulario.add(lblTipo);
        panelFormulario.add(cboTipo);
        panelFormulario.add(new JLabel(""));
        panelFormulario.add(new JLabel(""));

        // --- 2. PANEL CENTRAL: TABLA DE REGISTROS ---
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                " Huéspedes Registrados en el Sistema ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12)
        ));

        String[] columnas = {"ID", "Nombre Completo", "Documento", "Teléfono", "Correo", "Tipo Cliente"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblHuespedes = new JTable(modeloTabla);
        tblHuespedes.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollTabla = new JScrollPane(tblHuespedes);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        // --- 3. PANEL INFERIOR: BOTONES DE ACCIÓN ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        btnLimpiar = new JButton("Limpiar Campos");
        btnLimpiar.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        // NUEVO: Botón Modificar
        btnModificar = new JButton("Modificar");
        btnModificar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnModificar.setBackground(new Color(218, 165, 32)); // Dorado ejecutivo
        btnModificar.setForeground(Color.BLACK);

        // NUEVO: Botón Eliminar
        btnEliminar = new JButton("Eliminar");
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnEliminar.setBackground(new Color(178, 34, 34)); // Rojo oscuro corporativo
        btnEliminar.setForeground(Color.BLACK);

        btnRegistrar = new JButton("Guardar Nuevo");
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRegistrar.setBackground(new Color(34, 139, 34));
        btnRegistrar.setForeground(Color.BLACK);

        panelBotones.add(btnLimpiar);
        panelBotones.add(btnModificar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRegistrar);

        // --- ENSAMBLE FINAL ---
        JPanel contenedorMargen = new JPanel(new BorderLayout(5, 5));
        contenedorMargen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        contenedorMargen.add(panelFormulario, BorderLayout.NORTH);
        contenedorMargen.add(panelTabla, BorderLayout.CENTER);
        contenedorMargen.add(panelBotones, BorderLayout.SOUTH);

        add(contenedorMargen, BorderLayout.CENTER);

        // --- MANEJO DE EVENTOS ---
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarRegistro();
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarModificacion();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarEliminacion();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        // NUEVO: Evento al hacer clic en una fila de la tabla
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

    // NUEVO: Método para ejecutar modificación
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

    // NUEVO: Método para ejecutar eliminación
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
        idSeleccionado = -1; // Resetea la selección
        txtNombre.requestFocus();
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FrmHuesped.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHuesped().setVisible(true);
            }
        });
    }
}
