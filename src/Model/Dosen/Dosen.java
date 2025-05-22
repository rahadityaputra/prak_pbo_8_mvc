package Model.Dosen;

import Model.Identitas;

public class Dosen implements Identitas{
    private Integer id;
    private String nama, nidn;
    
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
        return nidn;
    }

    @Override
    public void setNIMorNIDN(String nidn) {
        this.nidn = nidn;
    }
}

