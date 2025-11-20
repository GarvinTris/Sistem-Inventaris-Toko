public class Laporan {

    private int idLaporan; // INT(11)
    private User idUser; // INT(11) FK -> User
    private String namaLaporan; // VARCHAR(128)
    private int awalLaporan; // INT(11)
    private int akhirLaporan; // INT(11)
    private int dateCreated; // INT(11)

    public Laporan(int idLaporan, User idUser, String namaLaporan, int awalLaporan, int akhirLaporan, int dateCreated) {
        this.idLaporan = idLaporan;
        this.idUser = idUser;
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

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
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
