package Controller;

import Model.Mahasiswa.*;
import View.*;
import java.util.List;
import javax.swing.JOptionPane;
import Model.PersonTableModel;

public class ControllerMahasiswa implements CrudController<Mahasiswa> {
    DAOMahasiswa dms;
    public PersonTableModel<Mahasiswa> model;

    List<Mahasiswa> daftarMahasiswa;
    
    public ControllerMahasiswa() {
        this.dms = new DAOMahasiswa();
    }

    @Override
    public void getAllData() {
        daftarMahasiswa = dms.getAll();
    }

    @Override
    public PersonTableModel<Mahasiswa> getTableModel() {
        getAllData();
        return new PersonTableModel<Mahasiswa>(daftarMahasiswa);
    }

    @Override
    public void add() {
        try {
            InputData<Mahasiswa> inputDataMahasiswa = new InputData<>(this, (nama, nim) -> {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNama(nama);
                mahasiswa.setNIMorNIDN(nim);
                return mahasiswa;
            });


            inputDataMahasiswa.setOnAdd(mahasiswa -> {
                dms.insert(mahasiswa);
                JOptionPane.showMessageDialog(null, "Mahasiswa baru berhasil ditambahkan.");
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    @Override
    public void edit(int id, String nama, String nimOrNidn) {
        try {
            EditData<Mahasiswa> editDataMahasiswa = new EditData<>(this, nama, nimOrNidn,(namaBaru, nimOrNidnBaru) -> {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setId(id);
                mahasiswa.setNama(namaBaru);
                mahasiswa.setNIMorNIDN(nimOrNidnBaru);
                return mahasiswa;
            });

            editDataMahasiswa.setOnEdit(mahasiswa -> {
                dms.update(mahasiswa);
                JOptionPane.showMessageDialog(null, "Mahasiswa baru berhasil ditambahkan.");
            });
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    @Override
    public void delete(int id, String nama) {
        int input = JOptionPane.showConfirmDialog(
                null,
                "Hapus " + nama + "?",
                "Hapus Mahasiswa",
                JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {
            dms.delete(id);
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");
        }
    }
}
