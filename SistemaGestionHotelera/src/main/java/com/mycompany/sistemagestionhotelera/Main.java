package com.mycompany.sistemagestionhotelera;

import javax.swing.UIManager;
import com.mycompany.sistemagestionhotelera.vista.FrmMenu;

public class Main {
    public static void main(String[] args) {
        try {
            // Establecer el Look and Feel nativo del sistema operativo
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se pudo establecer el設計 nativo: " + e.getMessage());
        }

        // Lanzar el Menú Principal como la primera pantalla del software
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMenu().setVisible(true);
            }
        });
    }
}