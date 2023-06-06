package com.alexquispe.utp.trabajofinal;

import javax.swing.*;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class App extends JFrame {

    // Persona
    JTextField textBoxNombre;
    JTextField textBoxDni;
    JTextField textBoxEdad;
    JButton buttonSubmit;
    JTable tablaPersonas;
    PersonTableModel personTableModel;
    List<Person> personList = new ArrayList<>();
    // General
    private JFrame mainFrame;
    private JPanel controlPanel;

    public static void main(String[] args) {
        App app = new App();
        app.openWindow();
        app.showFormWindow();
    }

    public void openWindow() {
        mainFrame = new JFrame("My App");
        mainFrame.setSize(700, 700);
        mainFrame.setLayout(new FlowLayout());

        // Alinear al centro
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setLocation(dim.width / 2 - mainFrame.getSize().width / 2, dim.height / 2 - mainFrame.getSize().height / 2);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
    }

    private void showFormWindow() {

        final JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new GridLayout(2, 1));

        final JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new GridLayout(12, 1));
        final JPanel panelSecundario = new JPanel();
        panelSecundario.setLayout(new GridLayout(2, 1));

        // Panel header titulo
        JPanel panelHeader = new JPanel();
        JLabel headerLabel = new JLabel("CRUD PERSONAS", JLabel.CENTER);
        panelHeader.add(headerLabel);
        panelHeader.setLayout(new GridLayout(1, 1));
        panelPrincipal.add("panelHeader", panelHeader);

        // Panel input nombre
        JPanel panelNombre = new JPanel();
        panelNombre.add(new JLabel("Nombre:"));
        textBoxNombre = new JTextField();
        textBoxNombre.setColumns(20);
        panelNombre.add(textBoxNombre);
        panelNombre.setLayout(new GridLayout(1, 2));
        panelPrincipal.add("panelNombre", panelNombre);

        // Panel input dni
        JPanel panelDni = new JPanel();
        panelDni.add(new JLabel("DNI:"));
        textBoxDni = new JTextField();
        textBoxDni.setColumns(20);
        panelDni.add(textBoxDni);
        panelDni.setLayout(new GridLayout(1, 2));
        panelPrincipal.add("panelDni", panelDni);

        // Panel input edad
        JPanel panelEdad = new JPanel();
        panelEdad.add(new JLabel("Edad:"));
        textBoxEdad = new JTextField();
        textBoxEdad.setColumns(20);
        panelEdad.add(textBoxEdad);
        panelEdad.setLayout(new GridLayout(1, 2));
        panelPrincipal.add("panelEdad", panelEdad);

        // Panel button submit
        JPanel panelSubmit = new JPanel();
        buttonSubmit = new JButton("Guardar");
        buttonSubmit.addActionListener(createPerson());
        panelSubmit.add(buttonSubmit);
        panelSubmit.setLayout(new GridLayout(1, 2));
        panelPrincipal.add("panelSubmit", panelSubmit);

        // Panel table list
        JPanel panelPersonas = new JPanel();
        DefaultTableColumnModel columnModel = new DefaultTableColumnModel();
        columnModel.addColumn(createColumn("Nombre", 0, 100));
        columnModel.addColumn(createColumn("DNI", 1, 100));
        columnModel.addColumn(createColumn("Edad", 2, 100));
        tablaPersonas = new JTable(personTableModel, columnModel);
        JScrollPane scrollPane = new JScrollPane(tablaPersonas);
        scrollPane.setPreferredSize(new Dimension(100, 200));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panelSecundario.add(panel);
        panelSecundario.add("panelPersonas", panelPersonas);

        // Paneles
        panelGeneral.add(panelPrincipal);
        panelGeneral.add(panelSecundario);
        controlPanel.add(panelGeneral);

        mainFrame.setVisible(true);
    }

    private TableColumn createColumn(String name, int index, int width) {
        TableColumn column = new TableColumn(index);
        column.setHeaderValue(name);
        column.setPreferredWidth(width);
        return column;
    }

    private ActionListener createPerson() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("[App.createPerson.actionPerformed]");

                // Set model
                Person person = new Person();
                person.setNombre(textBoxNombre.getText());
                person.setDni(textBoxDni.getText());
                person.setEdad(Integer.parseInt(textBoxEdad.getText()));

                // Agregar a la lista
                personList.add(person);

                // Agregar a la tabla
                personTableModel = new PersonTableModel(personList);
                tablaPersonas.setModel(personTableModel);

                // Resetear formulario
                resetForm();
            }

            public void resetForm() {
                textBoxNombre.setText("");
                textBoxDni.setText("");
                textBoxEdad.setText("");
                textBoxNombre.requestFocus();
            }
        };
    }

}
