import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    static ArrayList<Barang> daftarBarang = new ArrayList<>();
    static ArrayList<User> daftarUser = new ArrayList<>();
    static ArrayList<Barang_Masuk_Keluar> daftarBarangMasukKeluar = new ArrayList<>();
    static ArrayList<Master> daftarMaster = new ArrayList<>();

    public int nextId = 1;
    public int nextIdMst = 1;
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
                    System.out.println("Searching Barang");
                    break;

                case 8:
                    System.out.println("laporan Aset");
                    return; // keluar dari program
                case 9:
                    System.out.println("Fitur History belum tersedia.");
                    break;
                case 10:
                    System.out.println("Fitur Stock Opname belum tersedia.");
                    break;
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
        if (daftarMaster.isEmpty() || daftarBarang.isEmpty()) {
            System.out.println("Belum ada data master atau barang. Silahkan tambahkan terlebih dahulu.");
            return;
        }

        System.out.println("================ Barang Masuk ================");
        System.out.println("Masukan ID Master: ");
        int id_master = sc.nextInt();

        Master master = getMasterById(id_master);
        if (master == null) {
            System.out.println("ID Master tidak ditemukan.");
            return;
        }

        System.out.println("Masukan ID Barang: ");
        int id_barang = sc.nextInt();

        Barang barang = getBarangById(id_barang);
        if (barang == null) {
            System.out.println("ID Barang tidak ditemukan.");
            return;
        }

        System.out.println("Masukan ID Kelompok: ");
        int id_kelompok = sc.nextInt();
        sc.nextLine();

        System.out.println("Masukan Serial: ");
        String serial = sc.nextLine();

        System.out.println("Masukan Kondisi: ");
        String kondisi = sc.nextLine();

        System.out.println("Masukan Keterangan: ");
        String keterangan = sc.nextLine();

        Barang_Masuk_Keluar bm = new Barang_Masuk_Keluar(nextIdmsk, master, barang, id_kelompok, serial, kondisi,
                keterangan, LocalDate.now(), null, null);
        nextIdmsk++;
        daftarBarangMasukKeluar.add(bm);
    }

    public void BarangKeluar(Scanner sc) {

    }

    public void ManajemenUser(Scanner sc) {

        System.out.println("=========== Manajemen User ===========");
        System.out.println("1. Tambah User");
        System.out.println("2. Hapus User");
        System.out.println("3. Tampilkan User");

        int pilih = sc.nextInt();
        sc.nextLine();

        if (pilih == 1) {
            tambahUser(sc);
        } else if (pilih == 2) {
            hapusUser(sc);
        } else if (pilih == 3) {
            tampilUser();
        } else {
            System.out.println("Silahkan masukkan pilihan yang valid.");
        }

    }

    public void tampilUser() {
        System.out.println("\n========== DATA USER ==========");

        if (daftarUser.isEmpty()) {
            System.out.println("Belum ada user.");
            return;
        }

        System.out.printf("%-5s %-15s %-15s %-10s %-15s%n",
                "ID", "Nama", "Username", "Role", "Jabatan");

        for (User u : daftarUser) {
            System.out.printf("%-5d %-15s %-15s %-10d %-15s%n",
                    u.getIdUser(),
                    u.getNamaUser(),
                    u.getUsername(),
                    u.getRoleUser(),
                    u.getJabatan());
        }
    }

    public void tambahUser(Scanner sc) {
        System.out.println("Silahkan masukkan nama: ");
        String nama = sc.nextLine();

        System.out.println("Silahkan masukkan username: ");
        String username = sc.nextLine();

        System.out.println("Daftar pengelola sebagai: ");
        System.out.println("101 - admin ");
        System.out.println("102 - staff ");
        System.out.println("103 - supervisor ");
        System.out.println("201 - manajer ");
        int role = sc.nextInt();
        sc.nextLine();

        System.out.println("Silahkan masukkan jabatan: ");
        String jabatan = sc.nextLine();

        System.out.println("Silahkan masukkan password: ");
        String password = sc.nextLine();

        User user = new User(nextIdUser, role, nama, username, jabatan, password);
        nextIdUser++;

        Master master = new Master(nextIdMst, user, 101, "", 1, LocalDate.now().getDayOfYear(),
                LocalDate.now().getDayOfYear());
        nextIdMst++;

        daftarUser.add(user);
        daftarMaster.add(master);
    }

    public void hapusUser(Scanner sc) {

        tampilUser();

        System.out.print("\nMasukkan ID User yang ingin dihapus: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (User u : daftarUser) {
            if (u.getIdUser() == id) {
                daftarUser.remove(u);
                System.out.println("User berhasil dihapus.");
                return;
            }
        }

        System.out.println("User dengan ID tersebut tidak ditemukan.");

    }

    public Master getMasterById(int id) {
        for (Master m : daftarMaster) {
            if (m.getIdMaster() == id) {
                return m;
            }
        }
        return null;
    }

    public Barang getBarangById(int id) {
        for (Barang b : daftarBarang) {
            if (b.getId_barang() == id) {
                return b;
            }
        }
        return null;
    }

}

class DataTidakValid extends Exception {
    public DataTidakValid(String message) {
        super(message);
    }
}
