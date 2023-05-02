package com.alexquispe.utp.trabajofinal;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PersonTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {"Nombre", "DNI", "Edad"};
    private List<Person> personList;
    String[] columnNames;

    public PersonTableModel(List<Person> personList) {
        this.personList = personList;
    }


    public String[] setColumnNames(String[] columnNames) {
        return this.columnNames = columnNames;
    }

    public static String[] getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    public int getRowCount() {
        return personList.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person person = personList.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return person.getNombre();
            case 1:
                return person.getDni();
            case 2:
                return person.getEdad();
        }
        return null;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
                return String.class;
            case 2:
                return Integer.class;
        }
        return null;
    }

    public void addPerson(Person person) {
        personList.add(person);
        fireTableRowsInserted(personList.size() - 1, personList.size() - 1);
    }

    public void removePerson(int rowIndex) {
        personList.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
