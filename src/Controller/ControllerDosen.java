package Controller;

import Model.Dosen.*;
import View.*;
import java.util.List;
import javax.swing.JOptionPane;
import Model.PersonTableModel;

public class ControllerDosen implements CrudController<Dosen> {
    DAODosen dsn;

    List<Dosen> daftarDosen;
    public ControllerDosen() {
        this.dsn = new DAODosen();
    }

    @Override
    public PersonTableModel<Dosen> getTableModel() {
        getAllData();
        return new PersonTableModel<Dosen>(daftarDosen);
    }

    @Override
    public void getAllData() {
        daftarDosen = dsn.getAll();
    }

    @Override
    public void add() {
        try {
            InputData<Dosen> inputDataDosen = new InputData<>(this, (nama, nidn) -> {
                Dosen dosen = new Dosen();
                dosen.setNama(nama);
                dosen.setNIMorNIDN(nidn);
                return dosen;
            });


            inputDataDosen.setOnAdd(dosen -> {
                dsn.insert(dosen);
                JOptionPane.showMessageDialog(null, "Mahasiswa baru berhasil ditambahkan.");
                // halamanInput.dispose();
                //new ViewData();
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
    @Override
    public void edit(int id, String nama, String nimOrNidn) {
        try {
            EditData<Dosen> editDataDosen = new EditData<>(this, nama, nimOrNidn,(namaBaru, nimOrNidnBaru) -> {
                Dosen dosen = new Dosen();
                dosen.setId(id);
                dosen.setNama(namaBaru);
                dosen.setNIMorNIDN(nimOrNidnBaru);
                return dosen;
            });

            editDataDosen.setOnEdit(mahasiswa -> {
                dsn.update(mahasiswa);
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
                "Hapus Dosen",
                JOptionPane.YES_NO_OPTION
        );

        if (input == 0) {
            dsn.delete(id);
            JOptionPane.showMessageDialog(null, "Berhasil menghapus data.");
            //showAllMahasiswa();
        }
    }
}
