import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {


    public static Connection conectar() {
        String user = "postgres";
        String password = "admin";
        String URL = "jdbc:postgresql://localhost:5432/probas";

        try {
            Connection Conn = DriverManager.getConnection(URL, user, password);

            if (Conn != null) {
                System.out.println("Conexion exitosa");
                return Conn;
            } else {
                throw new RuntimeException("Fallo de conexion");
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException(e);
        }


    }

}
