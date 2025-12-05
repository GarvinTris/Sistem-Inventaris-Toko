public class Satuan implements Interface.Inter_Satuan {
    private int Id_Satuan;
    private String Nama_Satuan;
    private String Keterangan;

    public static final String[] ListSatuan = {
            "Pcs", "Box", "Kg", "Liter", "Meter", "Pack", "Roll", "Set"
    };

    public Satuan() {
    }

    public Satuan(int Id_Satuan, String Nama_Satuan, String Keterangan) {
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

    public String getNama_Satuan() {
        return Nama_Satuan;
    }

    public void setNama_Satuan(String Nama_Satuan) {
        this.Nama_Satuan = Nama_Satuan;
    }

    public String getKeterangan() {
        return Keterangan;
    }

    public void setKeterangan(String Keterangan) {
        this.Keterangan = Keterangan;
    }

    @Override
    public boolean isValidSatuan(String input) {
        for (String satuan : ListSatuan) {
            if (satuan.equalsIgnoreCase(input)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void ShowSatuan() {
        int i = 0;
        System.out.println("Daftar Satuan Unit:");
        for (String satuan : ListSatuan) {
            i++;
            System.out.println(" " + i + ". " + satuan);
        }
    }
}
