package com.mycompany.sistemagestionhotelera.vista;

import javax.swing.*;
import java.awt.*;

public class FrmMenu extends javax.swing.JFrame {

    private JButton btnHuespedes;
    private JButton btnHabitaciones; // LA NUEVA OPCIÓN INTEGRADA
    private JButton btnReservas;
    private JLabel lblBienvenida;

    public FrmMenu() {
        // Configuración del Menú Principal del Hotel
        setTitle("Sistema de Gestión Hotelera - Panel Administrativo");
        setSize(750, 480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(20, 20));

        // --- 1. ENCABEZADO DE BIENVENIDA ---
        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(new Color(24, 43, 73)); // Azul oscuro empresarial
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.Y_AXIS));
        panelHeader.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        lblBienvenida = new JLabel("SISTEMA DE GESTIÓN HOTELERA");
        lblBienvenida.setForeground(Color.WHITE);
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblSub = new JLabel("Panel de Control Principal - Modo Enterprise");
        lblSub.setForeground(new Color(200, 214, 229));
        lblSub.setFont(new Font("Segoe UI", Font.ITALIC, 13));
        lblSub.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelHeader.add(lblBienvenida);
        panelHeader.add(Box.createRigidArea(new Dimension(0, 5)));
        panelHeader.add(lblSub);

        // --- 2. PANEL CENTRAL: LOS 3 BOTONES DE CONTROL ---
        JPanel panelMenu = new JPanel(new GridLayout(1, 3, 20, 0));
        panelMenu.setBorder(BorderFactory.createEmptyBorder(30, 40, 40, 40));

        // Botón 1: Huéspedes
        btnHuespedes = new JButton("<html><center><b>1. GESTIÓN DE<br>HUÉSPEDES</b><br><br><font size='3' color='gray'>CRUD Clientes y<br>Patrón Factory</font></center></html>");
        configurarBotonMenu(btnHuespedes, new Color(240, 244, 248));

        // Botón 2: Habitaciones (EL NUEVO APARTADO QUE SOLICITASTE)
        btnHabitaciones = new JButton("<html><center><b>2. INVENTARIO DE<br>HABITACIONES</b><br><br><font size='3' color='gray'>Mantenimiento de<br>Cuartos y Categorías</font></center></html>");
        configurarBotonMenu(btnHabitaciones, new Color(240, 244, 248));

        // Botón 3: Reservas (Módulo Transaccional)
        btnReservas = new JButton("<html><center><b>3. MÓDULO DE<br>RESERVAS</b><br><br><font size='3' color='blue'>Transacciones ACID<br>Check-In / Check-Out</font></center></html>");
        configurarBotonMenu(btnReservas, new Color(218, 232, 249)); // Fondo celeste para destacar el Core

        panelMenu.add(btnHuespedes);
        panelMenu.add(btnHabitaciones); // Ubicado al centro de forma simétrica
        panelMenu.add(btnReservas);

        // --- 3. PIE DE PÁGINA ---
        JPanel panelFooter = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel lblFooter = new JLabel("Universidad Tecnológica del Perú - Proyecto Arquitectura de Software");
        lblFooter.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblFooter.setForeground(Color.GRAY);
        panelFooter.add(lblFooter);

        // Agregar secciones al contenedor principal
        add(panelHeader, BorderLayout.NORTH);
        add(panelMenu, BorderLayout.CENTER);
        add(panelFooter, BorderLayout.SOUTH);

        // --- ACCIONES DE LOS BOTONES ---
        // Botón Huéspedes (llama a tu vista de huéspedes actual)
        btnHuespedes.addActionListener(e -> {
            new FrmHuesped().setVisible(true);
        });

        // Botón Habitaciones (ABRE LA NUEVA VENTANA DE CREACIÓN)
        btnHabitaciones.addActionListener(e -> {
            new FrmHabitacion().setVisible(true);
        });

        // Botón Reservas (ABRE TU FORMULARIO TRANSACCIONAL CON EL DESCUENTO INTERCORP)
        btnReservas.addActionListener(e -> {
            new FrmReserva().setVisible(true);
        });
    }

    // Método auxiliar para dar estilo moderno a los botones en forma de tarjetas
    private void configurarBotonMenu(JButton boton, Color colorFondo) {
        boton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        boton.setBackground(colorFondo);
        boton.setForeground(new Color(44, 62, 80));
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 214, 229), 1, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
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
