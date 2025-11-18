public class Satuan {
    private int Id_Satuan;
    private int Nama_Satuan;
    private String Keterangan;
  
    public Satuan() {
    }

    public Satuan(int Id_Satuan, int Nama_Satuan, String Keterangan) {
        this.Id_Satuan = Id_Satuan;
        this.Nama_Satuan = Nama_Satuan;
        this.Keterangan = Keterangan;
    }

    public int getId_Satuan() {
        return Id_Satuan;
    }

    public void setId_Satuan(int Id_Satuan) {
        this.Id_Satuan = Id_Satuan;
    }

    public int getNama_Satuan() {
        return Nama_Satuan;
    }

    public void setNama_Satuan(int Nama_Satuan) {
        this.Nama_Satuan = Nama_Satuan;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String Keterangan) {
        this.Keterangan = Keterangan;
    }
}
