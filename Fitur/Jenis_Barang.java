public class Jenis_Barang {
    private int Id_Jenis_Barang;
    private int Id_Kelompok_Aset;
    private String Nama_Jenis_Barang;
    private String Keterangan;

    public Jenis_Barang() {
    }

    public Jenis_Barang(int Id_Jenis_Barang, int Id_Kelompok_Aset, String Nama_Jenis_Barang, String Keterangan) {
        this.Id_Jenis_Barang = Id_Jenis_Barang;
        this.Id_Kelompok_Aset = Id_Kelompok_Aset;
        this.Nama_Jenis_Barang = Nama_Jenis_Barang;
        this.Keterangan = Keterangan;
    }

    public int getId_Jenis_Barang() {
        return Id_Jenis_Barang;
    }

    public void setId_Jenis_Barang(int Id_Jenis_Barang) {
        this.Id_Jenis_Barang = Id_Jenis_Barang;
    }

    public int getId_Kelompok_Aset() {
        return Id_Kelompok_Aset;
    }

    public void setId_Kelompok_Aset(int Id_Kelompok_Aset) {
        this.Id_Kelompok_Aset = Id_Kelompok_Aset;
    }

    public String getNama_Jenis_Barang() {
        return Nama_Jenis_Barang;
    }

    public void setNama_Jenis_Barang(String Nama_Jenis_Barang) {
        this.Nama_Jenis_Barang = Nama_Jenis_Barang;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String Keterangan) {
        this.Keterangan = Keterangan;
    }
}
