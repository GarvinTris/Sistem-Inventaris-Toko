public class Perhitungan_Hasil {
    public int id_perhitungan_hasil;
    public Perhitungan_Fisik id_perhitungan_fisik;
    public Jenis_Barang id_jenis_barang;
    public int jumlah_perhitungan_fisik;
    public int stok_sistem;

    public Perhitungan_Hasil(int id_perhitungan_hasil, Perhitungan_Fisik id_perhitungan_fisik,
            Jenis_Barang id_jenis_barang, int jumlah_perhitungan_fisik, int stok_sistem) {
        this.id_perhitungan_hasil = id_perhitungan_hasil;
        this.id_perhitungan_fisik = id_perhitungan_fisik;
        this.id_jenis_barang = id_jenis_barang;
        this.jumlah_perhitungan_fisik = jumlah_perhitungan_fisik;
        this.stok_sistem = stok_sistem;
    }

    public int getId_perhitungan_hasil() {
        return id_perhitungan_hasil;
    }

    public void setId_perhitungan_hasil(int id_perhitungan_hasil) {
        this.id_perhitungan_hasil = id_perhitungan_hasil;
    }

    public Perhitungan_Fisik getId_perhitungan_fisik() {
        return id_perhitungan_fisik;
    }

    public void setId_perhitungan_fisik(Perhitungan_Fisik id_perhitungan_fisik) {
        this.id_perhitungan_fisik = id_perhitungan_fisik;
    }

    public Jenis_Barang getId_jenis_barang() {
        return id_jenis_barang;
    }

    public void setId_jenis_barang(Jenis_Barang id_jenis_barang) {
        this.id_jenis_barang = id_jenis_barang;
    }

    public int getJumlah_perhitungan_fisik() {
        return jumlah_perhitungan_fisik;
    }

    public void setJumlah_perhitungan_fisik(int jumlah_perhitungan_fisik) {
        this.jumlah_perhitungan_fisik = jumlah_perhitungan_fisik;
    }

    public int getStok_sistem() {
        return stok_sistem;
    }

    public void setStok_sistem(int stok_sistem) {
        this.stok_sistem = stok_sistem;
    }

}

