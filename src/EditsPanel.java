import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class EditsPanel {

    String login;
    String password;
    String dbName;
    String hostName;
    String name;
    String surname;

    JFrame frame;
    JPanel menu = new JPanel();
    JPanel input = new JPanel();


    JLabel namePersonLabel = new JLabel("Name: ");
    JLabel surnameLabel = new JLabel("Surname: ");
    JLabel birthLabel = new JLabel("Birth date: ");
    JLabel positionLabel = new JLabel("Position: ");
    JLabel salaryLabel = new JLabel("Salary: ");
    JLabel teamLabel = new JLabel("Team: ");

    JTextField nameTF = new JTextField();
    JTextField surnameTF = new JTextField();
    JTextField birthTF = new JTextField();
    JTextField positionTF = new JTextField();
    JTextField salaryTF = new JTextField();
    JTextField teamTF = new JTextField();

    JButton editButton = new JButton("Edit");
    JButton backButton = new JButton("Back");

    public EditsPanel(String name, String surname, String login, String password, String dbName, String hostName) {

        this.name = name;
        this.surname = surname;
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


        menu.add(namePersonLabel);
        menu.add(surnameLabel);
        menu.add(birthLabel);
        menu.add(positionLabel);
        menu.add(salaryLabel);
        menu.add(teamLabel);

        menu.add(editButton);
        menu.add(backButton);


        input.add(nameTF);
        input.add(surnameTF);
        input.add(birthTF);
        input.add(positionTF);
        input.add(salaryTF);
        input.add(teamTF);

        editButton.addActionListener(new EditsPanel.Edit());
        backButton.addActionListener(new EditsPanel.Back());


        try {
            String urlBuild = "jdbc:postgresql://" + hostName + ":5432/" + dbName;

            Class.forName("org.postgresql.Driver");
            Connection c = null;
            c = DriverManager.getConnection(urlBuild, login, password);

            PreparedStatement ps = c.prepareStatement("SELECT * FROM Person WHERE name = ? AND surname = ?");
            ps.setString(1, name);
            ps.setString(2, surname);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nameTF.setText(rs.getString(2));
                surnameTF.setText(rs.getString(3));
                birthTF.setText(rs.getString(4));
                positionTF.setText(rs.getString(5));
                salaryTF.setText(rs.getString(6));
                teamTF.setText(rs.getString(7));
            }

        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }


    }

    class Back implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            new EditMenuPanel(login, password, dbName, hostName).frame.setVisible(true);
        }
    }

    class Edit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (nameTF.getText().trim().length() == 0 || surnameTF.getText().trim().length() == 0 || birthTF.getText().trim().length() == 0 || positionTF.getText().trim().length() == 0 || salaryTF.getText().trim().length() == 0 || teamTF.getText().trim().length() == 0) {
                JOptionPane.showMessageDialog(null, "Please fill all fields.");
            } else {
                if (birthTF.getText().matches("\\d{4}-\\d{2}-\\d{2}")) {
                    try {
                        String urlBuild = "jdbc:postgresql://" + hostName + ":5432/" + dbName;

                        Class.forName("org.postgresql.Driver");
                        Connection c = null;
                        c = DriverManager.getConnection(urlBuild, login, password);

                        PreparedStatement ps = c.prepareStatement("UPDATE Person SET name = ?, surname = ?, birth_date = ?, position = ?, salary = ?, team = ? WHERE name = ? AND surname = ?");
                        ps.setString(1, nameTF.getText().trim());
                        ps.setString(2, surnameTF.getText().trim());
                        ps.setObject(3, Date.valueOf(birthTF.getText()));
                        ps.setString(4, positionTF.getText().trim());
                        ps.setDouble(5, Double.parseDouble(salaryTF.getText().trim()));
                        ps.setString(6, positionTF.getText().trim());
                        ps.setString(7, name);
                        ps.setString(8, surname);

                        ps.executeUpdate();
                        ps.close();
                        c.close();

                        JOptionPane.showMessageDialog(null, "Person edited successfully.");
                        frame.setVisible(false);
                        new EditMenuPanel(login, password, dbName, hostName).frame.setVisible(true);


                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }

                }
                else {
                    JOptionPane.showMessageDialog(null, "Incorrect date format.");
                }
            }
        }
    }
}
