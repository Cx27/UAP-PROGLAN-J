# Aplikasi Transaksi Keuangan

Aplikasi **Transaksi Keuangan** ini dibuat dengan menggunakan JavaFX. Aplikasi ini memungkinkan pengguna untuk mencatat transaksi keuangan berupa **Pengeluaran** dan **Pemasukan** dengan detail seperti tanggal, kategori, jumlah uang, dan jenis transaksi. Semua data transaksi disimpan dan dapat dikelola dengan mudah.

## Fitur Utama

1. **Pencatatan Transaksi**:
   - Pengguna dapat membuat transaksi pengeluaran atau pemasukan dengan memilih jenis transaksi melalui radio button yang tersedia.
   - Terdapat pilihan untuk memasukkan kategori transaksi, jumlah uang, dan tanggal transaksi yang dapat dipilih menggunakan **DatePicker**.

2. **Pilihan Kategori**:
   - Aplikasi ini menyediakan dua jenis kategori transaksi: 
     - **Pengeluaran**: Termasuk kategori seperti Makanan, Mobil, Pendidikan, Pulsa, Rokok, Rumah, dan lain-lain.
     - **Pemasukan**: Termasuk kategori seperti Gaji, Tabungan, Investasi, Penghargaan, dan lainnya.
   - Pengguna dapat memilih kategori yang sesuai dengan jenis transaksi yang dipilih (pengeluaran atau pemasukan).

3. **Tanggal Transaksi**:
   - Transaksi dapat diberikan tanggal tertentu menggunakan **DatePicker**, yang secara otomatis akan mengisi tanggal saat ini sebagai default.
   - Tanggal transaksi akan diformat sesuai dengan standar lokal Indonesia (dd MMM yyyy).

4. **Validasi Input**:
   - **Jumlah transaksi** harus diisi dan harus berupa angka.
   - Aplikasi memvalidasi agar jumlah uang yang dimasukkan minimal 1, dan akan menampilkan pesan error jika input tidak valid.
   - Pesan error juga akan muncul jika jumlah yang dimasukkan bukan angka.

5. **Navigasi Sederhana**:
   - Tombol **Kembali** akan membawa pengguna kembali ke halaman sebelumnya, memungkinkan navigasi yang lebih mudah antar halaman.
   - Setelah transaksi berhasil disimpan, aplikasi akan kembali ke halaman utama.

6. **Desain GUI**:
   - Aplikasi ini menggunakan **JavaFX** untuk antarmuka grafis.
   - GUI terdiri dari komponen-komponen seperti **Button**, **Label**, **TextField**, **ComboBox**, dan **DatePicker** yang membuat pengguna dapat dengan mudah mengisi data transaksi.
   - Desain antarmuka ini memungkinkan pengguna untuk dengan cepat melakukan pencatatan transaksi.

## Prasyarat

Sebelum menjalankan aplikasi ini, pastikan Anda memiliki:
- **Java Development Kit (JDK)** versi 8 atau lebih baru.
- **JavaFX** sudah terinstal pada sistem Anda (tergantung pada cara instalasi JDK Anda).

## Struktur Direktori
/src /com /main /tubes /Database Pusat.java // Kelas untuk mengelola database transaksi /
Menu Transaksi.java // Kelas utama untuk tampilan dan pengelolaan transaksi 
Harian.java // Halaman harian atau tampilan transaksi sebelumnya

## Cara Menjalankan Aplikasi

### 1. Clone atau Download Repository

Clone atau unduh repository ini ke komputer Anda.

```bash
git clone https://github.com/username/aplikasi-transaksi.git

Atau Anda dapat mendownloadnya langsung sebagai ZIP melalui halaman repository GitHub.
