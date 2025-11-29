import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    static ArrayList<Barang> daftarBarang = new ArrayList<>();
    static ArrayList<Barang_Masuk_Keluar> daftarBarangMasukKeluar = new ArrayList<>();
    static ArrayList<Jenis_Barang> daftarJenisBarang = new ArrayList<>();
    static int nextId = 1;
    static int nextIdmsk = 1;
    static int kodeDasar = 1000;

    public static void main(String[] args) {
        // Tambahkan contoh jenis barang
        daftarJenisBarang.add(new Jenis_Barang(1, 1, "Kebutuhan Pokok", "Barang-barang kebutuhan sehari-hari kantor"));
        daftarJenisBarang.add(new Jenis_Barang(2, 1, "Peralatan Kantor", "Barang elektronik dan perlengkapan kantor"));
        daftarJenisBarang.add(new Jenis_Barang(3, 2, "Furniture", "Meja, kursi, lemari kantor"));
        daftarJenisBarang.add(new Jenis_Barang(4, 2, "ATK", "Alat Tulis Kantor"));
        daftarJenisBarang.add(new Jenis_Barang(5, 3, "Elektronik Lainnya", "Printer, scanner, proyektor"));

        Scanner sc = new Scanner(System.in);
        Main app = new Main();
        app.Menu(sc);
    }

    // ============================================================
    // MENU UTAMA
    // ============================================================
    public void Menu(Scanner sc) {
        while (true) {
            System.out.println("=========== STOCKONE ============");
            System.out.println("Perlengkapan dan Aset Kantor Anda");
            System.out.println("=================================");
            System.out.println("1. Tambah Aset Barang");
            System.out.println("2. Edit Aset Barang");
            System.out.println("3. Hapus Aset Barang");
            System.out.println("4. Tampilkan Semua Aset");
            System.out.println("5. Barang Masuk");
            System.out.println("6. Barang Keluar");
            System.out.println("7. Searching Barang");
            System.out.println("8. Laporan Aset");
            System.out.println("9. History Barang Masuk/Keluar");
            System.out.println("10. Keluar");
            System.out.println("=================================");

            Integer pilihan = inputInt(sc, "Pilih menu");
            if (pilihan == null)
                continue;

            switch (pilihan) {
                case 1 -> tambahBarang(sc);
                case 2 -> editBarang(sc);
                case 3 -> HapusBarang(sc);
                case 4 -> tampilkanInfo();
                case 5 -> BarangMasuk(sc);
                case 6 -> BarangKeluar(sc);
                case 7 -> SearchingBarang(sc);
                case 8 -> Laporan(sc);
                case 9 -> tampilkanHistory();
                case 10 -> {
                    System.out.println("Keluar program...");
                    return;
                }
                default -> System.out.println("Pilihan tidak valid!");
            }
        }
    }

    // ============================================================
    // INPUT HELPER
    // ============================================================
    public String inputString(Scanner sc, String prompt) {
        System.out.print(prompt + " (ketik 'b' untuk kembali): ");
        String input = sc.nextLine().trim();
        if (input.equalsIgnoreCase("b"))
            return null;
        if (!input.matches("[a-zA-Z0-9\\s\\-_/]+")) {
            System.out.println("Input mengandung karakter tidak diperbolehkan!");
            return inputString(sc, prompt);
        }
        return input;
    }

    public Integer inputInt(Scanner sc, String prompt) {
        String input = inputString(sc, prompt);
        if (input == null)
            return null;
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid! Masukkan angka.");
            return inputInt(sc, prompt);
        }
    }

    // ============================================================
    // TAMBAH BARANG
    // ============================================================
    public void tambahBarang(Scanner sc) {
        System.out.println("\n========== Tambah Aset Barang ==========");

        String nama = inputString(sc, "Nama Barang");
        if (nama == null)
            return;

        Integer harga = inputInt(sc, "Harga");
        if (harga == null)
            return;

        Integer jumlah = inputInt(sc, "Jumlah Masuk");
        if (jumlah == null)
            return;

        String keterangan = inputString(sc, "Keterangan");
        if (keterangan == null)
            return;

        // PILIH JENIS BARANG
        System.out.println("\n=== Pilih Jenis Barang ===");
        for (Jenis_Barang jb : daftarJenisBarang) {
            System.out.println(jb.getId_Jenis_Barang() + ". " + jb.getNama_Jenis_Barang());
        }
        Integer idJenis = inputInt(sc, "Masukkan ID Jenis Barang");
        Jenis_Barang jenisTerpilih = null;
        for (Jenis_Barang jb : daftarJenisBarang) {
            if (jb.getId_Jenis_Barang() == idJenis) {
                jenisTerpilih = jb;
                break;
            }
        }
        if (jenisTerpilih == null) {
            System.out.println("Jenis Barang tidak ditemukan!");
            return;
        }

        // Satuan
        String unit = inputString(sc, "Satuan unit (pcs/box/kg/liter/meter/pack/roll/set)");
        if (unit == null)
            return;
        Satuan satuan = new Satuan(nextId, unit, null);

        int kode = kodeDasar + nextId;

        Barang b = new Barang(nextId, satuan, kode, nama, jumlah, harga, jumlah, keterangan, LocalDate.now());
        b.setJenisBarang(jenisTerpilih);
        daftarBarang.add(b);

        System.out.println("\nAset Barang berhasil ditambahkan dengan ID " + nextId);
        nextId++;
    }

    // ============================================================
    // EDIT BARANG
    // ============================================================
    public void editBarang(Scanner sc) {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada data barang untuk diedit.");
            return;
        }

        tampilkanInfo();
        Integer id_edit = inputInt(sc, "Masukkan ID Barang yang ingin diedit");
        if (id_edit == null)
            return;

        Barang bmk = getBarangById(id_edit);
        if (bmk == null) {
            System.out.println("ID Barang tidak ditemukan.");
            return;
        }

        while (true) {
            System.out.println(
                    "\n1. Nama Barang\n2. Harga\n3. Jumlah Stok\n4. Keterangan\n5. Satuan\n6. Jenis Barang\n7. Kembali");
            Integer edit_data = inputInt(sc, "Pilih data yang ingin diedit");
            if (edit_data == null || edit_data == 7)
                return;

            switch (edit_data) {
                case 1 -> {
                    String n = inputString(sc, "Nama Baru");
                    if (n != null)
                        bmk.setNama_barang(n);
                }
                case 2 -> {
                    Integer h = inputInt(sc, "Harga Baru");
                    if (h != null)
                        bmk.setHarga_barang(h);
                }
                case 3 -> {
                    Integer s = inputInt(sc, "Jumlah Stok Baru");
                    if (s != null)
                        bmk.setStock(s);
                }
                case 4 -> {
                    String k = inputString(sc, "Keterangan Baru");
                    if (k != null)
                        bmk.setKeterangan(k);
                }
                case 5 -> {
                    String u = inputString(sc, "Satuan Baru");
                    if (u != null)
                        bmk.id_satuan.setNama_Satuan(u);
                }
                case 6 -> {
                    System.out.println("\n=== Pilih Jenis Barang ===");
                    for (Jenis_Barang jb : daftarJenisBarang) {
                        System.out.println(jb.getId_Jenis_Barang() + ". " + jb.getNama_Jenis_Barang());
                    }
                    Integer idJ = inputInt(sc, "Masukkan ID Jenis Barang");
                    for (Jenis_Barang jb : daftarJenisBarang) {
                        if (jb.getId_Jenis_Barang() == idJ) {
                            bmk.setJenisBarang(jb);
                            break;
                        }
                    }
                }
            }
            System.out.println("Data berhasil diupdate.");
        }
    }

    // ============================================================
    // HAPUS BARANG
    // ============================================================
    public void HapusBarang(Scanner sc) {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada data barang.");
            return;
        }
        tampilkanInfo();
        Integer id_hapus = inputInt(sc, "Masukkan ID Barang yang ingin dihapus");
        if (id_hapus == null)
            return;

        Barang bmk = getBarangById(id_hapus);
        if (bmk != null) {
            daftarBarang.remove(bmk);
            System.out.println("Aset Barang berhasil dihapus.");
        } else
            System.out.println("ID Barang tidak ditemukan.");
    }

    // ============================================================
    // TAMPILKAN DATA ASET
    // ============================================================
    public void tampilkanInfo() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada data barang.");
            return;
        }

        System.out.printf("%-5s %-10s %-10s %-20s %-20s %-8s %-10s %-30s %-15s%n",
                "ID", "Satuan", "Kode", "Jenis", "Nama", "Stok", "Harga", "Keterangan", "Tanggal");
        System.out.println(
                "--------------------------------------------------------------------------------------------------");

        for (Barang bmk : daftarBarang) {
            System.out.printf("%-5d %-10s %-10d %-20s %-20s %-8d %-10.2f %-30s %-15s%n",
                    bmk.getId_barang(),
                    bmk.getId_satuan().getNama_Satuan(),
                    bmk.getKode_barang(),
                    bmk.getJenisBarang().getNama_Jenis_Barang(),
                    bmk.getNama_barang(),
                    bmk.getStock(),
                    bmk.getHarga_barang(),
                    bmk.getKeterangan(),
                    bmk.getCreated_at());
        }
    }

    // ============================================================
    // BARANG MASUK
    // ============================================================
    public void BarangMasuk(Scanner sc) {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada data barang.");
            return;
        }

        Integer id_barang = inputInt(sc, "Masukkan ID Barang");
        if (id_barang == null)
            return;

        Barang barang = getBarangById(id_barang);
        if (barang == null) {
            System.out.println("ID Barang tidak ditemukan.");
            return;
        }

        String serial;
        while (true) {
            serial = inputString(sc, "Masukkan Serial");
            if (serial == null)
                return;
            if (!isSerialValid(serial)) {
                System.out.println("Serial sudah ada atau tidak valid, coba lagi!");
                continue;
            }
            break;
        }

        String kondisi = inputString(sc, "Masukkan Kondisi (Baik/Buruk)");
        if (kondisi == null)
            return;

        if (!kondisi.equalsIgnoreCase("Baik") && !kondisi.equalsIgnoreCase("Buruk")) {
            System.out.println("Pilihan kondisi salah!");
            return;
        }

        String keterangan = inputString(sc, "Masukkan Keterangan");
        if (keterangan == null)
            return;

        Barang_Masuk_Keluar bm = new Barang_Masuk_Keluar(nextIdmsk, barang, barang.getStock(), serial, kondisi,
                keterangan, LocalDate.now(), null, null);
        daftarBarangMasukKeluar.add(bm);
        nextIdmsk++;
        System.out.println("Barang berhasil dicatat sebagai MASUK.");
    }

    public boolean isSerialValid(String serial) {
        if (serial.isBlank())
            return false;
        for (Barang_Masuk_Keluar bm : daftarBarangMasukKeluar) {
            if (bm.getSerial().equalsIgnoreCase(serial))
                return false;
        }
        return true;
    }

    // ============================================================
    // BARANG KELUAR
    // ============================================================
    public void BarangKeluar(Scanner sc) {
        if (daftarBarangMasukKeluar.isEmpty()) {
            System.out.println("Belum ada data barang masuk.");
            return;
        }

        Integer id = inputInt(sc, "Masukkan ID Barang Masuk");
        if (id == null)
            return;

        Barang_Masuk_Keluar data = null;
        for (Barang_Masuk_Keluar bm : daftarBarangMasukKeluar) {
            if (bm.getId_barang_masuk() == id) {
                data = bm;
                break;
            }
        }
        if (data == null) {
            System.out.println("ID Barang Masuk tidak ditemukan.");
            return;
        }
        if (data.getTanggal_keluar() != null) {
            System.out.println("Barang sudah keluar!");
            return;
        }

        String ketKeluar = inputString(sc, "Masukkan keterangan keluar");
        if (ketKeluar == null)
            return;

        data.setKet_Keluar(ketKeluar);
        data.setTanggal_keluar(LocalDate.now());
        System.out.println("Barang berhasil dicatat sebagai KELUAR.");
    }

    // ============================================================
    // SEARCHING
    // ============================================================
    public void SearchingBarang(Scanner sc) {
        String key = inputString(sc, "Masukkan nama atau kode");
        if (key == null)
            return;

        boolean ditemukan = false;
        for (Barang b : daftarBarang) {
            if (b.getNama_barang().toLowerCase().contains(key.toLowerCase()) ||
                    String.valueOf(b.getKode_barang()).equals(key)) {
                System.out.println(
                        "ID: " + b.getId_barang() + " Nama: " + b.getNama_barang() + " Kode: " + b.getKode_barang());
                ditemukan = true;
            }
        }
        if (!ditemukan)
            System.out.println("Barang tidak ditemukan.");
    }

    // ============================================================
    // LAPORAN
    // ============================================================
    public void Laporan(Scanner sc) {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada barang.");
            return;
        }

        int totalBarang = 0;
        float totalNilai = 0;

        System.out.println("\nJenis Barang    Jumlah    Nilai");
        for (Barang b : daftarBarang) {
            float nilai = b.getStock() * b.getHarga_barang();
            System.out.printf("%-15s %-8d Rp %, .0f%n",
                    b.getJenisBarang().getNama_Jenis_Barang() + " - " + b.getNama_barang(),
                    b.getStock(), nilai);
            totalBarang += b.getStock();
            totalNilai += nilai;
        }

        System.out.println("\nTotal Jenis Barang : " + daftarBarang.size());
        System.out.println("Total Item Barang  : " + totalBarang);
        System.out.println("Total Nilai Aset   : Rp " + String.format("%,.0f", totalNilai));
    }

    // ============================================================
    // HISTORY BARANG MASUK/KELUAR
    // ============================================================
    public void tampilkanHistory() {
        if (daftarBarangMasukKeluar.isEmpty()) {
            System.out.println("Belum ada riwayat barang masuk/keluar.");
            return;
        }

        System.out.printf("%-5s %-20s %-15s %-10s %-15s %-30s%n",
                "ID", "Nama Barang", "Serial", "Kondisi", "Tanggal Masuk", "Tanggal Keluar / Keterangan");
        System.out
                .println("------------------------------------------------------------------------------------------");

        for (Barang_Masuk_Keluar bm : daftarBarangMasukKeluar) {
            String namaBarang = bm.getId_barang() != null ? bm.getId_barang().getNama_barang() : "-";
            String tglKeluar = bm.getTanggal_keluar() != null
                    ? bm.getTanggal_keluar().toString() + " (" + bm.getKet_Keluar() + ")"
                    : "-";
            System.out.printf("%-5d %-20s %-15s %-10s %-15s %-30s%n",
                    bm.getId_barang_masuk(),
                    namaBarang,
                    bm.getSerial(),
                    bm.getKondisi(),
                    bm.getTanggal_masuk(),
                    tglKeluar);
        }

    }

    // ============================================================
    // HELPER GET BY ID
    // ============================================================
    public Barang getBarangById(int id) {
        for (Barang b : daftarBarang) {
            if (b.getId_barang() == id)
                return b;
        }
        return null;
    }
}
