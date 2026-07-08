package com.mycompany.sistemagestionhotelera.vista;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmMenu extends javax.swing.JFrame {

    private JButton btnModuloHuespedes;
    private JButton btnModuloReservas;
    private JButton btnSalir;

    public FrmMenu() {
        // Configuración del Menú Principal
        setTitle("Sistema Integrado de Gestión Hotelera - Panel Principal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra todo el programa al salir
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(15, 15));

        // --- 1. BANNER DE BIENVENIDA ---
        JPanel panelBanner = new JPanel(new GridLayout(2, 1));
        panelBanner.setBackground(new Color(24, 43, 73)); // Azul marino corporativo
        panelBanner.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblTitulo = new JLabel("SISTEMA DE GESTIÓN HOTELERA", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitulo.setForeground(Color.WHITE);

        JLabel lblSub = new JLabel("Panel de Control Administrativo", SwingConstants.CENTER);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSub.setForeground(new Color(170, 185, 205));

        panelBanner.add(lblTitulo);
        panelBanner.add(lblSub);
        add(panelBanner, BorderLayout.NORTH);

        // --- 2. PANEL CENTRAL: BOTONES DE ACCESO ---
        JPanel panelAccesos = new JPanel(new GridLayout(1, 2, 20, 0));
        panelAccesos.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), 
                " Seleccione un Módulo Operativo ", 
                TitledBorder.CENTER, 
                TitledBorder.TOP, 
                new Font("Segoe UI", Font.BOLD, 12)
        ));
        panelAccesos.setBackground(new Color(245, 247, 250));

        // Estilo de botones grandes
        Font fuenteBotones = new Font("Segoe UI", Font.BOLD, 14);

        btnModuloHuespedes = new JButton("<html><center><b>GESTIÓN DE<br>HUÉSPEDES</b><br><small>Registro y Control</small></center></html>");
        btnModuloHuespedes.setFont(fuenteBotones);
        btnModuloHuespedes.setBackground(Color.WHITE);
        btnModuloHuespedes.setFocusPainted(false);
        btnModuloHuespedes.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnModuloReservas = new JButton("<html><center><b>CONTROL DE<br>RESERVAS</b><br><small>Módulo Transaccional</small></center></html>");
        btnModuloReservas.setFont(fuenteBotones);
        btnModuloReservas.setBackground(Color.WHITE);
        btnModuloReservas.setFocusPainted(false);
        btnModuloReservas.setCursor(new Cursor(Cursor.HAND_CURSOR));

        panelAccesos.add(btnModuloHuespedes);
        panelAccesos.add(btnModuloReservas);
        
        // Agregar margen alrededor de los botones
        JPanel contenedorCentral = new JPanel(new BorderLayout());
        contenedorCentral.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        contenedorCentral.add(panelAccesos, BorderLayout.CENTER);
        add(contenedorCentral, BorderLayout.CENTER);

        // --- 3. PANEL INFERIOR: PIE DE PÁGINA ---
        JPanel panelPie = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        btnSalir = new JButton("Cerrar Sistema");
        btnSalir.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        panelPie.add(btnSalir);
        add(panelPie, BorderLayout.SOUTH);

        // --- ACCIONES DE LOS BOTONES ---
        btnModuloHuespedes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre la ventana de huéspedes sin cerrar el menú
                new FrmHuesped().setVisible(true);
            }
        });

        btnModuloReservas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre la ventana transaccional de reservas
                new FrmReserva().setVisible(true);
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}