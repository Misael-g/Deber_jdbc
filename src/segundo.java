import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class segundo {
    public JPanel segundaparte;
    private JLabel mensbienvenida;
    private JTable table1;
    private JScrollPane tablaprinc;

    public segundo(String usuario) {
        mensbienvenida.setText("¡Bienvenido, " + usuario + "!");
        cargarDatos();
    }

    private void cargarDatos() {
        String url = "jdbc:mysql://localhost:3306/mi_base_de_datos";
        String user = "root";
        String password = "123456";

        String[] columnNames = {"ID", "Nombre", "Edad", "Email"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table1.setModel(model);

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from datos");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                int edad = resultSet.getInt("edad");
                String email = resultSet.getString("email");

                model.addRow(new Object[]{id, nombre, edad, email});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
