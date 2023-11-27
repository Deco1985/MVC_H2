package Controlador;

import BD.Querys;
import Modelo.ListaProductosOrdenada;
import Modelo.Producto;
import UI.InterfazUsuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ControladorProducto {
    private final InterfazUsuario InterfazUsuario;
    private final Querys querys;
    private final ListaProductosOrdenada listaProductosOrdenada;


    public ControladorProducto() {
        this.InterfazUsuario = new InterfazUsuario();
        this.querys = new Querys();
        this.listaProductosOrdenada = new ListaProductosOrdenada(querys.obtenerListaProductosOrdenada());

        //Agregar ActionsListeners y querys


    }

    public List<Producto> obtenerProductos() {
        return querys.obtenerListaProductosOrdenada();
    }

    public void agregarProducto(Producto producto) {
        if (querys.registrar(producto)) {
            listaProductosOrdenada.agregarProducto(producto);
        }
    }

    public void modificarProducto(Producto producto) {
        if (querys.modificar(producto)) {
            listaProductosOrdenada.modificarProducto(producto);
        }
    }

    public void eliminarProducto(int id) {
        if (querys.eliminar(id)) {
            listaProductosOrdenada.eliminarProducto(id);
        }
    }

    public void buscarProducto(int id) {
        try {
            Producto productoEncontrado = querys.buscar(id);
            // Aquí puedes realizar acciones con el producto encontrado, como actualizar la interfaz.
        } catch (ExceptionBuscar ExceptionBuscar) {
            // Aquí maneja la excepción personalizada MiExcepcion.
            System.out.println(ExceptionBuscar.getMessage());
            // Puedes mostrar un mensaje al usuario, por ejemplo, en una ventana de diálogo.
        }
    }

    public void ordenarPorNombre() {
        listaProductosOrdenada.ordenarPorNombre();
    }

    public void ordenarPorPrecio() {
        listaProductosOrdenada.ordenarPorPrecio();
    }

    //Controlador sobre los botones de la interfaz
    public void updateTableModel(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        for (Producto producto : obtenerProductos()) {
            Object[] row = {producto.getId(), producto.getCodigo(), producto.getNombre(), producto.getPrecio()};
            tableModel.addRow(row);
        }
    }

    public Producto obtenerProductoDesdeFormulario(String id, String nombre, String codigo, String precio) {
        int parsedId = Integer.parseInt(id);
        int parsedPrecio = Integer.parseInt(precio);

        Producto producto = new Producto();
        producto.setId(parsedId);
        producto.setNombre(nombre);
        producto.setCodigo(codigo);
        producto.setPrecio(parsedPrecio);

        return producto;
    }

    public void limpiarFormulario(JTextField txtId, JTextField txtNombre, JTextField txtCodigo, JTextField txtPrecio) {
        txtId.setText("");
        txtNombre.setText("");
        txtCodigo.setText("");
        txtPrecio.setText("");
    }
    
    //Manejo de la excepción 
}