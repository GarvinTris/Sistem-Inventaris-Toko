import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {

    static ArrayList<Barang> daftarBarang = new ArrayList<>();

    public int i = 1;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Main app = new Main();
        app.Menu(sc);
    }

    public void Menu(Scanner sc) {
        int pilih = 0;

        System.out.println("=========== STOCKONE ============");
        System.out.println("Perlengkapan dan Aset Kantor Anda");
        System.out.println("=================================");
        System.out.println("Silahkan pilih Sistem Inventaris");
        System.out.println("1. Memasukan Aset Barang");
        System.out.println("2. Edit Aset Barang");
        System.out.println("3. Hapus Aset Barang");
        System.out.println("4. Tampilkan Aset"); // Menampilkan semua data barang
        System.out.println("5. Laporan Aset Barang"); // Data Barang masuk dan keluar
        System.out.println("6. Searching"); // Berdasaikan nama barang atau kode barang dan kondisi
        System.out.println("7. History"); // melacak siapa menambah produk
        System.out.println("8. Keluar");
        System.out.print("=================================");
        System.out.print("\nUntuk melihat fitur kami cukup ketik 'info' dan enter\n");

        try {
            pilih = sc.nextInt();
            switch (pilih) {
                case 1:
                    TambahBarang(sc);
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    tampilkanInfo();
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;

                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Inputan tidak valid. Silakan masukkan angka antara 1-8.");
        }
    }

    public void TambahBarang(Scanner sc) {
        System.out.println("=====================================");
        System.out.println("Memasukan Aset Barang");
        System.out.println("Tempat menambahkan Aset Anda ke dalam sistem");

        System.out.print("Nama Barang: ");
        String nama = sc.nextLine();

        System.out.print("Harga: ");
        int harga = sc.nextInt();

        System.out.print("Kategori Barang: ");
        String kategori = sc.nextLine();

        System.out.print("Jumlah Masuk: ");
        int jumlah = sc.nextInt();
        sc.nextLine();

        System.out.print("Kode Barang (SN): ");
        int kode = sc.nextInt();

        System.out.print("Kondisi Barang: ");
        String kondisi = sc.nextLine();

        System.out.println("Keterangan : ");
        String keterangan = sc.nextLine();

        Barang b = new Barang(i, null, kode, nama, jumlah, harga, jumlah, keterangan, LocalDate.now());

        // Barang_Masuk_Keluar barangMasuk = new Barang_Masuk_Keluar(i, null, null, 0,
        // kode, kondisi,
        // keterangan, LocalDate.now(), null, null);

        daftarBarang.add(b);

        System.out.println("Aset Barang berhasil ditambahkan!");
        i++;
        Menu(sc);
    }

    public void tampilkanInfo() {
        for (Barang bmk : daftarBarang) {
            System.out.println("=========== DAFTAR ASET BARANG =================================");
            System.out.println("ID Barang Masuk | Serial | Kondisi | Keterangan | Tanggal Masuk");
            System.out.println("================================================================");
            System.out.println(
                    "\t" + bmk.getId_barang() + "|\t" + bmk.getSerial() + "|\t" + bmk.getKondisi() + "|\t"
                            + bmk.getKeterangan() + "|\t" + bmk.getTanggal_masuk());
        }
    }

}

class DataTidakValid extends Exception {
    public DataTidakValid(String message) {
        super(message);
    }
}
