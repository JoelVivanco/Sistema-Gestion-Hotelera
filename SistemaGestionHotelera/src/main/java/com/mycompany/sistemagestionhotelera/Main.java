package com.mycompany.sistemagestionhotelera;

import javax.swing.UIManager;
import com.mycompany.sistemagestionhotelera.vista.FrmMenu;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No se pudo establecer: " + e.getMessage());
        }

        // Lanzar el Menu Principal
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmMenu().setVisible(true);
            }
        });
    }
}
