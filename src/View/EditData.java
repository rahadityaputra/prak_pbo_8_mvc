package View;

import Model.Identitas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import javax.swing.*;

public class EditData<T extends Identitas> extends JFrame {
    Consumer<T> onEdit;
    JLabel header = new JLabel("Edit Mahasiswa");
    JLabel labelInputNama = new JLabel("Nama");
    JLabel labelInputNIM = new JLabel("NIM");
    JTextField inputNama = new JTextField();
    JTextField inputNIMorNIDN = new JTextField();
    JButton tombolEdit = new JButton("Edit Mahasiswa");
    JButton tombolKembali = new JButton("Kembali");

    public EditData(String nama, String nimOrNidn, BiFunction<String, String, T> builder) {
        setTitle("Edit Mahasiswa");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setSize(480, 240);

        add(header);
        add(labelInputNama);
        add(labelInputNIM);
        add(inputNama);
        add(inputNIMorNIDN);
        add(tombolEdit);
        add(tombolKembali);

        header.setBounds(20, 8, 440, 24);
        labelInputNama.setBounds(20, 32, 440, 24);
        inputNama.setBounds(18, 56, 440, 36);
        labelInputNIM.setBounds(20, 96, 440, 24);
        inputNIMorNIDN.setBounds(18, 120, 440, 36);
        tombolKembali.setBounds(20, 160, 215, 40);
        tombolEdit.setBounds(240, 160, 215, 40);
        

        /* 
          Memberikan event handling untuk tombol kembali,
          Ketika tombol kembali diklik, maka akan kembali ke halaman ViewData().
         */
        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        tombolEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nama = inputNama.getText();
                    String nimOrNidn = inputNIMorNIDN.getText();
                    if ("".equals(nama) || "".equals(nimOrNidn)) {
                        throw new Exception("Nama atau NIM/NIDN tidak boleh kosong!");
                    }
                    T data = builder.apply(nama, nimOrNidn);
                    onEdit.accept(data);
                    
                } catch (Exception error) {
                    System.out.print(error.getLocalizedMessage());
                }
            }
        });
    }
    public void setOnEdit(Consumer<T> callback) {
        onEdit = callback;
    }
}
