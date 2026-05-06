import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.List;

public class VentanaProductos extends JFrame {

    private JTable tabla;
    private JLabel lblTotal;

    public VentanaProductos() {

        setTitle("Productos Oracle 19c");

        setSize(900, 500);

        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        DefaultTableModel modelo =
                new DefaultTableModel();

        modelo.addColumn("ID");
        modelo.addColumn("NOMBRE");
        modelo.addColumn("CATEGORIA");
        modelo.addColumn("PRECIO");
        modelo.addColumn("STOCK");

        tabla = new JTable(modelo);

        JScrollPane scroll =
                new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);

        lblTotal = new JLabel(
                "Total Productos: 0"
        );

        lblTotal.setFont(
                new Font("Arial", Font.BOLD, 18)
        );

        add(lblTotal, BorderLayout.SOUTH);

        cargarDatos();

        setVisible(true);
    }

    private void cargarDatos() {

        ProductoDAO dao =
                new ProductoDAO();

        List<Producto> lista =
                dao.listarProductos();

        DefaultTableModel modelo =
                (DefaultTableModel) tabla.getModel();

        for (Producto p : lista) {

            Object[] fila = {

                    p.getId(),
                    p.getNombre(),
                    p.getCategoria(),
                    p.getPrecio(),
                    p.getStock()
            };

            modelo.addRow(fila);
        }

        int total =
                dao.contarProductos();

        lblTotal.setText(
                "Total Productos: " + total
        );
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new VentanaProductos();
        });
    }
}   