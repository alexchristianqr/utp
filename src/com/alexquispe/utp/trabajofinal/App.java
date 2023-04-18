package com.alexquispe.utp.trabajofinal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JLabel msglabel;


    public static void main(String[] args) {
        App app = new App();
        app.openWindow();
        app.showFormWindow();
    }

    public void openWindow() {
        mainFrame = new JFrame("App Crud Persona");
        mainFrame.setSize(400, 700);
        mainFrame.setLayout(new FlowLayout());

        // Alinear al centro
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setLocation(dim.width / 2 - mainFrame.getSize().width / 2, dim.height / 2 - mainFrame.getSize().height / 2);

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void showFormWindow() {
        headerLabel.setText("-- Gestión de personas --");

        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JPanel textBoxNombre = new JPanel();
        textBoxNombre.add(new JLabel("Name:"));
        textBoxNombre.add(new JTextField(20));
        textBoxNombre.setLayout(new GridLayout(2, 1));
        panel.add("textBoxNombre", textBoxNombre);

        JPanel textBoxDni = new JPanel();
        textBoxDni.add(new JLabel("DNI:"));
        textBoxDni.add(new JTextField(30));
        textBoxDni.setLayout(new GridLayout(2, 1));
        panel.add("textBoxDni", textBoxDni);

        JPanel textBoxEdad = new JPanel();
        textBoxEdad.add(new JLabel("Edad:"));
        textBoxEdad.add(new JTextField(30));
        textBoxEdad.setLayout(new GridLayout(2, 1));
        panel.add("textBoxEdad", textBoxEdad);

        JButton buttonSubmit = new JButton("Guardar");
        buttonSubmit.setLayout(new GridLayout(2, 1));
//        buttonSubmit.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
        buttonSubmit.addActionListener(e -> {
            App app = new App();
            app.createPerson();
        });
        panel.add("buttonSubmit", buttonSubmit);

        controlPanel.add(panel);
        mainFrame.setVisible(true);
    }

    void createPerson() {
        System.out.println("Ejecutado createPerson");
    }

}
