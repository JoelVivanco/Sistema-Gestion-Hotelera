package com.mycompany.sistemagestionhotelera.vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.sistemagestionhotelera.controlador.HuespedControlador;

public class FrmHuesped extends javax.swing.JFrame {

    // Componentes del Formulario
    private JTextField txtNombre;
    private JTextField txtDocumento;
    private JTextField txtTelefono;
    private JTextField txtEmail;
    private JComboBox<String> cboTipo;
    private JButton btnRegistrar;
    private JButton btnLimpiar;

    // Componentes de la Tabla
    private JTable tblHuespedes;
    private DefaultTableModel modeloTabla;

    public FrmHuesped() {
        // Configuración de la ventana estilo ERP
        setTitle("Módulo de Administración: Gestión de Huéspedes");
        setSize(800, 550);
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

        // Inicializar componentes con fuentes estándar del sistema
        Font fuenteLabel = new Font("Segoe UI", Font.PLAIN, 12);
        
        JLabel lblNombre = new JLabel("Nombre Completo:"); lblNombre.setFont(fuenteLabel);
        txtNombre = new JTextField();
        
        JLabel lblDoc = new JLabel("Doc. Identidad (DNI/PAS):"); lblDoc.setFont(fuenteLabel);
        txtDocumento = new JTextField();
        
        JLabel lblTel = new JLabel("Teléfono:"); lblTel.setFont(fuenteLabel);
        txtTelefono = new JTextField();
        
        JLabel lblEmail = new JLabel("Correo Electrónico:"); lblEmail.setFont(fuenteLabel);
        txtEmail = new JTextField();
        
        JLabel lblTipo = new JLabel("Tipo de Cliente:"); lblTipo.setFont(fuenteLabel);
        String[] tipos = {"Regular", "Frecuente", "Corporativo"};
        cboTipo = new JComboBox<>(tipos);
        cboTipo.setBackground(Color.WHITE);

        // Agregar los elementos al Grid del formulario
        panelFormulario.add(lblNombre);      panelFormulario.add(txtNombre);
        panelFormulario.add(lblDoc);         panelFormulario.add(txtDocumento);
        panelFormulario.add(lblTel);         panelFormulario.add(txtTelefono);
        panelFormulario.add(lblEmail);       panelFormulario.add(txtEmail);
        panelFormulario.add(lblTipo);        panelFormulario.add(cboTipo);
        // Espacios vacíos para cuadrar el Grid de 3x4
        panelFormulario.add(new JLabel("")); panelFormulario.add(new JLabel(""));

        // --- 2. PANEL CENTRAL: TABLA DE REGISTROS ---
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), 
                " Huéspedes Registrados en el Sistema ", 
                TitledBorder.LEFT, 
                TitledBorder.TOP, 
                new Font("Segoe UI", Font.BOLD, 12)
        ));

        // Configuración de la tabla nativa
        String[] columnas = {"ID (Auto)", "Nombre Completo", "Documento", "Teléfono", "Correo", "Tipo Cliente"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; } // Celdas no editables directamente
        };
        
        tblHuespedes = new JTable(modeloTabla);
        tblHuespedes.getTableHeader().setReorderingAllowed(false); // No mover columnas
        JScrollPane scrollTabla = new JScrollPane(tblHuespedes);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        // --- 3. PANEL INFERIOR: BOTONES DE ACCIÓN ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        
        btnLimpiar = new JButton("Limpiar Campos");
        btnLimpiar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        
        btnRegistrar = new JButton("Guardar Registro");
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRegistrar.setBackground(new Color(34, 139, 34)); // Verde ejecutivo sutil
        btnRegistrar.setForeground(Color.BLACK);

        panelBotones.add(btnLimpiar);
        panelBotones.add(btnRegistrar);

        // --- ENSAMBLE FINAL EN EL CONTENEDOR PRINCIPAL ---
        JPanel contenedorMargen = new JPanel(new BorderLayout(5, 5));
        contenedorMargen.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        contenedorMargen.add(panelFormulario, BorderLayout.NORTH);
        contenedorMargen.add(panelTabla, BorderLayout.CENTER);
        contenedorMargen.add(panelBotones, BorderLayout.SOUTH);
        
        add(contenedorMargen, BorderLayout.CENTER);

        // --- EVENTOS ---
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarRegistro();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });
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
            
            // Agregar visualmente el registro a la tabla en tiempo real sin recargar
            modeloTabla.addRow(new Object[]{"S/N", nombre, documento, telefono, email, tipo});
            
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "No se pudo completar el registro.\nAsegúrese de llenar los campos obligatorios y que el documento no exista.", "Error de Consistencia", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDocumento.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        cboTipo.setSelectedIndex(0);
        txtNombre.requestFocus();
    }

    public static void main(String args[]) {
        try {
            // Activa el Look and Feel nativo del sistema operativo (Windows/Mac) para eliminar apariencias toscas
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