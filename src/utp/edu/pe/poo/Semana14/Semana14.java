package utp.edu.pe.poo.Semana14;

import java.sql.*;

public class Semana14 {

    public static void main(String[] args) {
        queryConsultar();
        queryInsertar();
        queryActualizar();
        queryEliminar();
    }

    public static void queryConsultar() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.obtenerConexion();

            stmt = conn.prepareStatement("SELECT * FROM country where code = ?");
            stmt.setString(1, "ABW");

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String code = rs.getString("code");
                String name = rs.getString("name");
                System.out.println("Code: " + code + " Name: " + name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrarConsulta(stmt);
            Conexion.cerrarConexion(conn);
        }
    }

    public static int queryInsertar() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.obtenerConexion();

            stmt = conn.prepareStatement("insert into city (Name, CountryCode, District, Population) values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, "TEST");
            stmt.setString(2, "PER");
            stmt.setString(3, "TEST");
            stmt.setInt(4, 1045);

            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                int code = rs.getInt(1);
                System.out.println("Code: " + code);
            }

            return stmt.executeUpdate();

        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrarConsulta(stmt);
            Conexion.cerrarConexion(conn);
        }
    }

    public static int queryActualizar() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.obtenerConexion();

            stmt = conn.prepareStatement("update city set name = ? where id = ?");
            stmt.setString(1, "Alex");
            stmt.setInt(2, 4082);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrarConsulta(stmt);
            Conexion.cerrarConexion(conn);
        }
    }

    public static int queryEliminar() {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.obtenerConexion();

            stmt = conn.prepareStatement("delete from city where id = ?");
            stmt.setInt(1, 4082);

            return stmt.executeUpdate();

        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Conexion.cerrarConsulta(stmt);
            Conexion.cerrarConexion(conn);
        }
    }

}
