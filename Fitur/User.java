public class User {
    private int idUser; // INT(11) PK
    private int roleUser; // INT(11)
    private String namaUser; // VARCHAR(30)
    private String username; // VARCHAR(30)
    private String jabatan; // VARCHAR(30)
    private String password; // VARCHAR(30)

    public User(int idUser, int roleUser, String namaUser, String username, String jabatan, String password) {
        this.idUser = idUser;
        this.roleUser = roleUser;
        this.namaUser = namaUser;
        this.username = username;
        this.jabatan = jabatan;
        this.password = password;
    }

    // Getter & Setter
    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(int roleUser) {
        this.roleUser = roleUser;
    }

    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void tampilkanInfo() {
        System.out.println("=== DATA USER ===");
        System.out.println("ID User   : " + idUser);
        System.out.println("Role User : " + roleUser);
        System.out.println("Nama User : " + namaUser);
        System.out.println("Username  : " + username);
        System.out.println("Jabatan   : " + jabatan);
        System.out.println("Password  : " + password);
    }
}
