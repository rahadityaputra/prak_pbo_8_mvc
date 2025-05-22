package Controller;

import Model.Mahasiswa.*;
import View.*;
import java.util.List;
import javax.swing.JOptionPane;
import Model.PersonTableModel;

public class ControllerMahasiswa implements CrudController<Mahasiswa> {
    DAOMahasiswa dms;
    public PersonTableModel<Mahasiswa> model;

    // Membuat varkkiabel "daftarMahasiswa" untuk menyimpan data mahasiswa yg diambil dari DB.
    List<Mahasiswa> daftarMahasiswa;
    
    
    /*
      Kalo temen-temen liat di sini, ada 3 fungsi contructor yang masing-masing memiliki
      parameter yang berbeda. Nah, hal ini disebut dengan "function overloading",
      yaitu sebuah fungsi yang memiliki nama sama tetapi memiliki parameter yang berbeda.
      
      Mengapa kita butuh "function overloading"?
      Karena dalam hal ini, controller mahasiswa akan digunakan pada 3 halaman atau 3 view yang berbeda, 
      yaitu Halaman Table, Halaman Input, dan Halaman Edit.
    */
    public ControllerMahasiswa() {
        this.dms = new DAOMahasiswa();
        showAllData();
    }

    @Override
    public void showAllData() {
        daftarMahasiswa = dms.getAll();
    }

    @Override
    public PersonTableModel<Mahasiswa> getTableModel() {
        return new PersonTableModel<Mahasiswa>(daftarMahasiswa);
    }


    public void showInputData() {

    }
    @Override
    public void add() {
        try {
            InputData<Mahasiswa> inputDataMahasiswa = new InputData<>((nama, nim) -> {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNama(nama);
                mahasiswa.setNIMorNIDN(nim);
                return mahasiswa;
            });


            inputDataMahasiswa.setOnAdd(mahasiswa -> {
                dms.insert(mahasiswa);
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
            EditData<Mahasiswa> editDataMahasiswa = new EditData<>(nama, nimOrNidn,(namaBaru, nimOrNidnBaru) -> {
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
            
            //halamanEdit.dispose();
            //new ViewData();
        } catch (Exception e) {
            // Menampilkan pop-up ketika terjadi error
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

            //showAllMahasiswa();
        }
    }
}
