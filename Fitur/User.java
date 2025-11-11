class User {
  constructor(id_user, nama, username, email, no_telepon, alamat) {
    this.id_user = id_user;
    this.nama = nama;
    this.username = username;
    this.email = email;
    this.no_telepon = no_telepon;
    this.alamat = alamat;
  }

  tampilkanInfo() {
    console.log(`=== DATA USER ===`);
    console.log(`ID User     : ${this.id_user}`);
    console.log(`Nama        : ${this.nama}`);
    console.log(`Username    : ${this.username}`);
    console.log(`Email       : ${this.email}`);
    console.log(`No Telepon  : ${this.no_telepon}`);
    console.log(`Alamat      : ${this.alamat}`);
  }
}

// === Contoh penggunaan ===
const user1 = new User(1, "Syafira Naura", "syafira123", "syafira@mail.com", "081234567890", "Medan");
user1.tampilkanInfo();

export default User;
