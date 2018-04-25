import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

public class Export {

    String login;
    String password;
    String dbName;
    String hostName;

    public Export(String login, String password, String dbName, String hostName) {
        this.login = login;
        this.password = password;
        this.dbName = dbName;
        this.hostName = hostName;
    }

    public void ExportHTML() {
        try {
            String urlBuild = "jdbc:postgresql://" + hostName + ":5432/" + dbName;

            Class.forName("org.postgresql.Driver");
            Connection c = null;
            c = DriverManager.getConnection(urlBuild, login, password);

            FileWriter fWriter = new FileWriter("persons.html");
            BufferedWriter bWriter = new BufferedWriter(fWriter);

            Statement st = c.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM Person ORDER BY id;");

            bWriter.write("<!DOCTYPE html> \n" +
                    "<head> \n" +
                         "<meta charset=\"UTF-8\" name=\"viewport\" content=\"width=device-width, initial-scale=1\"> \n" +
                         "<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"> \n" +
                         "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script> \n" +
                         "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script> \n" +
                         "<title>Persons DB HTML Export</title> \n" +
                    "</head> \n" +
                    "<body> \n" +

                    "<div class=\"container\"> \n" +
                    "<h2>Persons HTML Export</h2> \n" +

                    "<div class=\"table-responsive\"> \n" +
                    "<table class=\"table\"> \n" +

                        "<thead> \n" +
                            "<tr> \n" +
                              "<th>ID</th> \n" +
                              "<th>Name</th> \n" +
                              "<th>Surname</th> \n" +
                              "<th>Birth Date</th> \n" +
                              "<th>Position</th> \n" +
                              "<th>Salary</th> \n" +
                              "<th>Team</th> \n" +
                            "</tr> \n" +
                        "</thead> \n" +
                        "<tbody> \n");

            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String surname = rs.getString(3);
                String birthDate = rs.getString(4);
                String position = rs.getString(5);
                String salary = rs.getString(6);
                String team = rs.getString(7);

                bWriter.write("\n" +
                        "<tr> \n" +
                        "<th>" + id + "</th> \n" +
                        "<th>" + name + "</th> \n" +
                        "<th>" + surname + "</th> \n" +
                        "<th>" + birthDate + "</th> \n" +
                        "<th>" + position + "</th> \n" +
                        "<th>" + salary + "</th> \n" +
                        "<th>" + team + "</th> \n" +
                        "</tr> \n");


                bWriter.newLine();
            }

                bWriter.write("</tbody> \n" +
                                "</table> \n" +
                                "</div> \n" +
                                "</div> \n" +
                                "</body> \n" +
                                "</html>");




            bWriter.close();
            fWriter.close();
            rs.close();
            st.close();
            c.close();

            JOptionPane.showMessageDialog(null, "Database export  succeed.");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
