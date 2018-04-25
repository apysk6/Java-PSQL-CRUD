import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel {

    String login;
    String password;
    String dbName;
    String hostName;

    JFrame frame;
    JPanel menu = new JPanel();

    JButton addButton = new JButton("Add record");
    JButton editButton = new JButton("Edit record");
    JButton exportButton = new JButton("Export to HTML");
    JButton exitButton = new JButton("Exit");


    public MenuPanel(String login, String password, String dbName, String hostName) {

        this.login = login;
        this.password = password;
        this.dbName = dbName;
        this.hostName = hostName;

        frame = new JFrame();
        frame.setTitle("APB PSQL CRUD Project");
        frame.setSize(500, 300);
        frame.setVisible(true);

        Container cp = frame.getContentPane();
        cp.setLayout(new GridLayout(1, 2));
        cp.add(menu);

        menu.setLayout(new GridLayout(6,1));

        menu.add(addButton);
        menu.add(editButton);
        menu.add(exportButton);
        menu.add(exitButton);

        addButton.addActionListener(new AddRecord());
        editButton.addActionListener(new EditRecord());
        exportButton.addActionListener(new ExportRecord());
        exitButton.addActionListener(new Exit());
    }

    class AddRecord implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            new AddPanel(login, password, dbName, hostName).frame.setVisible(true);
        }
    }

    class EditRecord implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            frame.setVisible(false);
            new EditMenuPanel(login, password, dbName, hostName).frame.setVisible(true);
        }
    }

    class ExportRecord implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        Export html = new Export(login, password, dbName, hostName);
        html.ExportHTML();
        }
    }

    class Exit implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}