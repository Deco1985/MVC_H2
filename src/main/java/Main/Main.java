package Main;

import UI.InterfazUsuario;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                InterfazUsuario interfaz = new InterfazUsuario();
                interfaz.setVisible(true);
            }
        });
    }
}
