package com.mycompany.sistemagestionhotelera;

import com.mycompany.sistemagestionhotelera.vista.FrmLogin;
import javax.swing.UIManager;
import com.mycompany.sistemagestionhotelera.vista.FrmMenu;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new FrmLogin().setVisible(true);
        });
    }
}
