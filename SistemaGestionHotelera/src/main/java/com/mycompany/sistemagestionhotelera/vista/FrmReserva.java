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
    private JTextField txtTipoHabitacion;
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

    // Control de Seleccion
    private int idReservaSeleccionada = -1;

    public FrmReserva() {
        // Configuración de la ventana estilo ERP
        setTitle("Modulo de Operaciones: Registro de Reservas (Transaccional)");
        setSize(850, 620);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        Font fuenteLabel = new Font("Segoe UI", Font.PLAIN, 12);

        // PANEL SUPERIOR: FORMULARIO DE ENTRADA
        JPanel panelFormulario = new JPanel(new GridLayout(4, 4, 15, 12));
        panelFormulario.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                " Nueva Reserva (Control de Transaccion Activo) ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12)
        ));

        JLabel lblHuesped = new JLabel("ID Huesped (Numerico):");
        lblHuesped.setFont(fuenteLabel);
        txtIdHuesped = new JTextField();

        JLabel lblCanal = new JLabel("Canal de Reserva:");
        lblCanal.setFont(fuenteLabel);
        String[] canales = {"Recepcion", "Sitio Web", "Telefonica", "Agencia"};
        cboCanal = new JComboBox<>(canales);
        cboCanal.setBackground(Color.WHITE);

        JLabel lblHabitacion = new JLabel("Habitaciones Disponibles:");
        lblHabitacion.setFont(fuenteLabel);
        cboHabitacion = new JComboBox<>();
        cboHabitacion.setBackground(Color.WHITE);

        JLabel lblTipo = new JLabel("Tipo de Habitacion (Automático):");
        lblTipo.setFont(fuenteLabel);
        txtTipoHabitacion = new JTextField();
        txtTipoHabitacion.setEditable(false);
        txtTipoHabitacion.setBackground(new Color(240, 240, 240));
        txtTipoHabitacion.setFont(new Font("Segoe UI", Font.BOLD, 12));
        txtTipoHabitacion.setForeground(new Color(24, 43, 73));

        JLabel lblIngreso = new JLabel("Fecha Ingreso (AAAA-MM-DD):");
        lblIngreso.setFont(fuenteLabel);
        txtFechaIngreso = new JTextField();

        JLabel lblSalida = new JLabel("Fecha Salida (AAAA-MM-DD):");
        lblSalida.setFont(fuenteLabel);
        txtFechaSalida = new JTextField();

        JLabel lblCosto = new JLabel("Costo Total (S/.):");
        lblCosto.setFont(fuenteLabel);
        txtCostoTotal = new JTextField();

        // Agregar componentes guardando el orden del Grid
        panelFormulario.add(lblHuesped);
        panelFormulario.add(txtIdHuesped);
        panelFormulario.add(lblCanal);
        panelFormulario.add(cboCanal);

        panelFormulario.add(lblHabitacion);
        panelFormulario.add(cboHabitacion);
        panelFormulario.add(lblTipo);
        panelFormulario.add(txtTipoHabitacion);

        panelFormulario.add(lblIngreso);
        panelFormulario.add(txtFechaIngreso);
        panelFormulario.add(lblSalida);
        panelFormulario.add(txtFechaSalida);
        panelFormulario.add(lblCosto);
        panelFormulario.add(txtCostoTotal);

        //PANEL CENTRAL: TABLA DE CONTROL
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
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblReservas = new JTable(modeloTabla);
        tblReservas.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollTabla = new JScrollPane(tblReservas);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        //PANEL INFERIOR: ACCIONES
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));

        btnLimpiar = new JButton("Limpiar Formulario");
        btnLimpiar.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        btnCheckOut = new JButton("Procesar Check-Out / Facturar");
        btnCheckOut.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnCheckOut.setBackground(new Color(205, 92, 92));
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

        //MANEJO DE EVENTOS
        cboHabitacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarDetallesHabitacion();
            }
        });

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

    private void actualizarDetallesHabitacion() {
        if (cboHabitacion.getSelectedItem() == null) {
            txtTipoHabitacion.setText("");
            txtCostoTotal.setText("");
            return;
        }

        int idHab = (Integer) cboHabitacion.getSelectedItem();

        int idTipo = obtenerIdTipoDesdeDB(idHab);

        switch (idTipo) {
            case 1:
                txtTipoHabitacion.setText("Simples");
                txtCostoTotal.setText("90.00");
                break;
            case 2:
                txtTipoHabitacion.setText("Dobles");
                txtCostoTotal.setText("140.00");
                break;
            case 3:
                txtTipoHabitacion.setText("Matrimoniales");
                txtCostoTotal.setText("180.00");
                break;
            case 4:
                txtTipoHabitacion.setText("Familiares");
                txtCostoTotal.setText("250.00");
                break;
            case 5:
                txtTipoHabitacion.setText("Suites");
                txtCostoTotal.setText("300.00");
                break;
            default:
                txtTipoHabitacion.setText("Empresarial");
                txtCostoTotal.setText("200.00");
                break;
        }
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
        com.mycompany.sistemagestionhotelera.controlador.HabitacionControlador ctrlHab
                = new com.mycompany.sistemagestionhotelera.controlador.HabitacionControlador();

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

            String emailHuesped = obtenerEmailHuespedDesdeDB(idHuesped);

            double costoBase = Double.parseDouble(txtCostoTotal.getText().trim());

            // El sistema evalua de forma inteligente si el string contiene el dominio corporativo
            if (emailHuesped != null && emailHuesped.toLowerCase().contains("@intercorp.com")) {
                costoBase = costoBase * 0.90; // Aplicamos el 10% de descuento automático (Costo * 0.90)
                JOptionPane.showMessageDialog(this,
                        "¡Convenio INTERCORP Detectado!\nEl correo electrónico (" + emailHuesped + ") califica para un 10% de descuento corporativo.",
                        "Beneficio Intercorp Aplicado",
                        JOptionPane.INFORMATION_MESSAGE);
            }

            String canal = cboCanal.getSelectedItem().toString();

            ReservaControlador controlador = new ReservaControlador();
            boolean exito = controlador.registrarReserva(idHuesped, idHabitacion, ingreso, salida, costoBase, canal);

            if (exito) {
                JOptionPane.showMessageDialog(this, "¡Transacción Exitosa! Confirmada en MySQL (Commit).", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cargarDatosTabla();
                cargarHabitacionesDisponibles();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error en la transacción. Se ejecutó Rollback.", "Error Transaccional", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, verifique los campos numéricos.", "Error de Formato", JOptionPane.WARNING_MESSAGE);
        }

    }

    private void ejecutarCheckOut() {
        if (idReservaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una reserva de la tabla para procesar el Check-Out.", "Atención", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int opc = JOptionPane.showConfirmDialog(this, "¿Proceder con el cierre de estadía y liberación de habitación?", "Check-Out de Huésped", JOptionPane.YES_NO_OPTION);
        if (opc == JOptionPane.YES_OPTION) {
            ReservaControlador controlador = new ReservaControlador();
            boolean exito = controlador.procesarCheckOut(idReservaSeleccionada);

            if (exito) {
                JOptionPane.showMessageDialog(this, "¡Check-Out Exitoso!\nHabitación liberada y cuenta facturada correctamente.", "Ciclo Concluido", JOptionPane.INFORMATION_MESSAGE);
                cargarDatosTabla();
                cargarHabitacionesDisponibles();
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
        txtTipoHabitacion.setText("");
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

    private String obtenerEmailHuespedDesdeDB(int idHuesped) {
        String email = "";
        String sql = "SELECT email FROM huespedes WHERE id_huesped = ?";

        // Obtenemos la conexión Singleton compartida
        java.sql.Connection con = com.mycompany.sistemagestionhotelera.database.ConexionDB.getInstancia().getConexion();

        try (java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHuesped);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    email = rs.getString("email");
                }
            }
        } catch (java.sql.SQLException ex) {
            System.out.println("Error al verificar el correo del huésped: " + ex.getMessage());
        }
        return email;
    }

    private int obtenerIdTipoDesdeDB(int idHabitacion) {
        int idTipo = 1; // Por defecto Simple si ocurre un fallo
        String sql = "SELECT id_tipo FROM habitaciones WHERE id_habitacion = ?";
        java.sql.Connection con = com.mycompany.sistemagestionhotelera.database.ConexionDB.getInstancia().getConexion();

        try (java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHabitacion);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idTipo = rs.getInt("id_tipo");
                }
            }
        } catch (java.sql.SQLException ex) {
            System.out.println("Error al obtener el tipo de habitación: " + ex.getMessage());
        }
        return idTipo;
    }
}
