# Tugas Pemrograman 3: SistakaNG 2.0

CSGE601021 Dasar-Dasar Pemrograman 2 @ Fakultas Ilmu Komputer Universitas Indonesia,
Semester Genap 2021/2022

Dokumen ini berisi deskripsi dan informasi umum yang dibutuhkan untuk menyelesaikan Tugas Pemrograman 3 mata kuliah DDP 2.

Tugas Pemrograman ini mencakup materi berikut:
- Materi yang dicakup Tugas Pemrograman 1
- Materi yang dicakup Tugas Pemrograman 2
- *Inheritance*
- *Abstract Classes*
- *Interfaces*

## Daftar Isi

1. [Latar belakang dan deskripsi](#latar-belakang-dan-deskripsi)
2. [Persiapan](#persiapan)
3. [Menjalankan dan menguji program](#menjalankan-dan-menguji-program)
4. [Pengumpulan](#pengumpulan)
5. [Daftar pekerjaan](#daftar-pekerjaan)
    1. [Daftar pekerjaan wajib](#daftar-pekerjaan-wajib)
6. [Penilaian](#penilaian)


## Latar belakang dan deskripsi

Silakan membaca [dokumen soal][dokumen tp3]

## Persiapan

Silakan ikuti persiapan yang ada pada berkas [`README.md`][root-readme] di
*root* repositori ini.

> Template pengerjaan tugas ini disediakan di *path*
`src\main\java\assignments\assignment3`

## Menjalankan dan menguji program

> Catatan:<br>
> - Ubah `gradlew.bat` dengan `./gradlew` dan `\` dengan `/` jika kamu
    menggunakan Linux/Mac.
> - Tanda <kbd>></kbd> pada awal *command* tidak perlu ditulis.
    Digunakan sebagai pembeda antara *command* dan *output*-nya

Kamu bisa menjalankan beberapa *task* dengan Gradle melalui IDE atau
terminal/Command Prompt.
Jalankan *task* tersebut di root folder dari repository ini.

Untuk menjalankan program:

```bash
> gradlew.bat :assignment3:run --console plain
```

Untuk melihat output program dengan *test case* input yang disediakan:

```bash
> gradlew.bat :assignment3:run --console plain --quiet < assignment3\testcases\in\in1.txt
> gradlew.bat :assignment3:run --console plain --quiet < assignment3\testcases\in\in2.txt
```

*Hint*: kamu juga bisa menggunakan `>` setelah penjalanan command diatas untuk menyimpan output ke dalam sebuah *file* (lampirkan juga nama file yang akan dijadikan tempat peletakan output).

Contoh:
```bash
> gradlew.bat :assignment3:run --console plain --quiet < assignment3\testcases\in\in1.txt > assignment3\testcases\out-mahasiswa\out1.txt
```

## Pengumpulan
Simpan pekerjaan kamu, lalu lakukan `add`, `commit`, dan `push` dengan Git.
*Push* solusi kamu **sebelum 29 April 2022 pukul 23.55**. Disarankan untuk
*push* **sebelum pukul 22.00** untuk mengantisipasi antrean pada GitLab
Pipelines.

## Daftar pekerjaan

### Daftar pekerjaan wajib
- [ ] Buat sekurang-kurangnya 1 *commit* yang mengandung progres kamu dalam
  mengerjakan Tugas Pemrograman 3.
- [ ] *Push* *commit* kamu ke repositori Git *online* yang ada pada proyek
  GitLab kamu.
- [ ] Implementasikan semua *class* dengan benar.
- [ ] Demonstrasikan program kamu ke asdos kamu.


## Penilaian

| Kriteria | Keterangan   | Max Point |
|-----------|--------|-----------|
| Testcases | Dinilai berdasarkan kelulusan testcase | 20 |
| Atribut pada kelas Pengguna, Staf, Anggota, Mahasiswa, dan Dosen | Memiliki modifier yang sesuai dengan UML | 10 |
| Method pada kelas Pengguna, Staf, Anggota, Mahasiswa, Dosen, dan CanBorrow | Memiliki modifier dan implementasi yang tepat | 15 |
| Kelas Pengguna, Staf, Anggota, Mahasiswa, Dosen, dan CanBorrow | Memiliki modifier serta melakukan extends dan implements yang sesuai dengan UML | 6 |
| Atribut pada kelas Peminjaman, Buku, Kategori dan SistakaNG | Memiliki modifier yang sesuai | 4 |
| Method pada kelas Peminjaman, Buku, dan Kategori | Memiliki modifier dan implementasi yang tepat | 5 |
| Dokumentasi  | - | 5 |
| Standar Penulisan Kode  | Tiga kriteria standar kode: 1. Indentasi yang konsisten; 2. Aturan penamaan harus mengikuti Java Naming Convention; 3. Penamaan Module, Class, Method, dan Variabel yang tidak ambigu | 5 |
| Presentasi Demo  | - | 21 |
| Pertanyaan Demo  | - | 9 |


***Selamat Mengerjakan!***

[dokumen tp3]: https://docs.google.com/document/d/1Sep-ps1rxpx8OlIV_8sH8foNdXFPYz7fTUnPrsOyoqc/edit?usp=sharing
[root-readme]: ../README.md#memulai
