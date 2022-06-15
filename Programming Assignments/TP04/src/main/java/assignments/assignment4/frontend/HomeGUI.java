package assignments.assignment4.frontend;

import assignments.assignment4.backend.SistakaNG;
import assignments.assignment4.backend.pengguna.Pengguna;
import assignments.assignment4.frontend.anggota.ui.*;
import assignments.assignment4.frontend.staf.ui.*;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

// Class for the GUI Home settings
public class HomeGUI {
    private final CardLayout layout = new CardLayout();
    private final JFrame frame;
    private final JPanel mainPanel = new JPanel();
    private final Map<String, SistakaPanel> panelMap = new HashMap<>();
    private Pengguna user;

    // Constructor for HomeGUI
    public HomeGUI(JFrame frame) {
        this.frame = frame;
        mainPanel.setLayout(layout);
        initGUI();
        frame.setContentPane(mainPanel);
    }

    // Initialize the GUI Panel
    private void initGUI() {
        frame.setSize(600, 1000);
        SistakaPanel welcomePanel = new WelcomePanel(this);
        panelMap.put("welcome", welcomePanel);
        mainPanel.add(welcomePanel, "welcome");
        SistakaPanel loginPanel = new LoginPanel(this);
        panelMap.put("login", welcomePanel);
        mainPanel.add(loginPanel, "login");
        SistakaPanel anggotaPanel = new AnggotaHomePanel(this);
        panelMap.put("anggota", anggotaPanel);
        mainPanel.add(anggotaPanel, "anggota");
        SistakaPanel stafPanel = new StafHomePanel(this);
        panelMap.put("staf", stafPanel);
        mainPanel.add(stafPanel, "staf");
        SistakaPanel tambahMahasiswa = new TambahMahasiswaPanel(this);
        panelMap.put("tambahMhs", tambahMahasiswa);
        mainPanel.add(tambahMahasiswa, "tambahMhs");
        SistakaPanel tambahDosen = new TambahDosenPanel(this);
        panelMap.put("tambahDosen", tambahDosen);
        mainPanel.add(tambahDosen, "tambahDosen");
        SistakaPanel tambahKategori = new TambahKategoriPanel(this);
        panelMap.put("tambahKategori", tambahKategori);
        mainPanel.add(tambahKategori, "tambahKategori");
        SistakaPanel tambahBuku = new TambahBukuPanel(this);
        panelMap.put("tambahBuku", tambahBuku);
        mainPanel.add(tambahBuku, "tambahBuku");
        SistakaPanel hapusBuku = new HapusBukuPanel(this);
        panelMap.put("hapusBuku", hapusBuku);
        mainPanel.add(hapusBuku, "hapusBuku");
        SistakaPanel peringkat = new PeringkatPanel(this);
        panelMap.put("peringkat", peringkat);
        mainPanel.add(peringkat, "peringkat");
        SistakaPanel detailAnggota = new DetailAnggotaPanel(this);
        panelMap.put("detailAnggota", detailAnggota);
        mainPanel.add(detailAnggota, "detailAnggota");
        SistakaPanel daftarPeminjam = new DaftarPeminjamPanel(this);
        panelMap.put("daftarPeminjam", daftarPeminjam);
        mainPanel.add(daftarPeminjam, "daftarPeminjam");
        SistakaPanel peminjaman = new PeminjamanPanel(this);
        panelMap.put("peminjaman", peminjaman);
        mainPanel.add(peminjaman, "peminjaman");
        SistakaPanel pengembalian = new PengembalianPanel(this);
        panelMap.put("pengembalian", pengembalian);
        mainPanel.add(pengembalian, "pengembalian");
        SistakaPanel pembayaran = new PembayaranPanel(this);
        panelMap.put("pembayaran", pembayaran);
        mainPanel.add(pembayaran, "pembayaran");
        SistakaPanel detailUser= new DetailUserPanel(this);
        panelMap.put("detailUser", detailUser);
        mainPanel.add(detailUser, "detailUser");
    }

    // Get user
    public Pengguna getUser() {
        return user;
    }

    // Set user logged in
    public void setUser(Pengguna user) {
        SistakaNG.setPenggunaLoggedIn(user);
        this.user = user;
    }

    // Get frame
    public JFrame getFrame() {
        return frame;
    }

    // Set Panel to target
    public void setPanel(String target){
        panelMap.get(target).refresh();
        layout.show(mainPanel, target);
    }

    // Exit from program
    public void exit() {
        frame.dispose();
    }
}
