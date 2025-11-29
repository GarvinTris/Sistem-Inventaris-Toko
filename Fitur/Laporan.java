public class Laporan {

    private int idLaporan;
    private String namaLaporan;
    private int awalLaporan;
    private int akhirLaporan;
    private int dateCreated;

    public Laporan(int idLaporan, String namaLaporan, int awalLaporan, int akhirLaporan, int dateCreated) {
        this.idLaporan = idLaporan;
        this.namaLaporan = namaLaporan;
        this.awalLaporan = awalLaporan;
        this.akhirLaporan = akhirLaporan;
        this.dateCreated = dateCreated;
    }

    public int getIdLaporan() {
        return idLaporan;
    }

    public void setIdLaporan(int idLaporan) {
        this.idLaporan = idLaporan;
    }

    public String getNamaLaporan() {
        return namaLaporan;
    }

    public void setNamaLaporan(String namaLaporan) {
        this.namaLaporan = namaLaporan;
    }

    public int getAwalLaporan() {
        return awalLaporan;
    }

    public void setAwalLaporan(int awalLaporan) {
        this.awalLaporan = awalLaporan;
    }

    public int getAkhirLaporan() {
        return akhirLaporan;
    }

    public void setAkhirLaporan(int akhirLaporan) {
        this.akhirLaporan = akhirLaporan;
    }

    public int getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(int dateCreated) {
        this.dateCreated = dateCreated;
    }
}
