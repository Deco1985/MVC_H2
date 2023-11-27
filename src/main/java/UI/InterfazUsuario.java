package UI;

import Controlador.ControladorProducto;
import Modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazUsuario extends JFrame {


    private DefaultTableModel tableModel;
    private JTable table;

    private JTextField txtId, txtNombre, txtCodigo, txtPrecio;

    private ControladorProducto controladorProducto;
    public InterfazUsuario() {

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
                Producto producto = controladorProducto.obtenerProductoDesdeFormulario(
                        txtId.getText(), txtNombre.getText(), txtCodigo.getText(), txtPrecio.getText()
                );
                controladorProducto.agregarProducto(producto);
                controladorProducto.updateTableModel(tableModel);
                controladorProducto.limpiarFormulario(txtId, txtNombre, txtCodigo, txtPrecio);
            }
        });

        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Producto producto = controladorProducto.obtenerProductoDesdeFormulario(
                        txtId.getText(), txtNombre.getText(), txtCodigo.getText(), txtPrecio.getText()
                );
                controladorProducto.modificarProducto(producto);
                controladorProducto.updateTableModel(tableModel);
                controladorProducto.limpiarFormulario(txtId, txtNombre, txtCodigo, txtPrecio);
            }
        });

        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controladorProducto.eliminarProducto(Integer.parseInt(txtId.getText()));
                controladorProducto.updateTableModel(tableModel);
                controladorProducto.limpiarFormulario(txtId, txtNombre, txtCodigo, txtPrecio);
            }

        //Agregar nuevo actionListener para buscar 
            

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
        controladorProducto.updateTableModel(tableModel);
    }
}