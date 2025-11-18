public class User {
    private int idUser; // INT(11) PK
    private String nama; // VARCHAR(100)
    private String username; // VARCHAR(50)
    private String email; // VARCHAR(100)
    private String noTelepon; // VARCHAR(20)
    private String alamat; // VARCHAR(100)

    public User(int idUser, String nama, String username, String email, String noTelepon, String alamat) {
        this.idUser = idUser;
        this.nama = nama;
        this.username = username;
        this.email = email;
        this.noTelepon = noTelepon;
        this.alamat = alamat;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void tampilkanInfo() {
        System.out.println("=== DATA USER ===");
        System.out.println("ID User    : " + idUser);
        System.out.println("Nama       : " + nama);
        System.out.println("Username   : " + username);
        System.out.println("Email      : " + email);
        System.out.println("No Telepon : " + noTelepon);
        System.out.println("Alamat     : " + alamat);
    }
}
