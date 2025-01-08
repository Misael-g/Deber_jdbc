import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login {
    private JButton botoniniciar;
    private JTextField Usuariotex;
    private JPasswordField contraseniatex;
    private JLabel usuariolabel;
    private JLabel contralabel;
    public JPanel login;

    public Login() {
        botoniniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = Usuariotex.getText();
                String contrasenia = new String(contraseniatex.getPassword());

                if (autenticar(usuario, contrasenia)) {
                    JOptionPane.showMessageDialog(null, "Inicio exitoso.");
                    JFrame frame = new JFrame("Bienvenido");
                    frame.setContentPane(new segundo(usuario).segundaparte);
                    frame.setSize(800, 600);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                    ((JFrame) botoniniciar.getTopLevelAncestor()).dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
                }
            }
        });
    }

    private boolean autenticar(String usuario, String contrasenia) {
        String url = "jdbc:mysql://localhost:3306/mi_base_de_datos";
        String user = "root";
        String password = "123456";
        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String query = "select * from usuarios where usuario = ? and contrasenia = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, usuario);
            statement.setString(2, contrasenia);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
