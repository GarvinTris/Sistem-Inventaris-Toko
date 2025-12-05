public class Jenis_Barang {

    private int Id_Jenis_Barang;
    private ID_Kelompok_Aset KelompokAset;
    private String Nama_Jenis_Barang;
    private String Keterangan;

    public Jenis_Barang() {
    }

    public Jenis_Barang(int Id_Jenis_Barang, ID_Kelompok_Aset KelompokAset,
            String Nama_Jenis_Barang, String Keterangan) {
        this.Id_Jenis_Barang = Id_Jenis_Barang;
        this.KelompokAset = KelompokAset;
        this.Nama_Jenis_Barang = Nama_Jenis_Barang;
        this.Keterangan = Keterangan;
    }

    public int getId_Jenis_Barang() {
        return Id_Jenis_Barang;
    }

    public void setId_Jenis_Barang(int Id_Jenis_Barang) {
        this.Id_Jenis_Barang = Id_Jenis_Barang;
    }

    public ID_Kelompok_Aset getKelompokAset() {
        return KelompokAset;
    }

    public void setKelompokAset(ID_Kelompok_Aset KelompokAset) {
        this.KelompokAset = KelompokAset;
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
