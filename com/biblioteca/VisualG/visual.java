package com.biblioteca.VisualG;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.biblioteca.Modelos.LibroDTO;
import com.biblioteca.controladores.LibroController;

import java.awt.*;
import java.util.List;

public class visual extends JFrame {
    private JTextField txtBuscar;
    private JButton btnBuscar, btnAgregar, btnEliminar, btnPrestar, btnDevolver;
    private JTable tablaLibros;
    private DefaultTableModel modeloTabla;

    
    private final LibroController libroController;

    public visual() {
    
        libroController = new LibroController();

        setTitle("Biblioteca escolar");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        JPanel panelAcciones = new JPanel();
        txtBuscar = new JTextField(20);
        btnBuscar = new JButton("Buscar");
        btnAgregar = new JButton("Agregar");
        btnEliminar = new JButton("Eliminar");
        btnPrestar = new JButton("Prestar");
        btnDevolver = new JButton("Devolver");

        panelAcciones.add(new JLabel("Buscar por título:"));
        panelAcciones.add(txtBuscar);
        panelAcciones.add(btnBuscar);
        panelAcciones.add(btnAgregar);
        panelAcciones.add(btnEliminar);
        panelAcciones.add(btnPrestar);
        panelAcciones.add(btnDevolver);
        add(panelAcciones, BorderLayout.NORTH);

        
        String[] columnas = {"ID", "Título", "Autor", "Año"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaLibros = new JTable(modeloTabla);
        add(new JScrollPane(tablaLibros), BorderLayout.CENTER);

        
        btnBuscar.addActionListener(e -> realizarBusqueda());
        btnAgregar.addActionListener(e -> mostrarDialogoAgregar());
        btnEliminar.addActionListener(e -> eliminarLibro());
        btnPrestar.addActionListener(e -> PrestarLibro());
        btnDevolver.addActionListener(e -> DevolverLibro());

    }


    private void realizarBusqueda() {
        String textoBusqueda = txtBuscar.getText();
        modeloTabla.setRowCount(0);
        List<LibroDTO> resultados = libroController.buscarPorTitulo(textoBusqueda);

        for (LibroDTO libro : resultados) {
            modeloTabla.addRow(new Object[]{libro.getId(), libro.getTitulo(), libro.getAutor(), libro.getAnio()});
        }

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron libros con el título ingresado.");
        }
    }

    private void mostrarDialogoAgregar() {
        JTextField txtTitulo = new JTextField();
        JTextField txtAutor = new JTextField();
        JTextField txtAnio = new JTextField();

        Object[] message = {
            "Título:", txtTitulo,
            "Autor:", txtAutor,
            "Año:", txtAnio
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Agregar libro", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String titulo = txtTitulo.getText();
                String autor = txtAutor.getText();
                int anio = Integer.parseInt(txtAnio.getText());
                libroController.agregarLibro(new LibroDTO(0, titulo, autor, anio));
                JOptionPane.showMessageDialog(this, "Libro agregado.");
                realizarBusqueda(); 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al agregar el libro: " + e.getMessage());
            }
        }
    }

    private void eliminarLibro() {
        int filaSeleccionada = tablaLibros.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un libro para eliminar.");
            return;
        }

        int id = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este libro?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            libroController.eliminarLibro(id);
            JOptionPane.showMessageDialog(this, "Libro eliminado.");
            realizarBusqueda();
        }
    }

    private void PrestarLibro (){

        JOptionPane.showMessageDialog(this,"Prestamo Registrado");
    } 

    private void DevolverLibro (){
            
        JOptionPane.showMessageDialog(this,"Devuelto");
    } 


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            visual ventana = new visual();
            ventana.setVisible(true);
        });
    }
}
 