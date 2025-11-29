import java.time.LocalDate;

public class Barang extends Data {
    int id_barang;
    Satuan id_satuan;
    int kode_barang;
    String nama_barang;
    int stock;
    float harga_barang;
    int aset_tetap;
    String keterangan;
    LocalDate created_at;
    Jenis_Barang jenis_barang;

    public Barang(int id_barang, Satuan id_satuan, int kode_barang, String nama_barang, int stock, float harga_barang,
            int aset_tetap, String keterangan, LocalDate created_at) {
        this.id_barang = id_barang;
        this.id_satuan = id_satuan;
        this.kode_barang = kode_barang;
        this.nama_barang = nama_barang;
        this.stock = stock;
        this.harga_barang = harga_barang;
        this.aset_tetap = aset_tetap;
        this.keterangan = keterangan;
        this.created_at = LocalDate.now();
    }

    public int getId_barang() {
        return id_barang;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public Satuan getId_satuan() {
        return id_satuan;
    }

    public void setId_satuan(Satuan id_satuan) {
        this.id_satuan = id_satuan;
    }

    public int getKode_barang() {
        return kode_barang;
    }

    public void setKode_barang(int kode_barang) {
        this.kode_barang = kode_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(float harga_barang) {
        this.harga_barang = harga_barang;
    }

    public int getAset_tetap() {
        return aset_tetap;
    }

    public void setAset_tetap(int aset_tetap) {
        this.aset_tetap = aset_tetap;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public LocalDate getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDate created_at) {
        this.created_at = created_at;
    }

    public Jenis_Barang getJenisBarang() {
        return jenis_barang;
    }

    public void setJenisBarang(Jenis_Barang jenisBarang) {
        this.jenis_barang = jenisBarang;
    }

    @Override
    public String getSerial() {
        throw new UnsupportedOperationException("Unimplemented method 'getSerial'");
    }

    @Override
    public String getTanggal_masuk() {
        throw new UnsupportedOperationException("Unimplemented method 'getTanggal_masuk'");
    }

    @Override
    public String getKondisi() {
        throw new UnsupportedOperationException("Unimplemented method 'getKondisi'");
    }

}
