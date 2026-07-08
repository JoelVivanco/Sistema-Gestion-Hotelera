package com.mycompany.sistemagestionhotelera.vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.sistemagestionhotelera.controlador.ReservaControlador;

public class FrmReserva extends javax.swing.JFrame {

    // Componentes del Formulario
    private JTextField txtIdHuesped;
    private JComboBox<Integer> cboHabitacion;
    private JTextField txtFechaIngreso;
    private JTextField txtFechaSalida;
    private JTextField txtCostoTotal;
    private JComboBox<String> cboCanal;
    private JButton btnRegistrar;
    private JButton btnLimpiar;

    // Componentes de la Tabla
    private JTable tblReservas;
    private DefaultTableModel modeloTabla;

    public FrmReserva() {
        // Configuración de la ventana estilo ERP
        setTitle("Módulo de Operaciones: Registro de Reservas (Transaccional)");
        setSize(850, 580);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Font fuenteLabel = new Font("Segoe UI", Font.PLAIN, 12);

        // --- 1. PANEL SUPERIOR: FORMULARIO DE ENTRADA ---
        JPanel panelFormulario = new JPanel(new GridLayout(3, 4, 15, 12));
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), 
                " Nueva Reserva (Control de Transacción Activo) ", 
                TitledBorder.LEFT, 
                TitledBorder.TOP, 
                new Font("Segoe UI", Font.BOLD, 12)
        ));

        JLabel lblHuesped = new JLabel("ID Huésped (Numérico):"); lblHuesped.setFont(fuenteLabel);
        txtIdHuesped = new JTextField();

        JLabel lblHabitacion = new JLabel("Habitaciones Disponibles:"); lblHabitacion.setFont(fuenteLabel);
        cboHabitacion = new JComboBox<>(); // MODIFICADO: Inicialización del combo
        cboHabitacion.setBackground(Color.WHITE);

        JLabel lblIngreso = new JLabel("Fecha Ingreso (AAAA-MM-DD):"); lblIngreso.setFont(fuenteLabel);
        txtFechaIngreso = new JTextField();

        JLabel lblSalida = new JLabel("Fecha Salida (AAAA-MM-DD):"); lblSalida.setFont(fuenteLabel);
        txtFechaSalida = new JTextField();

        JLabel lblCosto = new JLabel("Costo Total (S/.):"); lblCosto.setFont(fuenteLabel);
        txtCostoTotal = new JTextField();

        JLabel lblCanal = new JLabel("Canal de Reserva:"); lblCanal.setFont(fuenteLabel);
        String[] canales = {"Recepción", "Sitio Web", "Telefónica", "Agencia"};
        cboCanal = new JComboBox<>(canales);
        cboCanal.setBackground(Color.WHITE);

        // Colocar componentes en el Grid
        panelFormulario.add(lblHuesped);    panelFormulario.add(txtIdHuesped);
        panelFormulario.add(lblHabitacion); panelFormulario.add(cboHabitacion);
        panelFormulario.add(lblIngreso);    panelFormulario.add(txtFechaIngreso);
        panelFormulario.add(lblSalida);     panelFormulario.add(txtFechaSalida);
        panelFormulario.add(lblCosto);      panelFormulario.add(txtCostoTotal);
        panelFormulario.add(lblCanal);      panelFormulario.add(cboCanal);

        // --- 2. PANEL CENTRAL: TABLA DE CONTROL ---
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), 
                " Auditoría de Reservas Procesadas ", 
                TitledBorder.LEFT, 
                TitledBorder.TOP, 
                new Font("Segoe UI", Font.BOLD, 12)
        ));

        String[] columnas = {"ID Reserva", "ID Huésped", "ID Hab.", "F. Ingreso", "F. Salida", "Total", "Canal"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        tblReservas = new JTable(modeloTabla);
        tblReservas.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollTabla = new JScrollPane(tblReservas);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        // --- 3. PANEL INFERIOR: ACCIONES ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        btnLimpiar = new JButton("Limpiar Formulario");
        btnLimpiar.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        btnRegistrar = new JButton("Confirmar Reserva (Commit)");
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRegistrar.setBackground(new Color(24, 43, 73)); 
        btnRegistrar.setForeground(Color.BLACK);

        panelBotones.add(btnLimpiar);
        panelBotones.add(btnRegistrar);

        // Ensamble final
        JPanel contenedorMargen = new JPanel(new BorderLayout(5, 5));
        contenedorMargen.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        contenedorMargen.add(panelFormulario, BorderLayout.NORTH);
        contenedorMargen.add(panelTabla, BorderLayout.CENTER);
        contenedorMargen.add(panelBotones, BorderLayout.SOUTH);

        add(contenedorMargen, BorderLayout.CENTER);

        // --- MANEJO DE EVENTOS ---
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarReserva();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        // Cargas automáticas iniciales de la BD
        cargarDatosTabla();
        cargarHabitacionesDisponibles();
    }

    private void cargarDatosTabla() {
        modeloTabla.setRowCount(0);
        ReservaControlador controlador = new ReservaControlador();
        java.util.List<Object[]> filas = controlador.obtenerListaReservas();
        for (Object[] fila : filas) {
            modeloTabla.addRow(fila);
        }
    }

    // AGREGADO: Consulta dinámicamente los cuartos libres en MySQL
    private void cargarHabitacionesDisponibles() {
        cboHabitacion.removeAllItems();
        com.mycompany.sistemagestionhotelera.controlador.HabitacionControlador ctrlHab = 
            new com.mycompany.sistemagestionhotelera.controlador.HabitacionControlador();
        
        java.util.List<Integer> disponibles = ctrlHab.obtenerHabitacionesDisponibles();
        for (Integer id : disponibles) {
            cboHabitacion.addItem(id);
        }
    }

    private void ejecutarReserva() {
        try {
            // Protección: Validar que existan cuartos libres seleccionados
            if (cboHabitacion.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, 
                        "No se puede procesar la reserva porque no hay habitaciones disponibles en este momento.", 
                        "Alerta de Ocupación", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int idHuesped = Integer.parseInt(txtIdHuesped.getText().trim());
            int idHabitacion = (Integer) cboHabitacion.getSelectedItem(); 
            String ingreso = txtFechaIngreso.getText().trim();
            String salida = txtFechaSalida.getText().trim();
            double costo = Double.parseDouble(txtCostoTotal.getText().trim());
            String canal = cboCanal.getSelectedItem().toString();

            ReservaControlador controlador = new ReservaControlador();
            boolean exito = controlador.registrarReserva(idHuesped, idHabitacion, ingreso, salida, costo, canal);

            if (exito) {
                JOptionPane.showMessageDialog(this, 
                        "¡Transacción Exitosa!\nLa reserva se guardó y la habitación pasó a 'Ocupada' (Commit aplicado).", 
                        "Commit Confirmado", JOptionPane.INFORMATION_MESSAGE);

                // Refresca los componentes en tiempo real con la BD
                cargarDatosTabla();
                cargarHabitacionesDisponibles();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, 
                        "Error en la transacción.\nSe ha ejecutado un ROLLBACK automático para proteger los datos.", 
                        "Rollback Ejecutado", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                    "Por favor, verifique los datos ingresados.\nEl campo ID Huésped y Costo deben ser numéricos.", 
                    "Error de Validación", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtIdHuesped.setText("");
        txtFechaIngreso.setText("");
        txtFechaSalida.setText("");
        txtCostoTotal.setText("");
        cboCanal.setSelectedIndex(0);
        if (cboHabitacion.getItemCount() > 0) {
            cboHabitacion.setSelectedIndex(0);
        }
        txtIdHuesped.requestFocus();
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FrmReserva.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmReserva().setVisible(true);
            }
        });
    }
}