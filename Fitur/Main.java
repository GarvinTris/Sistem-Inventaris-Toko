import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    static ArrayList<Barang> daftarBarang = new ArrayList<>();
    static ArrayList<Barang_Masuk_Keluar> daftarBarangMasukKeluar = new ArrayList<>();

    public int nextId = 1;
    public int nextIdmsk = 1;
    public int kodeDasar = 1000;

    public static void main(String[] args) {
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
            System.out.println("9. Keluar");
            System.out.println("=================================");

            Integer pilihan = sc.nextInt();
            sc.nextLine();

            switch (pilihan) {
                case 1 -> tambahBarang(sc);
                case 2 -> {
                    if (daftarBarang.isEmpty()) {
                        System.out.println("Belum ada data barang untuk diedit.");
                    } else
                        editBarang(sc);
                }
                case 3 -> {
                    if (daftarBarang.isEmpty()) {
                        System.out.println("Belum ada data barang untuk dihapus.");
                    } else
                        HapusBarang(sc);
                }
                case 4 -> tampilkanInfo();
                case 5 -> BarangMasuk(sc);
                case 6 -> BarangKeluar(sc);
                case 7 -> SearchingBarang(sc);
                case 8 -> Laporan(sc);
                case 9 -> {
                    System.out.println("Keluar program...");
                    return;
                }
                default -> System.out.println("Silahkan masukkan pilihan yang valid.");
            }
        }
    }

    // ============================================================
    // HELPER INPUT
    // ============================================================
    public String inputString(Scanner sc, String prompt) {
        System.out.print(prompt + " (ketik 'b' untuk kembali): ");
        String input = sc.nextLine().trim();
        if (input.equalsIgnoreCase("b"))
            return null;
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
    // TAMBAH BARANG / ASET
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

        Satuan.ListSatuan();
        System.out.println("Ketik '?' untuk mengetahui fungsi satuan unit");
        String unit = "";
        while (true) {
            unit = inputString(sc, "Isi Satuan unit");
            if (unit == null)
                return;
            if (unit.equals("?")) {
                System.out.println("pcs : barang per buah");
                System.out.println("box : barang dalam kotak");
                System.out.println("kg : barang berbasis berat");
                System.out.println("liter : berbasis volume cairan");
                System.out.println("meter : berbasis panjang");
                System.out.println("pack : beberapa pcs dalam bundel");
                System.out.println("roll : berbentuk gulungan");
                System.out.println("set : terdiri dari beberapa bagian");
                continue;
            }
            if (Satuan.isValidSatuan(unit))
                break;
            System.out.println("Satuan tidak valid!");
            Satuan.ListSatuan();
        }

        Satuan satuan = new Satuan(nextId, unit, null);
        int kode = kodeDasar + nextId;

        Barang b = new Barang(nextId, satuan, kode, nama, jumlah, harga, jumlah, keterangan, LocalDate.now());
        daftarBarang.add(b);

        System.out.println("\nAset Barang berhasil ditambahkan dengan ID " + nextId);
        nextId++;
    }

    // ============================================================
    // EDIT BARANG
    // ============================================================
    public void editBarang(Scanner sc) {
        System.out.println("=========== Edit Aset Barang ===========");
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
            System.out.println("\nEdit pada : ");
            System.out.println("1. Nama Barang");
            System.out.println("2. Harga");
            System.out.println("3. Jumlah Stok");
            System.out.println("4. Keterangan");
            System.out.println("5. Satuan");
            System.out.println("6. Kembali");

            Integer edit_data = inputInt(sc, "Pilih data yang ingin diedit");
            if (edit_data == null || edit_data == 6)
                return;

            switch (edit_data) {
                case 1 -> {
                    String nama_baru = inputString(sc, "Nama Barang (" + bmk.getNama_barang() + ")");
                    if (nama_baru != null)
                        bmk.setNama_barang(nama_baru);
                }
                case 2 -> {
                    Integer harga_baru = inputInt(sc, "Harga (" + bmk.getHarga_barang() + ")");
                    if (harga_baru != null)
                        bmk.setHarga_barang(harga_baru);
                }
                case 3 -> {
                    Integer stok_baru = inputInt(sc, "Jumlah Stok (" + bmk.getStock() + ")");
                    if (stok_baru != null)
                        bmk.setStock(stok_baru);
                }
                case 4 -> {
                    String ket_baru = inputString(sc, "Keterangan (" + bmk.getKeterangan() + ")");
                    if (ket_baru != null)
                        bmk.setKeterangan(ket_baru);
                }
                case 5 -> {
                    Satuan.ListSatuan();
                    String satuan = inputString(sc, "Satuan (" + bmk.getId_satuan().getNama_Satuan() + ")");
                    if (satuan != null)
                        bmk.id_satuan.setNama_Satuan(satuan);
                }
                default -> System.out.println("Pilihan tidak valid.");
            }

            System.out.println("Aset Barang dengan ID " + id_edit + " berhasil diupdate.");
        }
    }

    // ============================================================
    // HAPUS BARANG
    // ============================================================
    public void HapusBarang(Scanner sc) {
        System.out.println("=========== Hapus Aset Barang ===========");
        tampilkanInfo();

        Integer id_hapus = inputInt(sc, "Masukkan ID Barang yang ingin dihapus");
        if (id_hapus == null)
            return;

        Barang bmk = getBarangById(id_hapus);
        if (bmk != null) {
            daftarBarang.remove(bmk);
            System.out.println("Aset Barang dengan ID " + id_hapus + " berhasil dihapus.");
        } else {
            System.out.println("ID Barang tidak ditemukan.");
        }
    }

    // ============================================================
    // TAMPILKAN INFO
    // ============================================================
    public void tampilkanInfo() {
        System.out.println("\n========== DATA ASET BARANG ==========");
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada data barang.");
            return;
        }

        System.out.printf("%-10s %-12s %-10s %-20s %-8s %-10s %-12s %-30s %-15s%n",
                "ID", "Satuan", "Kode", "Nama", "Stok", "Harga", "AsetTtp", "Keterangan", "Tanggal");
        System.out.println(
                "---------------------------------------------------------------------------------------------");

        for (Barang bmk : daftarBarang) {
            System.out.printf("%-10d %-12s %-10d %-20s %-8d %-10.2f %-12d %-30s %-15s%n",
                    bmk.getId_barang(),
                    bmk.getId_satuan().getNama_Satuan(),
                    bmk.getKode_barang(),
                    bmk.getNama_barang(),
                    bmk.getStock(),
                    bmk.getHarga_barang(),
                    bmk.getAset_tetap(),
                    bmk.getKeterangan(),
                    bmk.getCreated_at());
        }
    }

    // ============================================================
    // BARANG MASUK
    // ============================================================
    public void BarangMasuk(Scanner sc) {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada data barang. Silahkan tambahkan terlebih dahulu.");
            return;
        }

        System.out.println("================ Barang Masuk ================");
        Integer id_barang = inputInt(sc, "Masukan ID Barang");
        if (id_barang == null)
            return;

        Barang barang = getBarangById(id_barang);
        if (barang == null) {
            System.out.println("ID Barang tidak ditemukan.");
            return;
        }

        String serial = inputString(sc, "Masukan Serial");
        if (serial == null)
            return;

        String kondisi = inputString(sc, "Masukan Kondisi");
        if (kondisi == null)
            return;
        if (kondisi.equals("Baik")) {
            kondisi = "Baik";
        } else if (kondisi.equals("Buruk")) {
            System.out.println("Jelaskan penyebab tersebut.");
            kondisi = inputString(sc, "Contoh Kerusakan (Bengkok, terkuplas, Patah):");
        } else {
            System.out.println("Pilihan yang salah.");
            BarangMasuk(sc);
        }

        String keterangan = inputString(sc, "Masukan Keterangan");
        if (keterangan == null)
            return;

        Barang_Masuk_Keluar bm = new Barang_Masuk_Keluar(nextIdmsk, null, 0, serial, kondisi, keterangan,
                LocalDate.now(), null, null);

        nextIdmsk++;
        daftarBarangMasukKeluar.add(bm);
        System.out.println("Barang berhasil dicatat sebagai MASUK.");
    }

    // ============================================================
    // BARANG KELUAR
    // ============================================================
    public void BarangKeluar(Scanner sc) {
        if (daftarBarangMasukKeluar.isEmpty()) {
            System.out.println("Belum ada data barang masuk.");
            return;
        }

        System.out.println("=========== Barang Keluar ===========");
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
            System.out.println("Barang ini sudah dicatat sebagai KELUAR sebelumnya!");
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
    // SEARCHING BARANG
    // ============================================================
    public void SearchingBarang(Scanner sc) {
        System.out.println("=========== Searching Barang ===========");
        String key = inputString(sc, "Masukkan nama atau kode");
        if (key == null)
            return;

        boolean ditemukan = false;
        for (Barang b : daftarBarang) {
            if (b.getNama_barang().toLowerCase().contains(key.toLowerCase()) ||
                    String.valueOf(b.getKode_barang()).equals(key)) {
                System.out.println("\nDitemukan:");
                System.out.println("ID: " + b.getId_barang());
                System.out.println("Nama: " + b.getNama_barang());
                System.out.println("Kode: " + b.getKode_barang());
                System.out.println("Stok: " + b.getStock());
                System.out.println("Harga: " + b.getHarga_barang());
                System.out.println("Satuan: " + b.getId_satuan().getNama_Satuan());
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
        System.out.println("=========== Laporan Aset ===========");
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada barang.");
            return;
        }

        int totalBarang = 0;
        float totalNilai = 0;

        System.out.println("\nJenis Barang    Jumlah    Nilai");
        for (Barang b : daftarBarang) {
            float nilai = b.getStock() * b.getHarga_barang();
            System.out.printf("%-15s %-8d Rp %, .0f%n", b.getNama_barang(), b.getStock(), nilai);
            totalBarang += b.getStock();
            totalNilai += nilai;
        }

        System.out.println("\nTotal Jenis Barang : " + daftarBarang.size());
        System.out.println("Total Item Barang  : " + totalBarang);
        System.out.println("Total Nilai Aset   : Rp " + String.format("%,.0f", totalNilai));
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

// ============================================================
// CUSTOM EXCEPTION
// ============================================================
class DataTidakValid extends Exception {
    public DataTidakValid(String message) {
        super(message);
    }
}
