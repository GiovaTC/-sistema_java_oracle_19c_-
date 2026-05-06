import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public List<Producto> listarProductos() {

        List<Producto> lista = new ArrayList<>();

        try {

            Connection cn = ConexionDB.getConexion();

            String sql =
                    "SELECT * FROM PRODUCTOS_J ORDER BY ID";

            PreparedStatement ps =
                    cn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Producto p = new Producto(
                        rs.getInt("ID"),
                        rs.getString("NOMBRE"),
                        rs.getString("CATEGORIA"),
                        rs.getDouble("PRECIO"),
                        rs.getInt("STOCK")
                );

                lista.add(p);
            }

            rs.close();
            ps.close();
            cn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return lista;
    }

    public int contarProductos() {

        int total = 0;

        try {

            Connection cn = ConexionDB.getConexion();

            String sql =
                    "SELECT COUNT(*) TOTAL FROM PRODUCTOS_J";

            PreparedStatement ps =
                    cn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                total = rs.getInt("TOTAL");
            }

            rs.close();
            ps.close();
            cn.close();

        } catch (Exception e) {

            e.printStackTrace();
        }

        return total;
    }
}
