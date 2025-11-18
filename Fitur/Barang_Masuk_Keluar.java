import java.time.LocalDate;

public class Barang_Masuk_Keluar {
    int id_barang_masuk;
    Master id_master;
    Barang id_barang;
    int id_Kelompok;
    String serial;
    String kondisi;
    String Keterangan;
    LocalDate tanggal_masuk;
    String Ket_Keluar;
    LocalDate tanggal_keluar;

    public Barang_Masuk_Keluar(int id_barang_masuk, Master id_master, Barang id_barang, int id_Kelompok, String serial,
            String kondisi, String keterangan, LocalDate tanggal_masuk, String ket_Keluar, LocalDate tanggal_keluar) {
        this.id_barang_masuk = id_barang_masuk;
        this.id_master = id_master;
        this.id_barang = id_barang;
        this.id_Kelompok = id_Kelompok;
        this.serial = serial;
        this.kondisi = kondisi;
        Keterangan = keterangan;
        this.tanggal_masuk = tanggal_masuk;
        Ket_Keluar = ket_Keluar;
        this.tanggal_keluar = tanggal_keluar;
    }

    public int getId_barang_masuk() {
        return id_barang_masuk;
    }

    public void setId_barang_masuk(int id_barang_masuk) {
        this.id_barang_masuk = id_barang_masuk;
    }

    public Master getId_master() {
        return id_master;
    }

    public void setId_master(Master id_master) {
        this.id_master = id_master;
    }

    public Barang getId_barang() {
        return id_barang;
    }

    public void setId_barang(Barang id_barang) {
        this.id_barang = id_barang;
    }

    public int getId_Kelompok() {
        return id_Kelompok;
    }

    public void setId_Kelompok(int id_Kelompok) {
        this.id_Kelompok = id_Kelompok;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String keterangan) {
        Keterangan = keterangan;
    }

    public LocalDate getTanggal_masuk() {
        return tanggal_masuk;
    }

    public void setTanggal_masuk(LocalDate tanggal_masuk) {
        this.tanggal_masuk = tanggal_masuk;
    }

    public String getKet_Keluar() {
        return Ket_Keluar;
    }

    public void setKet_Keluar(String ket_Keluar) {
        Ket_Keluar = ket_Keluar;
    }

    public LocalDate getTanggal_keluar() {
        return tanggal_keluar;
    }

    public void setTanggal_keluar(LocalDate tanggal_keluar) {
        this.tanggal_keluar = tanggal_keluar;
    }

}
