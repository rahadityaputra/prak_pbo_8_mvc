package Model.Mahasiswa;

import Model.Connector;
import Model.GenericInterfaceDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOMahasiswa implements GenericInterfaceDAO<Mahasiswa, Integer> {

    @Override
    public void insert(Mahasiswa mahasiswa) {
       try {
            String query = "INSERT INTO mahasiswa (nama, nim) VALUES (?, ?);";
            Connector.executeUpdate(query, mahasiswa.getNama(), mahasiswa.getNIMorNIDN());
        } catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        } 
    }

    @Override
    public void update(Mahasiswa mahasiswa) {
        try {
            String query = "UPDATE mahasiswa SET nama=?, nim=? WHERE id=?;";
            Connector.executeUpdate(query, mahasiswa.getNama(), mahasiswa.getNIMorNIDN(), mahasiswa.getId());
        } catch (SQLException e) {
            System.out.println("update Failed! (" + e.getMessage() + ")");
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String query = "DELETE FROM mahasiswa WHERE id=?;";
            Connector.executeUpdate(query, id);
        } catch (SQLException e) {
            // Menampilkan pesan error ketika gagal hapus data.
            System.out.println("Delete Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public List<Mahasiswa> getAll() {
         List<Mahasiswa> listMahasiswa = null;

        try {
            listMahasiswa = new ArrayList<>();
            String query = "SELECT * FROM mahasiswa;";
            listMahasiswa = Connector.executeQuery(query, Mahasiswa::new);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listMahasiswa;
    }
}
