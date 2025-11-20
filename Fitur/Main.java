import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    static ArrayList<Barang> daftarBarang = new ArrayList<>();

    public int nextId = 1;
    public int nextIdmsk = 1;
    public int nextIdUser = 1;
    public int kodeDasar = 1000;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Main app = new Main();
        app.Menu(sc);

    }

    public void Menu(Scanner sc) {

        int pilih = 0;

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
            System.out.println("9. History Aktivitas");
            System.out.println("10. Perhitungan Fisik / Stock Opname");
            System.out.println("11. Manajemen User");
            System.out.println("12. Keluar");
            System.out.println("=================================");

            try {
                pilih = sc.nextInt();
                sc.nextLine(); // buang newline
            } catch (Exception e) {
                System.out.println("Input tidak valid. Masukkan angka!");
                sc.nextLine();
                continue;
            }

            switch (pilih) {
                case 1:
                    tambahBarang(sc);
                    break;

                case 2:
                    if (daftarBarang.isEmpty()) {
                        System.out.println("Belum ada data barang untuk diedit.");
                        break;
                    } else {
                        editBarang(sc);
                    }
                    break;

                case 3:
                    if (daftarBarang.isEmpty()) {
                        System.out.println("Belum ada data barang untuk dihapus.");
                        break;
                    } else {
                        HapusBarang(sc);
                    }
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
                    System.out.println("Fitur History belum tersedia.");
                    break;

                case 8:
                    System.out.println("Terima kasih telah menggunakan StockOne!");
                    return; // keluar dari program
                case 11:
                    ManajemenUser(sc);
                    break;
                default:
                    System.out.println("Silahkan masukkan pilihan yang valid.");
                    break;
            }
        }
    }

    public void tambahBarang(Scanner sc) {

        System.out.println("\n========== Tambah Aset Barang ==========");
        System.out.print("Nama Barang: ");
        String nama = sc.nextLine();

        System.out.print("Harga: ");
        int harga = sc.nextInt();
        sc.nextLine();

        System.out.print("Jumlah Masuk: ");
        int jumlah = sc.nextInt();
        sc.nextLine();

        System.out.print("Keterangan: ");
        String keterangan = sc.nextLine();

        Satuan.ListSatuan();
        System.out.println("Ketik '?' untuk mengetahui fungsi satuan unit");
        System.out.print("Isi Satuan unit : ");

        String unit = "";

        while (true) {
            unit = sc.nextLine().trim();

            if (unit.equals("?")) {
                System.out.println("pcs : barang per buah");
                System.out.println("box : barang dalam kotak");
                System.out.println("kg : barang berbasis berat");
                System.out.println("liter : berbasis volume cairan");
                System.out.println("meter : berbasis panjang");
                System.out.println("pack : beberapa pcs dalam bundel");
                System.out.println("roll : berbentuk gulungan");
                System.out.println("set : terdiri dari beberapa bagian");
                System.out.print("Masukkan satuan ulang: ");
                continue;
            }

            if (Satuan.isValidSatuan(unit)) {
                break;
            }
            System.out.println("Satuan tidak valid!");
            Satuan.ListSatuan();
            System.out.print("Masukkan ulang: ");
        }

        Satuan satuan = new Satuan(nextId, unit, null);

        int kode = kodeDasar + nextId;

        Barang b = new Barang(
                nextId,
                satuan,
                kode,
                nama,
                jumlah,
                harga,
                jumlah,
                keterangan,
                LocalDate.now());

        daftarBarang.add(b);

        System.out.println("\nAset Barang berhasil ditambahkan dengan ID");
        nextId++;
    }

    public void editBarang(Scanner sc) {
        System.out.println("=========== Edit Aset Barang ===========");
        tampilkanInfo();
        System.out.print("Masukkan ID Barang yang ingin diedit: ");
        int id_edit = sc.nextInt();
        sc.nextLine();

        for (Barang bmk : daftarBarang) {
            if (bmk.getId_barang() == id_edit) {
                while (true) {
                    System.out.println("\nEdit pada : ");
                    System.out.println("1. Nama Barang");
                    System.out.println("2. Harga");
                    System.out.println("3. Jumlah Stok");
                    System.out.println("4. Keterangan");
                    System.out.println("5. satuan");

                    int edit_data = sc.nextInt();
                    sc.nextLine();
                    switch (edit_data) {
                        case 1:
                            System.out.print("Nama Barang (" + bmk.getNama_barang() + "): ");
                            String nama_baru = sc.nextLine();
                            if (!nama_baru.isEmpty()) {
                                bmk.setNama_barang(nama_baru);
                            }
                            break;
                        case 2:
                            System.out.print("Harga (" + bmk.getHarga_barang() + "): ");
                            String harga_input = sc.nextLine();
                            if (!harga_input.isEmpty()) {
                                int harga_baru = Integer.parseInt(harga_input);
                                bmk.setHarga_barang(harga_baru);
                            }
                            break;
                        case 3:
                            System.out.print("Jumlah Stok (" + bmk.getStock() + "): ");
                            String stok_input = sc.nextLine();
                            if (!stok_input.isEmpty()) {
                                int stok_baru = Integer.parseInt(stok_input);
                                bmk.setStock(stok_baru);
                            }
                            break;
                        case 4:
                            System.out.print("Keterangan (" + bmk.getKeterangan() + "): ");
                            String keterangan_baru = sc.nextLine();
                            if (!keterangan_baru.isEmpty()) {
                                bmk.setKeterangan(keterangan_baru);
                            }
                            break;
                        case 5:
                            Satuan.ListSatuan();
                            System.out.println("Satuan (" + bmk.getId_satuan().getNama_Satuan() + "): ");
                            String satuan = sc.nextLine().trim();
                            if (!satuan.isEmpty()) {
                                bmk.id_satuan.setNama_Satuan(satuan);
                            }
                            break;
                        default:
                            break;
                    }

                    System.out.println("Aset Barang dengan ID " + id_edit + " berhasil diupdate.");
                    return;
                }
            }
        }
    }

    public void HapusBarang(Scanner sc) {
        System.out.println("=========== Hapus Aset Barang ===========");
        tampilkanInfo();
        System.out.print("Masukkan ID Barang yang ingin dihapus: ");
        int id_hapus = sc.nextInt();

        for (Barang bmk : daftarBarang) {
            if (bmk.getId_barang() == id_hapus) {
                daftarBarang.remove(bmk);
                System.out.println("Aset Barang dengan ID " + id_hapus + " berhasil dihapus.");
                return;
            }
        }
        Menu(sc);
    }

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

    public void BarangMasuk(Scanner sc) {
        System.out.println("================ Barang Masuk ================");
        System.out.println("Masukan ID Master: ");
        int id_master = sc.nextInt();

        System.out.println("Masukan ID Barang: ");
        int id_barang = sc.nextInt();

        System.out.println("Masukan ID Kelompok: ");
        int id_kelompok = sc.nextInt();

        System.out.println("Masukan Serial: ");
        String serial = sc.nextLine();

        System.out.println("Masukan Kondisi: ");
        String kondisi = sc.nextLine();

        System.out.println("Masukan Keterangan: ");
        String keterangan = sc.nextLine();

        Barang_Masuk_Keluar bm = new Barang_Masuk_Keluar(nextIdmsk, id_master, id_barang, id_kelompok, serial, kondisi,
                keterangan, LocalDate.now(), null, null);
        nextIdmsk++;
    }

    public void BarangKeluar(Scanner sc) {

    }

    public void ManajemenUser(Scanner sc) {
        System.out.println("=========== Manajemen User ===========");
        System.out.println("");

        System.out.println("Silahkan masukkan nama user baru: ");
        String nama = sc.nextLine();

        System.out.println("Silahkan masukkan Email: ");
        String email = sc.nextLine();

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        if (email.matches(regex)) {
            System.out.println("Email valid.");
        } else {
            System.out.println("Email TIDAK valid (huruf kecil di nama TIDAK boleh).");
        }

    }
}

class DataTidakValid extends Exception {
    public DataTidakValid(String message) {
        super(message);
    }
}
