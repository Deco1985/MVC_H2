package Main;

import UI.InterfazUsuario;
import Controlador.ControladorProducto;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ControladorProducto controladorProducto = new ControladorProducto();
                InterfazUsuario interfaz = new InterfazUsuario();
                interfaz.setVisible(true);
            }
        });
    }
}
