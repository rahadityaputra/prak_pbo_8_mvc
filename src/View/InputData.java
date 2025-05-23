package View;

import Model.Identitas;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.function.Consumer;
import java.util.function.BiFunction;

import javax.swing.*;

import Controller.CrudController;

public class InputData<T extends Identitas> extends JFrame {
    private Consumer<T> onAdd;
    private final CrudController<T> controller;

    JLabel header = new JLabel("Input Mahasiswa");
    JLabel labelInputNama = new JLabel("Nama");
    JLabel labelInputNIM = new JLabel("NIM");
    JTextField inputNama = new JTextField();
    JTextField inputNIM = new JTextField();
    JButton tombolTambah = new JButton("Tambah Mahasiswa");
    JButton tombolKembali = new JButton("Kembali");

    public InputData(CrudController<T> controller, BiFunction<String, String, T> builder) {
        this.controller = controller;
        setTitle("Input Mahasiswa");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setSize(480, 240);

        add(header);
        add(labelInputNama);
        add(labelInputNIM);
        add(inputNama);
        add(inputNIM);
        add(tombolTambah);
        add(tombolKembali);

        header.setBounds(20, 8, 440, 24);
        labelInputNama.setBounds(20, 32, 440, 24);
        inputNama.setBounds(18, 56, 440, 36);
        labelInputNIM.setBounds(20, 96, 440, 24);
        inputNIM.setBounds(18, 120, 440, 36);
        tombolKembali.setBounds(20, 160, 215, 40);
        tombolTambah.setBounds(240, 160, 215, 40);

        tombolKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ViewData<>(InputData.this.controller);
                dispose();
            }
        });
        tombolTambah.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nama = inputNama.getText();
                    String nim = inputNIM.getText();
                    if ("".equals(nama) || "".equals(nim)) {
                        throw new Exception("Nama atau NIM/NIDN tidak boleh kosong!");
                    }
                    T data = builder.apply(nama, nim);
                    onAdd.accept(data);
                    new ViewData<>(InputData.this.controller);
                    dispose();
                    
                    
                } catch (Exception error) {
                    System.out.print(error.getLocalizedMessage());
                }
            }
        });
    }

    public void setOnAdd(Consumer<T> callback) {
        onAdd = callback;
    }
}
