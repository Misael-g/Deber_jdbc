import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestMySQLConnection {
    public static void main(String[] args) {
        // URL de conexión a la base de datos
        String url = "jdbc:mysql://localhost:3306/empresa"; // Cambia "localhost" y "tu_base_de_datos"
        String user = "root"; // Cambia por tu usuario de MySQL, por ejemplo "root"
        String password = "123456"; // Cambia por tu contraseña de MySQL

        try {
            // Intentar establecer la conexión
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conexión exitosa a la base de datos MySQL.");
            connection.close(); // Cerrar la conexión
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos:");
            e.printStackTrace();
        }
    }
}
