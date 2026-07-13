package sistema.formularios;

import javax.swing.*;
import java.awt.*;

public class FrmMenu extends javax.swing.JFrame {

    private JButton btnHuespedes;
    private JButton btnHabitaciones;
    private JButton btnReservas;
    private JLabel lblBienvenida;

    public FrmMenu() {
        this("Administración");
    }

    public FrmMenu(String rol) {
        setTitle("Sistema de Gestión Hotelera - Panel Administrativo");
        setSize(800, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));

        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(30, 41, 59));
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));

        lblBienvenida = new JLabel("SISTEMA DE GESTIÓN HOTELERA");
        lblBienvenida.setForeground(Color.WHITE);
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Panel Principal — Sesión: " + rol);
        lblSub.setForeground(new Color(148, 163, 184));
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelHeader.add(lblBienvenida);
        panelHeader.add(Box.createRigidArea(new Dimension(0, 4)));
        panelHeader.add(lblSub);

        JPanel panelMenu = new JPanel(new GridLayout(1, 3, 20, 0));
        panelMenu.setBackground(new Color(248, 250, 252)); 
        panelMenu.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        btnHuespedes = new JButton("Gestión de Huéspedes");
        configurarBotonMenu(btnHuespedes, Color.WHITE);

        btnHabitaciones = new JButton("Inventario de Habitaciones");
        configurarBotonMenu(btnHabitaciones, Color.WHITE);

        btnReservas = new JButton("Módulo de Reservas");
        configurarBotonMenu(btnReservas, Color.WHITE);
        btnReservas.setForeground(Color.BLACK); 

        panelMenu.add(btnHuespedes);
        panelMenu.add(btnHabitaciones);
        panelMenu.add(btnReservas);

        if (rol.equals("Personal")) {
            btnHabitaciones.setEnabled(false);
            btnHabitaciones.setBackground(new Color(241, 245, 249)); 
        } 

        JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelFooter.setBackground(new Color(248, 250, 252));
        JLabel lblFooter = new JLabel("Universidad Tecnológica del Perú - Proyecto Arquitectura de Software");
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblFooter.setForeground(new Color(148, 163, 184));
        panelFooter.add(lblFooter);

        add(panelHeader, BorderLayout.NORTH);
        add(panelMenu, BorderLayout.CENTER);
        add(panelFooter, BorderLayout.SOUTH);

        btnHuespedes.addActionListener(e -> {
            new FrmHuesped().setVisible(true);
        });

        btnHabitaciones.addActionListener(e -> {
            new FrmHabitacion().setVisible(true);
        });

        btnReservas.addActionListener(e -> {
            new FrmReserva().setVisible(true);
        });
    }

    private void configurarBotonMenu(JButton boton, Color colorFondo) {
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(colorFondo);
        if (colorFondo == Color.WHITE) {
            boton.setForeground(new Color(51, 65, 85));
        }
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(226, 232, 240), 1, true),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
    }

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FrmMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> {
            new FrmMenu("Administración").setVisible(true);
        });
    }
}