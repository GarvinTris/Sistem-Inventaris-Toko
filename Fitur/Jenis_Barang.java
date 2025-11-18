public class Jenis_Barang {
    public int id_jenis_barang;
    public Kelompok_Aset id_kelompok_aset;
    public String nama_jenis_barang;
    public string keterangan;

    public Jenis_Barang(int id_jenis_barang, Kelompok_Aset id_kelompok_aset, String nama_jenis_barang,
            String keterangan) {
        this.id_jenis_barang = id_jenis_barang;
        this.id_kelompok_aset = id_kelompok_aset;
        this.nama_jenis_barang = nama_jenis_barang;
        this.keterangan = keterangan;
    }

    public int getId_jenis_barang() {
        return id_jenis_barang;
    }

    public void setId_jenis_barang(int id_jenis_barang) {
        this.id_jenis_barang = id_jenis_barang;
    }

    public Kelompok_Aset getId_kelompok_aset() {
        return id_kelompok_aset;
    }

    public void setId_kelompok_aset(Kelompok_Aset id_kelompok_aset) {
        this.id_kelompok_aset = id_kelompok_aset;
    }

    public String getNama_jenis_barang() {
        return nama_jenis_barang;
    }

    public void setNama_jenis_barang(String nama_jenis_barang) {
        this.nama_jenis_barang = nama_jenis_barang;
    }

    public string getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(string keterangan) {
        this.keterangan = keterangan;
    }

}

