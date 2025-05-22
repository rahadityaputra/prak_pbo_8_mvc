package Model.Dosen;

import Model.Connector;
import Model.GenericInterfaceDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAODosen implements GenericInterfaceDAO<Dosen, Integer> {
    @Override
    public void insert(Dosen dosen) {
       try {
            String query = "INSERT INTO dosen (nama, nidn) VALUES (?, ?);";
            Connector.executeUpdate(query, dosen.getNama(), dosen.getNIMorNIDN());
        } catch (SQLException e) {
            System.out.println("Input Failed: " + e.getLocalizedMessage());
        } 
    }

    @Override
    public void update(Dosen dosen) {
        try {
            String query = "UPDATE dosen SET nama=?, nidn=? WHERE id=?;";
            Connector.executeUpdate(query, dosen.getNama(), dosen.getId());
        } catch (SQLException e) {
            // Menampilkan pesan error ketika gagal edit data.
            System.out.println("update Failed! (" + e.getMessage() + ")");
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            String query = "DELETE FROM dosen WHERE id=?;";
            Connector.executeUpdate(query, id);
        } catch (SQLException e) {
            System.out.println("Delete Failed: " + e.getLocalizedMessage());
        }
    }

    @Override
    public List<Dosen> getAll() {
         List<Dosen> listDosen = null;

        try {
            listDosen = new ArrayList<>();
            String query = "SELECT * FROM dosen;";
            ResultSet resultSet = Connector.executeQuery(query);
            while (resultSet.next()) {
                Dosen dsn = new Dosen();
                dsn.setId(resultSet.getInt("id"));
                dsn.setNama(resultSet.getString("nama"));
                dsn.setNIMorNIDN(resultSet.getString("nidn"));
                listDosen.add(dsn);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getLocalizedMessage());
        }
        return listDosen;
    }
}

