import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Menu {

    static ArrayList<Barang> daftarBarang = new ArrayList<>();
    static ArrayList<Barang_Masuk_Keluar> daftarBarangMasukKeluar = new ArrayList<>();
    static ArrayList<Jenis_Barang> daftarJenisBarang = new ArrayList<>();
    static ArrayList<Laporan> daftarLaporan = new ArrayList<>();
    static int nextIdLaporan = 1;

    static int nextId = 1;
    static int nextIdmsk = 1;
    static int kodeDasar = 1000;

    public Menu() {
        // Tambahkan contoh jenis barang
        ID_Kelompok_Aset kelompokPokok = new ID_Kelompok_Aset(1, "Kebutuhan Pokok");
        ID_Kelompok_Aset kelompokKantor = new ID_Kelompok_Aset(2, "Peralatan Kantor");
        ID_Kelompok_Aset kelompokFurniture = new ID_Kelompok_Aset(3, "Furniture");
        ID_Kelompok_Aset kelompokElektronik = new ID_Kelompok_Aset(4, "Elektronik");

        // Tambahkan contoh jenis barang (Jenis_Barang membutuhkan objek KelompokAset)
        daftarJenisBarang.add(
                new Jenis_Barang(1, kelompokPokok, "Kebutuhan Pokok", "Barang-barang kebutuhan sehari-hari kantor"));
        daftarJenisBarang.add(
                new Jenis_Barang(2, kelompokKantor, "Peralatan Kantor", "Barang elektronik dan perlengkapan kantor"));
        daftarJenisBarang.add(new Jenis_Barang(3, kelompokFurniture, "Furniture", "Meja, kursi, lemari kantor"));
        daftarJenisBarang.add(new Jenis_Barang(4, kelompokKantor, "ATK", "Alat Tulis Kantor"));
        daftarJenisBarang
                .add(new Jenis_Barang(5, kelompokElektronik, "Elektronik Lainnya", "Printer, scanner, proyektor"));
    }

    public void Utama(Scanner sc) {
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
                case 1:
                    tambahBarang(sc);
                    break;
                case 2:
                    editBarang(sc);
                    break;
                case 3:
                    HapusBarang(sc);
                    break;
                case 4:
                    tampilkanInfo();
                    break;
                case 5:
                    BarangMasuk(sc);
                    break;
                case 6:
                    BarangKeluar(sc);
                    break;
                case 7:
                    SearchingBarang(sc);
                    break;
                case 8:
                    laporanMenu(sc);
                    break;
                case 9:
                    tampilkanHistory();
                    break;
                case 10:
                    System.out.println("Keluar program...");
                    return;
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }

        }
    }

    // template input
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
        if (input == null) {
            return null;
        }
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid! Masukkan angka.");
            return inputInt(sc, prompt);
        }
    }

    public Float InputHarga(Scanner sc, String prompt) {
        System.out.print(prompt + " (ketik 'b' untuk kembali): ");
        String input = sc.nextLine().trim();
        if (input.equalsIgnoreCase("b"))
            return null;

        try {
            float value = Float.parseFloat(input);
            if (value < 0) {
                System.out.println("Input tidak boleh negatif!");
                return InputHarga(sc, prompt); // ulang input
            }
            return value;
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid! Masukkan angka.");
            return InputHarga(sc, prompt); // ulang input
        }
    }

    // Create
    public void tambahBarang(Scanner sc) {
        System.out.println("\n========== Tambah Aset Barang ==========");

        String nama = inputString(sc, "Nama Barang");
        if (nama == null)
            return;
        if (nama.isEmpty()) {
            System.out.println("Nama barang tidak boleh kosong!");
            return;
        }
        nama = nama.trim();

        Integer harga = inputInt(sc, "Harga");
        if (harga == null)
            return;

        Integer jumlah = inputInt(sc, "Jumlah Masuk");
        if (jumlah == null)
            return;

        String keterangan = inputString(sc, "Keterangan");
        keterangan = keterangan.trim();
        if (keterangan.isEmpty()) {
            keterangan = "-";
        }

        // Pilih jenis
        System.out.println("\n=== Pilih Jenis Barang ===");
        for (Jenis_Barang jb : daftarJenisBarang) {
            System.out.println(jb.getId_Jenis_Barang() + ". " + jb.getNama_Jenis_Barang());
        }
        Integer idJenis = inputInt(sc, "Pilih Jenis Barang");

        if (idJenis == null) {
            System.out.println("Kembali ke menu sebelumnya...");
            return; // keluar dari method atau lakukan apa yang sesuai
        }

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
        String unit = inputString(sc, "Satuan (pcs/box/kg/liter)");
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

    // Update
    public void editBarang(Scanner sc) {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada data barang.");
            return;
        }
        tampilkanInfo();
        Integer id = inputInt(sc, "Masukkan ID Barang");
        if (id == null) { // user memilih kembali
            System.out.println("Kembali ke menu sebelumnya...");
            return;
        }
        Barang bmk = getBarangById(id);
        if (bmk == null) {
            System.out.println("Barang tidak ditemukan.");
            return;
        }

        while (true) {
            System.out.println("""
                    1. Nama
                    2. Harga
                    3. Jumlah Stok
                    4. Keterangan
                    5. Satuan
                    6. Jenis Barang
                    7. Kembali""");

            Integer pilih = inputInt(sc, "Pilih menu");
            if (pilih == null || pilih == 7)
                return;

            switch (pilih) {
                case 1:
                    bmk.setNama_barang(inputString(sc, "Nama Baru"));
                    break;
                case 2:
                    Float harga = InputHarga(sc, "Harga Baru");
                    if (harga == null) {
                        System.out.println("Kembali ke menu sebelumnya...");
                        break;
                    }
                    if (harga <= 0) {
                        System.out.println("Harga harus lebih dari 0!");
                        break;
                    }
                    bmk.setHarga_barang(harga);
                case 3:
                    Integer stock = inputInt(sc, "Stok Baru");
                    if (stock == null || stock < 0) {
                        System.out.println("Stok tidak valid!");
                        break;
                    }
                    bmk.setStock(stock);
                    break;
                case 4:
                    bmk.setKeterangan(inputString(sc, "Keterangan Baru"));
                    break;
                case 5:
                    Integer id_satuan = inputInt(sc, "ID Satuan Baru");
                    if (id_satuan == null) {
                        System.out.println("Kembali ke menu sebelumnya...");
                        return;
                    }
                    bmk.getId_satuan().setNama_Satuan(id_satuan.toString());
                    break;
                case 6: {
                    System.out.println("=== Pilih Jenis ===");
                    for (Jenis_Barang jb : daftarJenisBarang)
                        System.out.println(jb.getId_Jenis_Barang() + ". " + jb.getNama_Jenis_Barang());
                    Integer idJ = inputInt(sc, "Masukkan ID Jenis");
                    if (idJ == null) { // user memilih kembali
                        System.out.println("Kembali ke menu sebelumnya...");
                        return;
                    }
                    for (Jenis_Barang jb : daftarJenisBarang)
                        if (jb.getId_Jenis_Barang() == idJ)
                            bmk.setJenisBarang(jb);
                    break;
                }
                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }

            System.out.println("Data berhasil diupdate.");
        }
    }

    // Delete
    public void HapusBarang(Scanner sc) {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada data barang.");
            return;
        }
        tampilkanInfo();
        Integer id = inputInt(sc, "Masukkan ID Barang");

        if (id == null) { // user memilih kembali
            System.out.println("Kembali ke menu sebelumnya...");
            return;
        }

        Barang b = getBarangById(id);
        if (b != null) {
            daftarBarang.remove(b);
            System.out.println("Barang berhasil dihapus.");
        } else
            System.out.println("Barang tidak ditemukan.");
    }

    // Read
    public void tampilkanInfo() {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada data barang.");
            return;
        }

        System.out.printf("%-5s %-10s %-20s %-20s %-10s %-10s%n",
                "ID", "Kode", "Jenis", "Nama", "Stok", "Harga");

        for (Barang b : daftarBarang) {

            String jenis = (b.getJenisBarang() != null) ? b.getJenisBarang().getNama_Jenis_Barang() : "Tidak ada";

            String nama = b.getNama_barang();
            if (nama.length() > 20) {
                nama = nama.substring(0, 17) + "...";
            }

            System.out.printf("%-5d %-10d %-20s %-20s %-10d %-10.0f%n",
                    b.getId_barang(),
                    b.getKode_barang(),
                    jenis,
                    nama,
                    b.getStock(),
                    b.getHarga_barang());

        }
    }

    public void BarangMasuk(Scanner sc) {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada data barang.");
            return;
        }

        Integer id = inputInt(sc, "ID Barang");
        if (id == null) {
            System.out.println("Kembali ke menu sebelumnya...");
            return;
        }

        Barang barang = getBarangById(id);
        if (barang == null) {
            System.out.println("Tidak ditemukan.");
            return;
        }

        String serial;
        while (true) {
            serial = inputString(sc, "Serial");
            if (serial == null) {
                System.out.println("Kembali ke menu sebelumnya...");
                return;
            }
            if (!isSerialValid(serial)) {
                System.out.println("Serial sudah ada!");
                continue;
            }
            break;
        }

        String kondisi;
        while (true) {
            kondisi = inputString(sc, "Kondisi (Baik/Buruk)");
            if (kondisi == null) {
                System.out.println("Kembali ke menu sebelumnya...");
                return;
            }

            if (kondisi.equalsIgnoreCase("Baik") || kondisi.equalsIgnoreCase("Buruk")) {
                kondisi = kondisi.substring(0, 1).toUpperCase() + kondisi.substring(1).toLowerCase();
                break;
            } else {
                System.out.println("Kondisi harus 'Baik' atau 'Buruk'.");
            }
        }

        String ket = inputString(sc, "Keterangan");
        if (ket == null) {
            System.out.println("Kembali ke menu sebelumnya...");
            return;
        }

        Integer qtyMasuk = inputInt(sc, "Jumlah Barang Masuk");
        if (qtyMasuk == null || qtyMasuk <= 0) {
            System.out.println("Jumlah barang tidak valid.");
            return;
        }

        Barang_Masuk_Keluar bm = new Barang_Masuk_Keluar(
                nextIdmsk,
                barang,
                0,
                serial,
                kondisi,
                ket,
                LocalDate.now(),
                null,
                null,
                qtyMasuk);

        // Update stock barang
        barang.setStock(barang.getStock() + qtyMasuk);

        daftarBarangMasukKeluar.add(bm);
        nextIdmsk++;

        System.out.println("Barang masuk berhasil dicatat.");
    }

    public boolean isSerialValid(String serial) {
        if (serial == null || serial.isBlank())
            return false;
        for (Barang_Masuk_Keluar bm : daftarBarangMasukKeluar)
            if (bm.getSerial().equalsIgnoreCase(serial))
                return false;
        return true;
    }

    public void BarangKeluar(Scanner sc) {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada data barang.");
            return;
        }
        Integer id = inputInt(sc, "ID Barang Masuk");
        if (id == null) {
            System.out.println("Kembali ke menu sebelumnya...");
            return; // user tekan 'b'
        }
        Barang_Masuk_Keluar data = null;

        for (Barang_Masuk_Keluar bm : daftarBarangMasukKeluar)
            if (bm.getId_barang_masuk() == id)
                data = bm;

        if (data == null) {
            System.out.println("Tidak ditemukan.");
            return;
        }
        if (data.getTanggal_keluar() != null) {
            System.out.println("Sudah keluar.");
            return;
        }

        data.setTanggal_keluar(LocalDate.now());
        data.setKet_Keluar(inputString(sc, "Keterangan keluar"));
        System.out.println("Barang keluar dicatat.");
    }

    public void SearchingBarang(Scanner sc) {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada data barang.");
            return;
        }
        String key = inputString(sc, "Pencarian (berlaku untuk nama barang)");
        if (key == null) {
            System.out.println("Kembali ke menu sebelumnya...");
            return;
        }
        boolean ada = false;
        for (Barang b : daftarBarang) {
            if (b.getNama_barang().toLowerCase().contains(key.toLowerCase())) {
                System.out.println(b.getId_barang() + " - " + b.getNama_barang());
                ada = true;
            }
        }
        if (!ada)
            System.out.println("Tidak ditemukan.");
        sc.nextLine();
    }

    public void laporanMenu(Scanner sc) {
        if (daftarBarang.isEmpty()) {
            System.out.println("Belum ada data barang.");
            return;
        }
        while (true) {
            System.out.println("\n===== MENU LAPORAN =====");
            System.out.println("1. Buat Laporan");
            System.out.println("2. Tampilkan Laporan");
            System.out.println("3. Edit Laporan");
            System.out.println("4. Hapus Laporan");
            System.out.println("5. Kembali");
            Integer pilih = inputInt(sc, "Pilih menu");

            if (pilih == null || pilih == 5)
                return;

            switch (pilih) {
                case 1:
                    buatLaporan(sc);
                    break;
                case 2:
                    tampilkanLaporan();
                    break;
                case 3:
                    editLaporan(sc);
                    break;
                case 4:
                    hapusLaporan(sc);
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    // Create
    public void buatLaporan(Scanner sc) {
        System.out.println("===== Buat Laporan Aset =====");

        DateTimeFormatter inputFmt = DateTimeFormatter.ofPattern("yyyyMMdd");
        DateTimeFormatter outputFmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String strAwal = inputString(sc, "Tanggal Awal (format YYYYMMDD)");
        if (strAwal == null) {
            System.out.println("Kembali...");
            return;
        }

        LocalDate awalDate;
        try {
            awalDate = LocalDate.parse(strAwal, inputFmt);
        } catch (DateTimeParseException e) {
            System.out.println("Format tanggal salah!");
            return;
        }

        if (awalDate.isBefore(LocalDate.now())) {
            System.out.println("Tanggal awal tidak boleh lebih kecil dari tanggal hari ini (" +
                    LocalDate.now().format(outputFmt) + ").");
            return;
        }

        String strAkhir = inputString(sc, "Tanggal Akhir (format YYYYMMDD)");
        if (strAkhir == null) {
            System.out.println("Kembali...");
            return;
        }

        LocalDate akhirDate;
        try {
            akhirDate = LocalDate.parse(strAkhir, inputFmt);
        } catch (DateTimeParseException e) {
            System.out.println("Format tanggal salah!");
            return;
        }

        if (akhirDate.isBefore(awalDate)) {
            System.out.println("Tanggal akhir tidak boleh lebih kecil daripada tanggal awal.");
            return;
        }

        int totalQty = 0;
        float totalNilai = 0;

        for (Barang_Masuk_Keluar bm : daftarBarangMasukKeluar) {

            LocalDate masuk = bm.getTanggal_masuk();
            if (masuk == null)
                continue;

            boolean dalamRange = (!masuk.isBefore(awalDate)) && // masuk >= awal
                    (!masuk.isAfter(akhirDate)); // masuk <= akhir

            if (dalamRange) {
                Barang b = bm.getId_barang();
                int qty = bm.getQtyMasuk();
                totalQty += qty;
                totalNilai += qty * b.getHarga_barang();
            }
        }

        if (totalQty == 0) {
            System.out.println("Tidak ada barang yang masuk dalam periode ini.");
            return;
        }

        String namaLaporan = inputString(sc, "Nama Laporan");
        if (namaLaporan == null)
            return;

        Laporan lp = new Laporan(
                nextIdLaporan,
                namaLaporan,
                Integer.parseInt(awalDate.format(inputFmt)),
                Integer.parseInt(akhirDate.format(inputFmt)),
                Integer.parseInt(LocalDate.now().format(inputFmt)),
                totalQty,
                totalNilai);

        daftarLaporan.add(lp);
        nextIdLaporan++;

        System.out.println("\n=== Laporan Dibuat ===");
        System.out.printf("ID Laporan : %d%n", lp.getIdLaporan());
        System.out.printf("Nama       : %s%n", lp.getNamaLaporan());
        System.out.printf("Periode    : %s - %s%n", awalDate.format(outputFmt), akhirDate.format(outputFmt));
        System.out.printf("Total Stok : %d%n", lp.getTotalQty());
        System.out.printf("Total Nilai: Rp %.0f%n", lp.getTotalNilai());
    }

    // Read
    public void tampilkanLaporan() {
        if (daftarLaporan.isEmpty()) {
            System.out.println("Belum ada laporan.");
            return;
        }

        System.out.printf("%-5s %-20s %-12s %-12s %-10s %-12s%n",
                "ID", "Nama Laporan", "Awal", "Akhir", "TotalQty", "TotalNilai");
        for (Laporan lp : daftarLaporan) {
            System.out.printf("%-5d %-20s %-12d %-12d %-10d Rp %-12.0f%n",
                    lp.getIdLaporan(), lp.getNamaLaporan(),
                    lp.getAwalLaporan(), lp.getAkhirLaporan(),
                    lp.getTotalQty(), lp.getTotalNilai());
        }
    }

    // Update
    public void editLaporan(Scanner sc) {
        if (daftarLaporan.isEmpty()) {
            System.out.println("Belum ada laporan.");
            return;
        }

        Integer id = inputInt(sc, "Masukkan ID Laporan yang ingin diubah");
        if (id == null) { // user memilih kembali
            System.out.println("Kembali ke menu sebelumnya...");
            return;
        }
        Laporan lp = null;
        for (Laporan l : daftarLaporan) {
            if (l.getIdLaporan() == id)
                lp = l;
        }

        if (lp == null) {
            System.out.println("Laporan tidak ditemukan.");
            return;
        }

        String namaBaru = inputString(sc, "Nama Laporan Baru");
        if (namaBaru != null && !namaBaru.isBlank()) {
            lp.setNamaLaporan(namaBaru);
            System.out.println("Nama laporan berhasil diupdate.");
        } else {
            System.out.println("Tidak ada perubahan nama.");
        }

        // Bisa ditambahkan update tanggal & recalc totalQty & totalNilai jika perlu
    }

    // Delete
    public void hapusLaporan(Scanner sc) {
        if (daftarLaporan.isEmpty()) {
            System.out.println("Belum ada laporan.");
            return;
        }

        Integer id = inputInt(sc, "Masukkan ID Laporan yang ingin dihapus");

        if (id == null) { // user memilih kembali
            System.out.println("Kembali ke menu sebelumnya...");
            return;
        }
        Laporan lp = null;
        for (Laporan l : daftarLaporan) {
            if (l.getIdLaporan() == id)
                lp = l;
        }

        if (lp != null) {
            daftarLaporan.remove(lp);
            System.out.println("Laporan berhasil dihapus.");
        } else {
            System.out.println("Laporan tidak ditemukan.");
        }
    }

    public void tampilkanHistory() {
        for (Barang_Masuk_Keluar bm : daftarBarangMasukKeluar) {
            System.out.println(bm.getId_barang_masuk() + " - " + bm.getSerial()
                    + " | Masuk: " + bm.getTanggal_masuk()
                    + " | Keluar: " + bm.getTanggal_keluar());
        }
    }

    public Barang getBarangById(int id) {
        for (Barang b : daftarBarang)
            if (b.getId_barang() == id)
                return b;
        return null;
    }
}
