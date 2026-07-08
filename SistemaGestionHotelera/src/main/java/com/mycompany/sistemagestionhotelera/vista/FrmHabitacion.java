package com.mycompany.sistemagestionhotelera.vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
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
        setSize(450, 320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        Font fuenteLabel = new Font("Segoe UI", Font.PLAIN, 12);

        // panel del formulario con los campos base
        JPanel panelForm = new JPanel(new GridLayout(4, 2, 10, 15));
        panelForm.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(),
                " Datos de la Nueva Habitación ",
                TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 12)
        ));

        panelForm.add(new JLabel("ID Habitación (Ej: 105):")).setFont(fuenteLabel);
        txtIdHabitacion = new JTextField();
        panelForm.add(txtIdHabitacion);

        panelForm.add(new JLabel("Número de Cuarto:")).setFont(fuenteLabel);
        txtNumero = new JTextField();
        panelForm.add(txtNumero);

        panelForm.add(new JLabel("Tipo (Categoría):")).setFont(fuenteLabel);
        String[] tipos = {"1 - Simples", "2 - Dobles", "3 - Matrimoniales", "4 - Familiares", "5 - Suites"};
        cboTipo = new JComboBox<>(tipos);
        cboTipo.setBackground(Color.WHITE);
        panelForm.add(cboTipo);

        panelForm.add(new JLabel("Estado Inicial:")).setFont(fuenteLabel);
        String[] estados = {"Disponible", "Ocupada"};
        cboEstado = new JComboBox<>(estados);
        cboEstado.setBackground(Color.WHITE);
        panelForm.add(cboEstado);

        // panel de botones para guardar o salir
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        btnCerrar = new JButton("Cancelar");

        btnGuardar = new JButton("Guardar en BD");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnGuardar.setBackground(new Color(24, 43, 73));
        btnGuardar.setForeground(Color.BLACK);

        panelBotones.add(btnCerrar);
        panelBotones.add(btnGuardar);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        contenedor.add(panelForm, BorderLayout.CENTER);
        contenedor.add(panelBotones, BorderLayout.SOUTH);

        add(contenedor, BorderLayout.CENTER);

        // eventos de los botones
        btnCerrar.addActionListener(e -> dispose());

        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarHabitacion();
            }
        });
    }

    // metodo para guardar el cuarto jalando los datos de la pantalla
    private void guardarHabitacion() {
        try {
            int id = Integer.parseInt(txtIdHabitacion.getText().trim());
            String numero = txtNumero.getText().trim();

            // jalamos solo el primer caracter para el id del tipo
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
                txtIdHabitacion.setText("");
                txtNumero.setText("");
                cboTipo.setSelectedIndex(0);
                cboEstado.setSelectedIndex(0);
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
