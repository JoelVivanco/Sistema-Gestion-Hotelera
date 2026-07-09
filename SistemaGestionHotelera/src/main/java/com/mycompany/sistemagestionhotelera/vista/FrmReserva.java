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

    private JTable tblReservas;
    private DefaultTableModel modeloTabla;
    private int idReservaSeleccionada = -1;

    public FrmReserva() {
        setTitle("Módulo de Operaciones: Registro de Reservas");
        setSize(900, 640);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(248, 250, 252));
        setLayout(new BorderLayout(15, 15));

        Font fuenteLabel = new Font("Segoe UI", Font.CENTER_BASELINE, 12);
        Color colorTextoLabel = new Color(71, 85, 105);

        // --- PANEL SUPERIOR: FORMULARIO ---
        JPanel panelFormulario = new JPanel(new GridLayout(4, 4, 15, 15));
        panelFormulario.setBackground(Color.WHITE);
        panelFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        JLabel lblHuesped = new JLabel("ID Huésped:");
        lblHuesped.setFont(fuenteLabel);
        lblHuesped.setForeground(colorTextoLabel);
        txtIdHuesped = new JTextField();

        JLabel lblCanal = new JLabel("Canal de Reserva:");
        lblCanal.setFont(fuenteLabel);
        lblCanal.setForeground(colorTextoLabel);
        String[] canales = {"Recepción", "Sitio Web", "Telefónica", "Agencia"};
        cboCanal = new JComboBox<>(canales);
        cboCanal.setBackground(Color.WHITE);

        JLabel lblHabitacion = new JLabel("Habitación Disponible:");
        lblHabitacion.setFont(fuenteLabel);
        lblHabitacion.setForeground(colorTextoLabel);
        cboHabitacion = new JComboBox<>();
        cboHabitacion.setBackground(Color.WHITE);

        JLabel lblTipo = new JLabel("Tipo de Habitación:");
        lblTipo.setFont(fuenteLabel);
        lblTipo.setForeground(colorTextoLabel);
        txtTipoHabitacion = new JTextField();
        txtTipoHabitacion.setEditable(false);
        txtTipoHabitacion.setBackground(new Color(241, 245, 249));
        txtTipoHabitacion.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JLabel lblIngreso = new JLabel("Fecha Ingreso (AAAA-MM-DD):");
        lblIngreso.setFont(fuenteLabel);
        lblIngreso.setForeground(colorTextoLabel);
        txtFechaIngreso = new JTextField();

        JLabel lblSalida = new JLabel("Fecha Salida (AAAA-MM-DD):");
        lblSalida.setFont(fuenteLabel);
        lblSalida.setForeground(colorTextoLabel);
        txtFechaSalida = new JTextField();

        JLabel lblCosto = new JLabel("Costo Total (S/.):");
        lblCosto.setFont(fuenteLabel);
        lblCosto.setForeground(colorTextoLabel);
        txtCostoTotal = new JTextField();

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

        // --- PANEL CENTRAL: TABLA ---
        JPanel panelTabla = new JPanel(new BorderLayout());
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel lblTablaTitulo = new JLabel("Historial de Reservas Procesadas");
        lblTablaTitulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTablaTitulo.setForeground(new Color(51, 65, 85));
        lblTablaTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        panelTabla.add(lblTablaTitulo, BorderLayout.NORTH);

        String[] columnas = {"ID Reserva", "ID Huésped", "ID Hab.", "F. Ingreso", "F. Salida", "Total", "Canal"};
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblReservas = new JTable(modeloTabla);
        tblReservas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblReservas.getTableHeader().setReorderingAllowed(false);
        tblReservas.setRowHeight(22);
        JScrollPane scrollTabla = new JScrollPane(tblReservas);
        panelTabla.add(scrollTabla, BorderLayout.CENTER);

        // --- PANEL INFERIOR: ACCIONES (Estilo Botones Flat) ---
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        panelBotones.setBackground(new Color(248, 250, 252));

        btnLimpiar = new JButton("Limpiar Campos");
        estiloBoton(btnLimpiar, new Color(226, 232, 240), new Color(71, 85, 105));

        btnCheckOut = new JButton("Procesar Check-Out");
        estiloBoton(btnCheckOut, new Color(239, 68, 68), Color.WHITE); // Coral plano moderno

        btnRegistrar = new JButton("Confirmar Reserva (Commit)");
        estiloBoton(btnRegistrar, new Color(30, 41, 59), Color.WHITE); // Azul oscuro con texto blanco nítido

        panelBotones.add(btnLimpiar);
        panelBotones.add(btnCheckOut);
        panelBotones.add(btnRegistrar);

        JPanel contenedorMargen = new JPanel(new BorderLayout(15, 15));
        contenedorMargen.setBackground(new Color(248, 250, 252));
        contenedorMargen.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        contenedorMargen.add(panelFormulario, BorderLayout.NORTH);
        contenedorMargen.add(panelTabla, BorderLayout.CENTER);
        contenedorMargen.add(panelBotones, BorderLayout.SOUTH);

        add(contenedorMargen, BorderLayout.CENTER);

        // --- MANEJO DE EVENTOS INTACTO ---
        cboHabitacion.addActionListener(e -> actualizarDetallesHabitacion());
        btnRegistrar.addActionListener(e -> ejecutarReserva());
        btnCheckOut.addActionListener(e -> ejecutarCheckOut());
        btnLimpiar.addActionListener(e -> limpiarCampos());
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

    private void estiloBoton(JButton btn, Color fondo, Color texto) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(fondo);
        btn.setForeground(texto);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 18, 10, 18));
    }

    // [Se mantienen los métodos actualizarDetallesHabitacion, cargarDatosTabla, cargarHabitacionesDisponibles, ejecutarReserva, ejecutarCheckOut, limpiarCampos, obtenerEmailHuespedDesdeDB y obtenerIdTipoDesdeDB exactamente igual como los tenías]
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
        com.mycompany.sistemagestionhotelera.controlador.HabitacionControlador ctrlHab = new com.mycompany.sistemagestionhotelera.controlador.HabitacionControlador();
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

            if (emailHuesped != null && emailHuesped.toLowerCase().contains("@intercorp.com")) {
                costoBase = costoBase * 0.90;
                JOptionPane.showMessageDialog(this,
                        "¡Convenio INTERCORP Detectado!\nEl correo electrónico (" + emailHuesped + ") califica para un 10% de descuento corporativo.",
                        "Beneficio Intercorp Aplicado", JOptionPane.INFORMATION_MESSAGE);
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
        }
        java.awt.EventQueue.invokeLater(() -> {
            new FrmReserva().setVisible(true);
        });
    }

    private String obtenerEmailHuespedDesdeDB(int idHuesped) {
        String email = "";
        String sql = "SELECT email FROM huespedes WHERE id_huesped = ?";
        java.sql.Connection con = com.mycompany.sistemagestionhotelera.database.ConexionDB.getInstancia().getConexion();
        try (java.sql.PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idHuesped);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    email = rs.getString("email");
                }
            }
        } catch (java.sql.SQLException ex) {
        }
        return email;
    }

    private int obtenerIdTipoDesdeDB(int idHabitacion) {
        int idTipo = 1;
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
        }
        return idTipo;
    }
}
