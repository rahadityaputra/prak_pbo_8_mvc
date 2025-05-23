package View;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import Controller.ControllerDosen;
import Controller.ControllerMahasiswa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewHome extends JFrame {
   JLabel header = new JLabel("Selamat Datang di Database UPNVYK");
   JLabel label = new JLabel("Pilih Menu");
   JButton buttonMahasiswa = new JButton("Mahasiswa");
   JButton buttonDosen = new JButton("Dosen");


   public ViewHome() {
        super("Home");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLayout(null);
        this.add(header);
        this.add(label);
        this.add(buttonMahasiswa);
        this.add(buttonDosen);
        setVisible(true);
        header.setBounds(100, 50, 300, 30);
        label.setBounds(100, 100, 300, 30);
        buttonMahasiswa.setBounds(100, 150, 100, 30);
        buttonDosen.setBounds(250, 150, 100, 30);
        buttonMahasiswa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerMahasiswa controller = new ControllerMahasiswa();
                new ViewData<>(controller);
                dispose();
            }
        });


        buttonDosen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControllerDosen controller = new ControllerDosen();
                new ViewData<>(controller);
                dispose();
            }
        });
   }

}
