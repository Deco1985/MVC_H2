package BD;

import Modelo.Producto;
import Controlador.ExceptionBuscar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Querys {
    private Connection connection;

    public Querys() {
        // Crear una instancia de Conexion y obtener la conexión
        this.connection = new Conexion().getConexion();
    }

    public boolean registrar(Producto producto) {
        String sql = "INSERT INTO PRODUCTO (CODIGO, NOMBRE, PRECIO) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, producto.getCodigo());
            statement.setString(2, producto.getNombre());
            statement.setInt(3, producto.getPrecio());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean modificar(Producto producto) {
        String sql = "UPDATE PRODUCTO SET CODIGO = ?, NOMBRE = ?, PRECIO = ? WHERE Id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, producto.getCodigo());
            statement.setString(2, producto.getNombre());
            statement.setInt(3, producto.getPrecio());
            statement.setInt(4, producto.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM PRODUCTO WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Producto buscar(int id) throws ExceptionBuscar {
        String sql = "SELECT * FROM PRODUCTO WHERE ID = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Producto producto = new Producto();
                producto.setId(resultSet.getInt("Id"));
                producto.setCodigo(resultSet.getString("Codigo"));
                producto.setNombre(resultSet.getString("Nombre"));
                producto.setPrecio(resultSet.getInt("Precio"));
                return producto;  
            } else {
                throw new ExceptionBuscar("No hay coincidencias");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ExceptionBuscar("Error");
        }
    }

    public ArrayList<Producto> obtenerListaProductosOrdenada() {
        ArrayList<Producto> listaProductos = new ArrayList<>();

        try (Connection con = Conexion.getConexion();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM PRODUCTO");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId(rs.getInt("id"));
                producto.setCodigo(rs.getString("codigo"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getInt("precio"));
                listaProductos.add(producto);
            }
            return listaProductos;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
