package Modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListaProductosOrdenada {
    private List<Producto> listaProductos;

    public ListaProductosOrdenada(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public void ordenarPorNombre() {
        Collections.sort(listaProductos, Comparator.comparing(Producto::getNombre));
    }

    public void ordenarPorPrecio() {
        Collections.sort(listaProductos, Comparator.comparingInt(Producto::getPrecio).reversed());
    }

    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public void modificarProducto(Producto productoModificado) {
        for (int i = 0; i < listaProductos.size(); i++) {
            Producto productoActual = listaProductos.get(i);
            if (productoActual.getId() == productoModificado.getId()) {
                listaProductos.set(i, productoModificado);
                break;
            }
        }
    }

    public void eliminarProducto(int id) {
        listaProductos.removeIf(producto -> producto.getId() == id);
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }
}
