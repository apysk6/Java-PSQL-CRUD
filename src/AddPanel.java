import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddPanel {

    String login;
    String password;
    String dbName;
    String hostName;

    JFrame frame;
    JPanel menu = new JPanel();
    JPanel input = new JPanel();

    JTextField nameTF = new JTextField();
    JTextField surnameTF = new JTextField();
    JTextField birthTF = new JTextField();
    JTextField positionTF = new JTextField();
    JTextField salaryTF = new JTextField();
    JTextField teamTF = new JTextField();

    JLabel nameLabel = new JLabel("Enter name:");
    JLabel surnameLabel = new JLabel("Enter surname:");
    JLabel birthLabel = new JLabel("Enter date of birth (yyyy-mm-dd):");
    JLabel positionLabel = new JLabel("Enter position:");
    JLabel salaryLabel = new JLabel("Enter salary:");
    JLabel teamLabel = new JLabel("Enter team name:");

    JButton addButton = new JButton("Add person");
    JButton backButton = new JButton("Go back");

    public AddPanel(String login, String password, String dbName, String hostName) {

        this.login = login;
        this.password = password;
        this.dbName = dbName;
        this.hostName = hostName;

        frame = new JFrame();
        frame.setTitle("APB PSQL CRUD Project");
        frame.setSize(500, 400);
        frame.setVisible(true);

        Container cp = frame.getContentPane();
        cp.setLayout(new GridLayout(1, 2));
        cp.add(menu);
        cp.add(input);

        menu.setLayout(new GridLayout(8, 1));
        input.setLayout(new GridLayout(8, 1));

        menu.add(nameLabel);
        menu.add(surnameLabel);
        menu.add(birthLabel);
        menu.add(positionLabel);
        menu.add(salaryLabel);
        menu.add(teamLabel);
        menu.add(addButton);
        menu.add(backButton);

        input.add(nameTF);
        input.add(surnameTF);
        input.add(birthTF);
        input.add(positionTF);
        input.add(salaryTF);
        input.add(teamTF);

        addButton.addActionListener(new AddPanel.Add());
        backButton.addActionListener(new AddPanel.Back());
    }

    class Add implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (nameTF.getText().trim().length() == 0 || surnameTF.getText().trim().length() == 0 || birthTF.getText().trim().length() == 0 || positionTF.getText().trim().length() == 0 || salaryTF.getText().trim().length() == 0 || teamTF.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Please fill all fields");
            } else {
                if (birthTF.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {

                    String urlBuild = "jdbc:postgresql://" + hostName + ":5432/" + dbName;

                    try {
                        Class.forName("org.postgresql.Driver");
                        Connection c = null;
                        c = DriverManager.getConnection(urlBuild, login, password);

                        PreparedStatement st = c.prepareStatement("INSERT INTO Person(name, surname, birth_date, position, salary, team) VALUES(?,?,?,?,?,?)");
                        st.setString(1, nameTF.getText());
                        st.setString(2, surnameTF.getText());
                        st.setObject(3, Date.valueOf(birthTF.getText()));
                        st.setString(4, positionTF.getText());
                        st.setDouble(5, Double.parseDouble(salaryTF.getText()));
                        st.setString(6, teamTF.getText());

                        st.executeUpdate();
                        st.close();
                        c.close();

                        JOptionPane.showMessageDialog(null, "Person added successfully.");
                        nameTF.setText("");
                        surnameTF.setText("");
                        birthTF.setText("");
                        positionTF.setText("");
                        salaryTF.setText("");
                        teamTF.setText("");


                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Person added successfully.");
                }
            }


        }
    }

        class Back implements ActionListener {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                new MenuPanel(login, password, dbName, hostName).frame.setVisible(true);
            }
        }

}
