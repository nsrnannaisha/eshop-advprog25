**Nama  : Nisrina Annaisha Sarnadi   
NPM   : 2306275960  
Kelas : B**


## Reflection 1
***

### Clean Code Principle
Clean Code merupakan sebuah konsep yang menekankan pada penulisan kode yang mudah dibaca, dipahami, dan dipelihara. Pada kode saya, saya telah menerapkan beberapa _principles_, yaitu:
1. _Meaningful Names_  
   Nama _class_ seperti ```ProductController```, ```ProductService```, dan ```ProductRepository``` sudah mencerminkan tanggung jawab masing-masing class.
   Variabel seperti ```productData``` dan ```allProducts``` jelas menggambarkan isi dan tujuan penggunaannya.
   Selain itu, nama-nama _method_ seperti ```findProductById```, ```create```, dan lain-lain sudah sesuai dengan fungsinya.
2. Single Responsibility Principle (SRP)
   Setiap class memiliki satu tanggung jawab. Misalnya:
   - ```Product``` sebagai model data.
   - ```ProductRepository``` untuk mengelola akses data.
   - ```ProductService``` sebagai layer bisnis.
   - ```ProductController``` sebagai layer kontroler untuk menangani request dari user.
3. _Separation of Concerns (SoC)_  
   Memisahkan _package_ Model, Repository, Service, dan Controller yang membuat kode modular dan mudah diperluas.
4. Menghindari Pengulangan Kode  
   _Method_ ```findProductById``` digunakan kembali untuk _edit_ dan _delete_, menghindari duplikasi kode.
5. Tidak Berlebihan dalam Memberikan Komentar  
   Komentar diberikan tidak berlebihan karena kode cukup jelas untuk dibaca tanpa perlu komentar tambahan

### Secure Coding Practices:
Secure Coding Practices adalah serangkaian prinsip, teknik, dan pedoman untuk menulis kode yang aman dan mengurangi kerentanan terhadap serangan siber. Hal ini saya terapkan pada:
1. Mengganti ID Produk Menjadi UUID  
   Pada file ```Product.java```, id produk di-_generate_ dengan UUID untuk meningkatkan keamanan dan dan menghindari enumerasi oleh user.
2. Memvalidasi _input quantity_ produk  
   Untuk memastikan nilai dari ```productQuantity``` tidak negatif, telah ditambahkan validasi sederhana.

Setelah mengikuti tutorial, terdapat beberapa aspek yang masih dapat ditingkatkan, khususnya dalam hal error handling dan validasi.
Penanganan kesalahan perlu seharusnya bisa lebih aman untuk menghindari potensi error yang mungkin muncul.
Selain itu, validasi input juga perlu diperketat agar hanya data valid yang diproses, serta mekanisme pengelolaan data harus lebih aman untuk menghindari potensi kerentanan keamanan.

## Reflection 2
***

### 1. Unit Test
**After writing the unit test, how do I feel?**  
Setelah menulis unit test, saya merasa lebih memahami bagaimana setiap bagian kecil dari program saya bekerja.
Menulis unit test memberikan rasa percaya diri karena saya tahu bahwa fungsi-fungsi dalam kode saya sudah diuji dan berjalan seperti yang diharapkan.

**How many unit tests should be made in a class?**  
Jumlah unit test dalam satu kelas bersifat fleksibel, tergantung pada seberapa kompleks kelas tersebut.
Unit test harus menguji semua logika dan variasi input yang mungkin diterima oleh sistem sehingga pengembang dapat memastikan bahwa setiap bagian kode sudah berfungsi dengan benar.

**How to make sure that our unit tests are enough to verify our program?**   
Untuk memastikan unit test kita sudah cukup, kita bisa menggunakan code coverage sebagai alat bantu.
Code coverage akan menunjukkan bagian mana dari kode kita yang sudah diuji dan mana yang belum.
Namun, angka code coverage tinggi tidak berarti semua bug sudah tertangkap.
Kita juga harus memastikan bahwa semua skenario penting sudah diuji, termasuk edge cases dan bagaimana program menangani kesalahan (error handling).
Selain itu, kita dapat memastikannya dengan menulis unit test sejak awal saat membuat kode (TDD) dan membuat negative tests untuk menguji input yang tidak valid.

**If you I 100% code coverage, does that mean my code has no bugs or errors?**
Tidak. Meskipun sudah mencapai 100% code coverage, hal itu tidak menjamin kode bebas bug.
Code coverage hanya memastikan bahwa semua baris kode dieksekusi, tetapi tidak menjamin bahwa semua skenario telah diuji.
Untuk memastikan kualitas, kita harus menulis unit test yang memeriksa hasil yang diharapkan, kondisi error, dan edge case, serta melengkapi dengan integration test dan end-to-end test.

### 2. Functional test
**What do I think about the cleanliness of the code of the new functional test suite?**  
_Functional test suite_ baru dengan struktur yang mirip dengan ```CreateProductFunctionalTest.java``` bisa membuat kode jadi kurang rapi.
Hal tersebut karena terdapat bagian kode yang sama.
Jika semua functional test memiliki banyak bagian kode yang sama, kode menjadi panjang, susah dibaca, dan sulit dirawat jika ada perubahan.

**Will the new code reduce the code quality?**  
Ya, kode baru dapat menurunkan kualitas jika terdapat banyak pengulangan sehingga jika diperlukan perubahan bagian, _file_ dengan bagian kode yang sama harus diubah pula.  
Hal tersebut dapat menimbulkan kesalahan yang sulit ditemukan dan memperlambat pengembangan.

**Potensi isu clean code:**
- Duplikasi Kode: Menulis ulang bagian yang sama di banyak file dapat meningkatkan risiko kesalahan.
- Sulit Dipelihara: Jika terjadi perubahan, semua file terkait harus diperiksa dan diperbarui, yang memakan waktu dan rentan terhadap kesalahan.
- Kurang Fleksibel: Kode yang berulang menjadi sulit digunakan kembali untuk kebutuhan lain.

**Potential clean code issues:**
- Duplikasi Kode: Penulisan ulang bagian kode yang sama di banyak file dapat menyebabkan pemborosan dan meningkatkan risiko terjadinya kesalahan.
- Sulit Dipelihara: Jika terdapat perubahan, seluruh file terkait harus diperiksa dan diperbarui, yang dapat menghabiskan waktu serta rentan terhadap kesalahan.
- Kurang Fleksibel: Kode yang berulang menjadi kurang modular dan sulit digunakan kembali untuk keperluan lainnya.

**Improvement suggestions**
- Penggunaan Inheritance: Membuat kelas dasar seperti ```BaseFunctionalTest.java``` yang berisi seluruh pengaturan awal dan variabel, sehingga functional test lainnya dapat mewarisi kelas ini tanpa perlu menulis ulang.
- Pemanfaatan Utility Class atau Helper Method: Kedua hal ini dapat membantu jika terdapat proses yang sering digunakan sehingga tidak diperlukan pengulangan.
- Menerapkan Parameterized Test di JUnit: Untuk menghindari pengulangan pada pengujian yang serupa, Parameterized Test dapat digunakan agar pengujian lebih efisien dan terstruktur.