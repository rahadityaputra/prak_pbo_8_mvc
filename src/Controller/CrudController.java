package Controller;

import Model.Identitas;
import Model.PersonTableModel;

public interface CrudController<T extends Identitas>{
    PersonTableModel<T> getTableModel();
    void add();
    void edit(int id, String nama, String nimOrNidn);
    void delete(int id, String nama);
    void getAllData();
}
