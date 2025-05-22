package Model.Mahasiswa;

import Model.Identitas;

public class Mahasiswa implements Identitas {
    private Integer id;
    private String nama, nim;
    
    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getNama() {
        return nama;
    }

    @Override
    public void setNama(String nama) {
        this.nama = nama;
    }

    @Override
    public String getNIMorNIDN() {
        return nim;
    }

    @Override
    public void setNIMorNIDN(String nim) {
        this.nim = nim;
    }
}

