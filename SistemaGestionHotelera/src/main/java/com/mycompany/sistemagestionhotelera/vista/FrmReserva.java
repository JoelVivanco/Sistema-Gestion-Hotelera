package com.mycompany.sistemagestionhotelera.vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private JButton btnCheckOut;
    private JButton btnLimpiar;

    // Componentes de la Tabla
    private JTable tblReservas;
    private DefaultTableModel modeloTabla;
    
    // Control de Selección
    private int idReservaSeleccionada = -1;

    public FrmReserva() {
        // Configuración de la ventana estilo ERP
        setTitle("Modulo de Operaciones: Registro de Reservas (Transaccional)");
        setSize(850, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Font fuenteLabel = new Font("Segoe UI", Font.PLAIN, 12);

        // --- 1. PANEL SUPERIOR: FORMULARIO DE ENTRADA ---
        JPanel panelFormulario = new JPanel(new GridLayout(3, 4, 15, 12));
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), 
                " Nueva Reserva (Control de Transaccion Activo) ", 
                TitledBorder.LEFT, 
                TitledBorder.TOP, 
                new Font("Segoe UI", Font.BOLD, 12)
        ));

        JLabel lblHuesped = new JLabel("ID Huesped (Numerico):"); lblHuesped.setFont(fuenteLabel);
        txtIdHuesped = new JTextField();

        JLabel lblHabitacion = new JLabel("Habitaciones Disponibles:"); lblHabitacion.setFont(fuenteLabel);
        cboHabitacion = new JComboBox<>(); 
        cboHabitacion.setBackground(Color.WHITE);

        JLabel lblIngreso = new JLabel("Fecha Ingreso (AAAA-MM-DD):"); lblIngreso.setFont(fuenteLabel);
        txtFechaIngreso = new JTextField();

        JLabel lblSalida = new JLabel("Fecha Salida (AAAA-MM-DD):"); lblSalida.setFont(fuenteLabel);
        txtFechaSalida = new JTextField();

        JLabel lblCosto = new JLabel("Costo Total (S/.):"); lblCosto.setFont(fuenteLabel);
        txtCostoTotal = new JTextField();

        JLabel lblCanal = new JLabel("Canal de Reserva:"); lblCanal.setFont(fuenteLabel);
        String[] canales = {"Recepcion", "Sitio Web", "Telefonica", "Agencia"};
        cboCanal = new JComboBox<>(canales);
        cboCanal.setBackground(Color.WHITE);

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

        String[] columnas = {"ID Reserva", "ID Huesped", "ID Hab.", "F. Ingreso", "F. Salida", "Total", "Canal"};
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

        // NUEVO: Botón Check-Out
        btnCheckOut = new JButton("Procesar Check-Out / Facturar");
        btnCheckOut.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCheckOut.setBackground(new Color(205, 92, 92)); // Terracota suave
        btnCheckOut.setForeground(Color.BLACK);

        btnRegistrar = new JButton("Confirmar Reserva (Commit)");
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnRegistrar.setBackground(new Color(24, 43, 73)); 
        btnRegistrar.setForeground(Color.BLACK);

        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCheckOut);
        panelBotones.add(btnRegistrar);

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

        btnCheckOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ejecutarCheckOut();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            }
        });

        tblReservas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tblReservas.getSelectedRow();
                if (filaSeleccionada != -1) {
                    idReservaSeleccionada = Integer.parseInt(modeloTabla.getValueAt(filaSeleccionada, 0).toString());
                }
            }
        });

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
            if (cboHabitacion.getSelectedItem() == null) {
                JOptionPane.showMessageDialog(this, "No hay habitaciones disponibles.", "Alerta", JOptionPane.WARNING_MESSAGE);
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
                JOptionPane.showMessageDialog(this, "Transaccion Exitosa Confirmada en MySQL.", "Commit", JOptionPane.INFORMATION_MESSAGE);
                cargarDatosTabla();
                cargarHabitacionesDisponibles(); 
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error en la transacción. Rollback ejecutado.", "Rollback", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Verifique los datos numericos.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void ejecutarCheckOut() {
        if (idReservaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una reserva de la tabla para procesar el Check-Out.", "Atencioon", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int opc = JOptionPane.showConfirmDialog(this, "Proceder con el cierre de estadía y liberación de habitación?", "Check-Out de Huesped", JOptionPane.YES_NO_OPTION);
        if (opc == JOptionPane.YES_OPTION) {
            ReservaControlador controlador = new ReservaControlador();
            boolean exito = controlador.procesarCheckOut(idReservaSeleccionada);

            if (exito) {
                JOptionPane.showMessageDialog(this, "Check-Out Exitoso\nHabitación liberada y cuenta facturada correctamente.", "Ciclo Concluido", JOptionPane.INFORMATION_MESSAGE);
                cargarDatosTabla();
                cargarHabitacionesDisponibles(); // Actualiza el combo metiendo el cuarto liberado de nuevo
                idReservaSeleccionada = -1;
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo procesar el Check-Out.", "Error", JOptionPane.ERROR_MESSAGE);
            }
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
        idReservaSeleccionada = -1;
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