package com.mycompany.sistemagestionhotelera.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.mycompany.sistemagestionhotelera.controlador.HabitacionControlador;

public class FrmHabitacion extends javax.swing.JFrame {

    private JTextField txtIdHabitacion;
    private JTextField txtNumero;
    private JComboBox<String> cboTipo;
    private JComboBox<String> cboEstado;
    private JButton btnGuardar;
    private JButton btnCerrar;

    public FrmHabitacion() {
        setTitle("Mantenimiento de Inventario: Crear Habitaciones");
        setSize(460, 340);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(248, 250, 252));
        setLayout(new BorderLayout(0, 0));

        Font fuenteLabel = new Font("Segoe UI", Font.CENTER_BASELINE, 12);
        Color colorTextoLabel = new Color(71, 85, 105);

        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 20));
        panelForm.setBackground(Color.WHITE);
        panelForm.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1, true),
                BorderFactory.createEmptyBorder(25, 25, 25, 25)
        ));

        JLabel lblId = new JLabel("ID Habitación:"); lblId.setFont(fuenteLabel); lblId.setForeground(colorTextoLabel);
        txtIdHabitacion = new JTextField();
        
        JLabel lblNum = new JLabel("Número de Cuarto:"); lblNum.setFont(fuenteLabel); lblNum.setForeground(colorTextoLabel);
        txtNumero = new JTextField();

        JLabel lblTipo = new JLabel("Tipo (Categoría):"); lblTipo.setFont(fuenteLabel); lblTipo.setForeground(colorTextoLabel);
        String[] tipos = {"1 - Simples", "2 - Dobles", "3 - Matrimoniales", "4 - Familiares", "5 - Suites"};
        cboTipo = new JComboBox<>(tipos);
        cboTipo.setBackground(Color.WHITE);

        JLabel lblEst = new JLabel("Estado Inicial:"); lblEst.setFont(fuenteLabel); lblEst.setForeground(colorTextoLabel);
        String[] estados = {"Disponible", "Ocupada"};
        cboEstado = new JComboBox<>(estados);
        cboEstado.setBackground(Color.WHITE);

        panelForm.add(lblId);   panelForm.add(txtIdHabitacion);
        panelForm.add(lblNum);  panelForm.add(txtNumero);
        panelForm.add(lblTipo); panelForm.add(cboTipo);
        panelForm.add(lblEst);  panelForm.add(cboEstado);

        // panel de botones inferior
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 12, 0));
        panelBotones.setBackground(new Color(248, 250, 252));

        btnCerrar = new JButton("Cancelar");
        estiloBoton(btnCerrar, new Color(226, 232, 240), new Color(71, 85, 105));

        btnGuardar = new JButton("Guardar en BD");
        estiloBoton(btnGuardar, new Color(30, 41, 59), Color.WHITE);

        panelBotones.add(btnCerrar);
        panelBotones.add(btnGuardar);

        JPanel contenedor = new JPanel(new BorderLayout(15, 15));
        contenedor.setBackground(new Color(248, 250, 252));
        contenedor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        contenedor.add(panelForm, BorderLayout.CENTER);
        contenedor.add(panelBotones, BorderLayout.SOUTH);

        add(contenedor, BorderLayout.CENTER);

        // Eventos intactos
        btnCerrar.addActionListener(e -> dispose());
        btnGuardar.addActionListener(e -> guardarHabitacion());
    }

    private void estiloBoton(JButton btn, Color fondo, Color texto) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(fondo);
        btn.setForeground(texto);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(9, 16, 9, 16));
    }

    private void guardarHabitacion() {
        try {
            int id = Integer.parseInt(txtIdHabitacion.getText().trim());
            String numero = txtNumero.getText().trim();
            String seleccionTipo = cboTipo.getSelectedItem().toString();
            int idTipo = Integer.parseInt(seleccionTipo.split(" - ")[0]);
            String estado = cboEstado.getSelectedItem().toString();

            if (numero.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El número de habitación no puede estar vacío.", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }

            HabitacionControlador controlador = new HabitacionControlador();
            boolean exito = controlador.guardarNuevaHabitacion(id, numero, idTipo, estado);

            if (exito) {
                JOptionPane.showMessageDialog(this, "¡Habitación " + numero + " creada con éxito!", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                txtIdHabitacion.setText(""); txtNumero.setText(""); cboTipo.setSelectedIndex(0); cboEstado.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo registrar. Verifique si el ID ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "El ID de la habitación debe ser un número válido.", "Error de Formato", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new FrmHabitacion().setVisible(true);
        });
    }
}