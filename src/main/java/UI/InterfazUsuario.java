package UI;

import Controlador.ControladorProducto;
import Modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazUsuario extends JFrame {
    private ControladorProducto controladorProducto;

    private DefaultTableModel tableModel;
    private JTable table;

    private JTextField txtId, txtNombre, txtCodigo, txtPrecio;

    public InterfazUsuario() {
        controladorProducto = new ControladorProducto();

        setTitle("Gestión de Productos");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        // Inicializar la tabla y el modelo
        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Código");
        tableModel.addColumn("Precio");

        table = new JTable(tableModel);
        updateTable();

        // Botones de ordenar
        JButton btnOrdenarNombre = new JButton("Ordenar Alfabéticamente");
        JButton btnOrdenarPrecio = new JButton("Ordenar por Precio");

        btnOrdenarNombre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorProducto.ordenarPorNombre();
                updateTable();
            }
        });

        btnOrdenarPrecio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorProducto.ordenarPorPrecio();
                updateTable();
            }
        });

        // Campos de texto y botones de agregar, modificar, eliminar
        txtId = new JTextField(5);
        txtNombre = new JTextField(10);
        txtCodigo = new JTextField(10);
        txtPrecio = new JTextField(5);

        JButton btnAgregar = new JButton("Agregar");
        JButton btnModificar = new JButton("Modificar");
        JButton btnEliminar = new JButton("Eliminar");

        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProducto();
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarProducto();
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        // Diseño de la interfaz
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnOrdenarNombre);
        panelBotones.add(btnOrdenarPrecio);

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2));
        panelFormulario.add(new JLabel("ID:"));
        panelFormulario.add(txtId);
        panelFormulario.add(new JLabel("Nombre:"));
        panelFormulario.add(txtNombre);
        panelFormulario.add(new JLabel("Código:"));
        panelFormulario.add(txtCodigo);
        panelFormulario.add(new JLabel("Precio:"));
        panelFormulario.add(txtPrecio);

        JPanel panelBotonesFormulario = new JPanel();
        panelBotonesFormulario.add(btnAgregar);
        panelBotonesFormulario.add(btnModificar);
        panelBotonesFormulario.add(btnEliminar);

        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(panelFormulario, BorderLayout.CENTER);
        panel.add(panelBotonesFormulario, BorderLayout.SOUTH);

        add(panel);
    }

    private void updateTable() {
        tableModel.setRowCount(0);
        for (Producto producto : controladorProducto.obtenerProductos()) {
            Object[] row = {producto.getId(), producto.getNombre(), producto.getCodigo(), producto.getPrecio()};
            tableModel.addRow(row);
        }
    }

    private void agregarProducto() {
        int id = Integer.parseInt(txtId.getText());
        String nombre = txtNombre.getText();
        String codigo = txtCodigo.getText();
        int precio = Integer.parseInt(txtPrecio.getText());

        Producto nuevoProducto = new Producto();
        nuevoProducto.setId(id);
        nuevoProducto.setNombre(nombre);
        nuevoProducto.setCodigo(codigo);
        nuevoProducto.setPrecio(precio);

        controladorProducto.agregarProducto(nuevoProducto);
        updateTable();
        limpiarFormulario();
    }

    private void modificarProducto() {
        int id = Integer.parseInt(txtId.getText());
        String nombre = txtNombre.getText();
        String codigo = txtCodigo.getText();
        int precio = Integer.parseInt(txtPrecio.getText());

        Producto productoModificado = new Producto();
        productoModificado.setId(id);
        productoModificado.setNombre(nombre);
        productoModificado.setCodigo(codigo);
        productoModificado.setPrecio(precio);

        controladorProducto.modificarProducto(productoModificado);
        updateTable();
        limpiarFormulario();
    }

    private void eliminarProducto() {
        int id = Integer.parseInt(txtId.getText());
        controladorProducto.eliminarProducto(id);
        updateTable();
        limpiarFormulario();
    }

    private void limpiarFormulario() {
        txtId.setText("");
        txtNombre.setText("");
        txtCodigo.setText("");
        txtPrecio.setText("");
    }

}

