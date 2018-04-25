import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoggingPanel {

    JFrame frame;
    JPanel menu = new JPanel();
    JPanel input = new JPanel();

    JTextField loginTF = new JTextField();
    JPasswordField passwordTF = new JPasswordField();
    JTextField dbNameTF = new JTextField();
    JTextField dbHostTF = new JTextField();

    JLabel loginLabel = new JLabel("Enter login: ");
    JLabel passwordLabel = new JLabel("Enter password: ");
    JLabel dbNameLabel = new JLabel("Enter db name: ");
    JLabel dbHostLabel = new JLabel("Enter host name: ");

    JButton loginButton = new JButton("Log in");
    JButton exitButton = new JButton("Exit");



    public LoggingPanel() {
        frame = new JFrame();
        frame.setTitle("APB PSQL CRUD Project");
        frame.setSize(500,400);
        frame.setVisible(true);

        Container cp = frame.getContentPane();
        cp.setLayout(new GridLayout(1,2));
        cp.add(menu);
        cp.add(input);

        menu.setLayout(new GridLayout(6,1));
        input.setLayout(new GridLayout(6,1));

        menu.add(loginLabel);
        menu.add(passwordLabel);
        menu.add(dbNameLabel);
        menu.add(dbHostLabel);
        menu.add(loginButton);
        menu.add(exitButton);

        input.add(loginTF);
        input.add(passwordTF);
        input.add(dbNameTF);
        input.add(dbHostTF);

        dbHostTF.setText("localhost");

        loginButton.addActionListener(new Login());
        exitButton.addActionListener(new Exit());

    }

    class Login implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (passwordTF.getPassword().length == 0 || loginTF.getText().trim().length() == 0 || dbNameTF.getText().trim().length() == 0 || dbNameTF.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null,"Please fill all fields.");
            }

            else {
                String login = loginTF.getText().trim();
                char[] pass = passwordTF.getPassword();
                String password = String.valueOf(pass);
                String dbName = dbNameTF.getText().trim();
                String hostName = dbHostTF.getText().trim();

                Connector con = new Connector(login, password, dbName, hostName);

                if (con.Connect() == 1) {
                    JOptionPane.showMessageDialog(null, "Database opened successfully");
                    frame.setVisible(false);
                    new MenuPanel(login, password, dbName, hostName).frame.setVisible(true);

                }
                else {
                    JOptionPane.showMessageDialog(null, "Connection to the database failed. Try again.");
                }



            }
        }
    }

    class Exit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String args[]) {
        LoggingPanel panel = new LoggingPanel();
    }
}