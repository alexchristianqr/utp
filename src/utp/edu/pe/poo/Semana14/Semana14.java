package semana14;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class Conexion {

    private static Connection cnx = null;

    public static Connection obtener() throws SQLException, ClassNotFoundException {
        if (cnx == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                String xuser = "root";
                String xpassword = "";
                cnx = DriverManager.getConnection("jdbc:mysql://localhost:3308/inventario", xuser, xpassword);
            } catch (SQLException ex) {
                throw new SQLException(ex);
            } catch (ClassNotFoundException ex) {
                throw new ClassCastException(ex.getMessage());
            }
        }
        return cnx;
    }

    public static void cerrar() throws SQLException {
        if (cnx != null) {
            cnx.close();
        }
    }
    
    

}





import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Principal {
/** Clase principal y método MAIN */
    public static void main(String[] args) {
        String consulta="SELECT title,length FROM film";
        
        try {
            Statement sentencia=Conexion.obtener().createStatement();
            ResultSet resultado=sentencia.executeQuery(consulta);
            while (resultado.next())
            {
                System.out.println (resultado.getString (1) + " " + resultado.getInt(2));
            }
            
        } catch (ClassNotFoundException | SQLException e) {
            
            e.printStackTrace();
        }
    }

}
