/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author rafli
 */
public class PersonTableModel<T extends Identitas> extends AbstractTableModel {

    private final List<T> data;
    private final String[] kolom = {"ID", "Nama", "NIM/NIDN"};
    public PersonTableModel(List<T> daftar) {
        this.data = daftar;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return kolom.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        int ci = columnIndex;
        switch (ci) {
            case 0:
                return data.get(rowIndex).getId();
            case 1:
                return data.get(rowIndex).getNama();
            case 2:
                return data.get(rowIndex).getNIMorNIDN();
            default:
                return null;
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        return kolom[columnIndex];
    }
}
