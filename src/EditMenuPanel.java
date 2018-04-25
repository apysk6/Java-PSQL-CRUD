import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Vector;

public class EditMenuPanel {

    String login;
    String password;
    String dbName;
    String hostName;

    JFrame frame;
    JPanel menu = new JPanel();
    JPanel input = new JPanel();

    JLabel nameLabel = new JLabel("Select name:");
    JButton editButton = new JButton("Edit record");
    JButton backButton = new JButton("Back");

    JComboBox bx;
    String str;

    public EditMenuPanel(String login, String password, String dbName, String hostName) {

        this.login = login;
        this.password = password;
        this.dbName = dbName;
        this.hostName = hostName;

        frame = new JFrame();
        frame.setTitle("APB PSQL CRUD Project");
        frame.setSize(500, 400);
        frame.setVisible(true);


        Container cp = frame.getContentPane();
        cp.setLayout(new GridLayout(3, 2));
        cp.add(menu);
        cp.add(input);

        menu.add(nameLabel);
        menu.add(editButton);
        menu.add(backButton);





        editButton.addActionListener(new EditMenuPanel.Edit());
        backButton.addActionListener(new EditMenuPanel.Back());


        try {
            String urlBuild = "jdbc:postgresql://" + hostName + ":5432/" + dbName;

            Class.forName("org.postgresql.Driver");
            Connection c = null;
            c = DriverManager.getConnection(urlBuild, login, password);

            PreparedStatement ps = c.prepareStatement("SELECT name, surname FROM Person;");
            ResultSet rs = ps.executeQuery();
            Vector v1 = new Vector();

            while (rs.next()) {
                String s1 = rs.getString(1);
                String s2 = rs.getString(2);
                String s3 = s1 + " " + s2;

                v1.add(s3);

            }
            bx = new JComboBox(v1);
            bx.setBounds(240, 20, 200, 20);
            menu.add(bx);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    class Edit implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            str = (String) bx.getSelectedItem();
            String[] fullName = str.split(" ");
            String name = fullName[0];
            String surname = fullName[1];

            frame.setVisible(false);
            new EditsPanel(name, surname, login, password, dbName, hostName).frame.setVisible(true);


        }
        }

    class Back implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            new MenuPanel(login, password, dbName, hostName).frame.setVisible(true);
        }
    }
}

