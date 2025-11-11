public class Master {
  
    private int idMaster;       // INT(11)
    private User idUser;         // INT(11) FK -> User
    private int noMaster;       // INT(11)
    private String file;        // VARCHAR(50)
    private int status;         // INT(11)
    private int tanggalInput;   // INT(11)
    private int dateCreated;    // INT(11)

  
    public Master(int idMaster, int idUser, int noMaster, String file, int status, int tanggalInput, int dateCreated) {
        this.idMaster = idMaster;
        this.idUser = idUser;
        this.noMaster = noMaster;
        this.file = file;
        this.status = status;
        this.tanggalInput = tanggalInput;
        this.dateCreated = dateCreated;
    }

   
    public int getIdMaster() {
        return idMaster;
    }

    public void setIdMaster(int idMaster) {
        this.idMaster = idMaster;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getNoMaster() {
        return noMaster;
    }

    public void setNoMaster(int noMaster) {
        this.noMaster = noMaster;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTanggalInput() {
        return tanggalInput;
    }

    public void setTanggalInput(int tanggalInput) {
        this.tanggalInput = tanggalInput;
    }

    public int getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(int dateCreated) {
        this.dateCreated = dateCreated;
    }

   
    public void tampilkanMaster(User user) {
        System.out.println("=== MASTER DATA ===");
        System.out.println("ID Master     : " + idMaster);
        System.out.println("No Master     : " + noMaster);
        System.out.println("File          : " + file);
        System.out.println("Status        : " + status);
        System.out.println("Tanggal Input : " + tanggalInput);
        System.out.println("Date Created  : " + dateCreated);
        System.out.println("Dibuat oleh   : " + user.getNama());
    }
}
