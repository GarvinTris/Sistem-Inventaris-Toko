import java.time.LocalDate;

public class Perhitungan_Fisik {
    public int id_perhitungan_fisik;
    public int no_perhitungan_fisik;
    public String file;
    public LocalDate tanggal_perhitungan;

    public Perhitungan_Fisik(int id_perhitungan_fisik, int no_perhitungan_fisik, String file,
            LocalDate tanggal_perhitungan) {
        this.id_perhitungan_fisik = id_perhitungan_fisik;
        this.no_perhitungan_fisik = no_perhitungan_fisik;
        this.file = file;
        this.tanggal_perhitungan = tanggal_perhitungan;
    }

    public int getId_perhitungan_fisik() {
        return id_perhitungan_fisik;
    }

    public void setId_perhitungan_fisik(int id_perhitungan_fisik) {
        this.id_perhitungan_fisik = id_perhitungan_fisik;
    }

    public int getNo_perhitungan_fisik() {
        return no_perhitungan_fisik;
    }

    public void setNo_perhitungan_fisik(int no_perhitungan_fisik) {
        this.no_perhitungan_fisik = no_perhitungan_fisik;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public LocalDate getTanggal_perhitungan() {
        return tanggal_perhitungan;
    }

    public void setTanggal_perhitungan(LocalDate tanggal_perhitungan) {
        this.tanggal_perhitungan = tanggal_perhitungan;
    }

}
