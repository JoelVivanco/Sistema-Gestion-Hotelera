package sistema.database;



import sistema.formularios.FrmLogin;
import javax.swing.UIManager;
import sistema.formularios.FrmMenu;

public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new FrmLogin().setVisible(true);
        });
    }
}
