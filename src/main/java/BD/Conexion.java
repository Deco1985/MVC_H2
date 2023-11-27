
package BD;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {
    private static Connection con = null;  // 'con' estático para obtenerListaProductosOrdenada()

    public static Connection getConexion() {
        try {
            if (con == null || con.isClosed()) {
                Properties properties = new Properties();
                FileInputStream input = new FileInputStream("src/main/resources/application.properties");
                properties.load(input);
                Class.forName("org.h2.Driver");
                con = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"), properties.getProperty("password"));
                JOptionPane.showMessageDialog(null, "conectado");
            }
        } catch (SQLException e) {
            System.err.println(e);
            JOptionPane.showMessageDialog(null, "error");
        } catch (ClassNotFoundException | IOException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
        }
        return con;
    }
}
