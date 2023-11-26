package Controlador;

import BD.Querys;
import Modelo.ListaProductosOrdenada;
import Modelo.Producto;

import java.util.List;

public class ControladorProducto {
    private Querys querys;
    private ListaProductosOrdenada listaProductosOrdenada;

    public ControladorProducto() {
        this.querys = new Querys();
        this.listaProductosOrdenada = new ListaProductosOrdenada(querys.obtenerListaProductosOrdenada());
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

    public Producto buscarProducto(int id) {
        return querys.buscar(id);
    }

    public void ordenarPorNombre() {
        listaProductosOrdenada.ordenarPorNombre();
    }

    public void ordenarPorPrecio() {
        listaProductosOrdenada.ordenarPorPrecio();
    }


}