package com.mycompany.sistemagestionhotelera.vista;

import javax.swing.*;
import java.awt.*;

public class FrmMenu extends javax.swing.JFrame {

    private JButton btnHuespedes;
    private JButton btnHabitaciones;
    private JButton btnReservas;
    private JLabel lblBienvenida;

    public FrmMenu() {
        // Configuracion del Menu Principal del Hotel
        setTitle("Sistema de Gestión Hotelera - Panel Administrativo");
        setSize(800, 420);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(0, 0));

        // --- 1. ENCABEZADO DE BIENVENIDA ---
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(30, 41, 59)); // Gris azulado oscuro moderno
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(25, 20, 25, 20));

        lblBienvenida = new JLabel("SISTEMA DE GESTIÓN HOTELERA");
        lblBienvenida.setForeground(Color.WHITE);
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Panel de Control Principal");
        lblSub.setForeground(new Color(148, 163, 184));
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelHeader.add(lblBienvenida);
        panelHeader.add(Box.createRigidArea(new Dimension(0, 4)));
        panelHeader.add(lblSub);

        // --- 2. PANEL CENTRAL: LOS 3 BOTONES DE CONTROL ---
        JPanel panelMenu = new JPanel(new GridLayout(1, 3, 20, 0));
        panelMenu.setBackground(new Color(248, 250, 252)); // Fondo claro sutil
        panelMenu.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Botones con etiquetas limpias y sin HTML de relleno artificial
        btnHuespedes = new JButton("Gestión de Huéspedes");
        configurarBotonMenu(btnHuespedes, Color.WHITE);

        btnHabitaciones = new JButton("Inventario de Habitaciones");
        configurarBotonMenu(btnHabitaciones, Color.WHITE);

        btnReservas = new JButton("Módulo de Reservas");
        configurarBotonMenu(btnReservas, new Color(37, 99, 235)); // Azul ejecutivo acento
        btnReservas.setForeground(Color.BLACK); // Texto blanco para contraste limpio

        panelMenu.add(btnHuespedes);
        panelMenu.add(btnHabitaciones);
        panelMenu.add(btnReservas);

        // --- 3. PIE DE PÁGINA ---
        JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelFooter.setBackground(new Color(248, 250, 252));
        JLabel lblFooter = new JLabel("Universidad Tecnológica del Perú - Proyecto Arquitectura de Software");
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblFooter.setForeground(new Color(148, 163, 184));
        panelFooter.add(lblFooter);

        add(panelHeader, BorderLayout.NORTH);
        add(panelMenu, BorderLayout.CENTER);
        add(panelFooter, BorderLayout.SOUTH);

        // --- ACCIONES DE LOS BOTONES ---
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
            new FrmMenu().setVisible(true);
        });
    }
}