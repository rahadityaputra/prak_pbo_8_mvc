package Model.Dosen;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.List;
/**
 *
 * @author rahadityaputra
 */
public interface InterfaceDAODosen {
    // Method untuk memasukkan suatu data mahasiswa
    public void insert(ModelDosen dosen);
    
    // Method untuk mengupdate (mengedit) suatu data mahasiswa
    public void update(ModelDosen dosen);
    
    // Method untuk menghapus suatu data mahasiswa
    public void delete(int id);
    
    // Method untuk mengambil data mahasiswa
    public List<ModelDosen> getAll();
}

