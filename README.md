**Nama  : Nisrina Annaisha Sarnadi   
NPM   : 2306275960  
Kelas : B**

> Link Deployment: https://advshop-aisss.koyeb.app/

# Module 1
## Reflection 1

### Clean Code Principle
_Clean Code_ merupakan sebuah konsep yang menekankan pada penulisan kode yang mudah dibaca, dipahami, dan dipelihara. Pada kode saya, saya telah menerapkan beberapa _principles_, yaitu:
1. _Meaningful Names_  
   Nama _class_ seperti ```ProductController```, ```ProductService```, dan ```ProductRepository``` sudah mencerminkan tanggung jawab masing-masing _class_.
   Variabel seperti ```productData``` dan ```allProducts``` jelas menggambarkan isi dan tujuan penggunaannya.
   Selain itu, nama-nama _method_ seperti ```findProductById```, ```create```, dan lain-lain sudah sesuai dengan fungsinya.
2. _Single Responsibility Principle_ (SRP)
   Setiap class memiliki satu tanggung jawab. Misalnya:
   - ```Product``` sebagai model data.
   - ```ProductRepository``` untuk mengelola akses data.
   - ```ProductService``` sebagai _layer_ bisnis.
   - ```ProductController``` sebagai layer kontroler untuk menangani _request_ dari _user_.
3. _Separation of Concerns (SoC)_  
   Memisahkan _package_ Model, Repository, Service, dan Controller membuat kode lebih terstruktur dan mudah dikembangkan.
4. Menghindari Pengulangan Kode  
   _Method_ ```findProductById``` digunakan kembali untuk _edit_ dan _delete_, menghindari duplikasi kode.
5. Tidak Berlebihan dalam Memberikan Komentar  
   Komentar diberikan tidak berlebihan karena kode cukup jelas untuk dibaca tanpa perlu komentar tambahan

### Secure Coding Practices:
_Secure Coding Practices_ adalah serangkaian prinsip, teknik, dan pedoman untuk menulis kode yang aman dan mengurangi kerentanan terhadap serangan siber. Hal ini saya terapkan pada:
1. Mengganti ID Produk Menjadi UUID  
   Pada _file_ ```Product.java```, id produk di-_generate_ dengan UUID untuk meningkatkan keamanan dan dan menghindari enumerasi oleh user.
2. Memvalidasi _input quantity_ produk  
   Untuk memastikan nilai dari ```productQuantity``` tidak negatif, telah ditambahkan validasi sederhana.

Setelah mengikuti tutorial, terdapat beberapa aspek yang masih dapat ditingkatkan, khususnya dalam hal _error handling_ dan validasi.
Penanganan kesalahan perlu seharusnya bisa lebih aman untuk menghindari potensi _error_ yang mungkin muncul.
Selain itu, validasi _input_ juga perlu diperketat agar hanya data valid yang dapat diproses, serta mekanisme pengelolaan data harus lebih aman untuk menghindari potensi kerentanan keamanan.

## Reflection 2

### 1. Unit Test
**After writing the unit test, how do I feel?**  
Setelah menulis _unit test_, saya merasa lebih memahami cara kerja program saya.
Menulis _unit test_ memberikan rasa percaya diri karena saya tahu bahwa fungsi-fungsi dalam kode saya sudah diuji dan berjalan sesuai harapan.

**How many unit tests should be made in a class?**  
Jumlah _unit test_ dalam satu kelas bersifat fleksibel, tergantung pada tingkat kompleksitas kelas tersebut.
_Unit test_ harus dapat mencakup semua logika dan variasi _input_ yang mungkin diterima oleh sistem agar pengembang dapat memastikan bahwa setiap bagian kode sudah berfungsi dengan benar.

**How to make sure that our unit tests are enough to verify our program?**   
Untuk memastikan _unit test_ cukup memverifikasi program, kita dapat menargetkan tercapainya _code coverage_ pada bagian kritis. Selain itu, penting untuk menulis _test case_ yang mencakup berbagai skenario, serta memastikan semua cabang kode diuji dengan baik. Pendekatan _Test-Driven Development_ (TDD) juga membantu memastikan setiap fungsi diuji sejak awal pengembangan dan _negative testing_ memastikan program mampu menangani _input_ tidak valid. Melakukan _review_ dan _refactor test_ secara berkala, serta mengotomatisasi _unit test_ melalui integrasi ke dalam _CI/CD pipeline_, juga akan menjaga tes tetap relevan dan memastikan pengujian dilakukan secara konsisten setiap ada perubahan kode.

**If I have 100% code coverage, does that mean my code has no bugs or errors?**
Tidak. Meskipun _code coverage_ mencapai 100%, bukan berarti kode bebas dari _bug_ atau _error_. _Code coverage_ hanya memastikan semua baris kode telah dijalankan, namun tidak menjamin semua skenario telah diuji. Untuk memastikan kualitas kode, diperlukan _unit test_ yang menguji hasil yang diharapkan, penanganan _error_, dan _edge cas_e, serta dilengkapi dengan _integration test_ dan _end-to-end test_.

### 2. Functional test
**What do I think about the cleanliness of the code of the new functional test suite?**  
Menurut saya, membuat _functional test suite_ baru dengan struktur mirip seperti `CreateProductFunctionalTest.java` dapat menyebabkan kode menjadi kurang rapi jika terdapat banyak bagian kode yang sama. Jika semua _functional test_ memiliki banyak duplikasi kode, hal ini akan membuat kode menjadi panjang, sulit dibaca, dan menyulitkan perawatan ketika ada perubahan.

**Will the new code reduce the code quality?**  
Ya, kode baru dapat menurunkan kualitas jika terdapat banyak duplikasi, karena setiap perubahan harus dilakukan di beberapa _file_ kode sekaligus. Hal ini dapat meningkatkan risiko kesalahan yang sulit dideteksi dan memperlambat proses pengembangan.
  
**Potential clean code issues**
- Duplikasi Kode: Mengulang bagian kode yang sama di banyak _file_ dapat menyebabkan pemborosan dan meningkatkan risiko kesalahan.
- Sulit Dipelihara: Perubahan pada satu bagian kode mengharuskan pembaruan di semua _file_ terkait, yang memakan waktu dan rawan kesalahan.
- Kurang Fleksibel: Kode yang berulang menjadi sulit digunakan kembali untuk fungsionalitas lain.

**Improvement suggestions**
- Menggunakan _design pattern_ seperti _factory method_ untuk menghasilkan objek yang sering digunakan dalam tes. Hal ini mengurangi duplikasi kode dan memudahkan perubahan di satu tempat.
- Memisahkan konfigurasi (URL, kredensial, atau _environment variables_) ke _file_ terpisah (.properties atau .yaml) agar kode lebih tertata dan mudah diubah.
- Menerapkan _dependency injection_ (Spring atau Guice) untuk mengelola dependensi dalam tes yang memudahkan pengaturan objek dan membuat kode lebih fleksibel.
- Gunakan _annotation_ untuk _setup_ yang umum (@SetupEnvironment) untuk menangani inisialisasi atau konfigurasi yang sering diulang agar duplikasi kode berkurang dan membuat tes lebih rapi.
- Menerapkan _modular testing_, yaitu memisahkan _test case_ ke dalam modul-modul kecil yang independen yang dapat memudahkan pemeliharaan dan penggunaan kembali kode.

# Module 2
## Reflection

### Code Quality Issues
1. _Method_ kosong tanpa implementasi  
-> ```ProductRepositoryTest.java```     
Terdapat _method_ ```setUp()``` yang kosong tanpa implementasi. _Method_ diperbaiki dengan menambahkan _comment_ yang menjelaskan alasan _method_ tersebut kosong.
2. _Modifier public_ yang tidak diperlukan   
-> ```ProductRepositoryTest.java```, ```ProductTest.java```, ```CreateProductFunctionalTest.java```  
Terdapat _class_ dengan _public modifier_ yang tidak diperlukan untuk _testing_. _Class_ diperbaiki dengan menghapus _modifier public_ tersebut.
3. ```throws Exception``` yang tidak diperlukan  
-> ```HomePageFunctionalTest.java```, ```ProductFunctionalTest.java```  
Terdapat _method_ dengan ```throws exception``` yang tidak diperlukan untuk _testing_. _Method_ diperbaiki dengan menghapus ```throws Exception``` tersebut.
4. Penggunaan _field injection_  
-> ```ProductServiceImpl.java```, ```ProductController.java```  
Terdapat dependensi yang di-_inject_ langsung ke dalam _field_ menggunakan anotasi ```@Autowired```. Dependesi diperbaiki dengan digantinya dependensi menjadi konstruktor _injection_ agar lebih aman dan mudah diuji.
5. _Dependency_ tidak terkelompok dengan baik   
-> ```build.gradle.kts```  
_Dependencies_ ini tidak dikelompokkan berdasarkan tujuan penggunaannya. Dependesi diperbaiki dengan mengelompokkannya menjadi kelompok yang lebih terstruktur.

### CI/CD Workflows Implementation
Ya, implementasi CI/CD saat ini telah memenuhi definisi _Continuous Integration_ (CI) dan _Continuous Deployment_ (CD). Setiap kali ada perubahan kode, _workflow_ otomatis menjalankan pengujian dan analisis kualitas kode untuk memastikan bahwa kode yang digabungkan ke _repository_ utama tetap stabil dan tidak menyebabkan _error_ yang tidak terdeteksi. Dalam proses ini, `ci.yml` berfungsi untuk menjalankan pengujian otomatis, `scorecard.yml` untuk analisis keamanan dan kualitas kode, serta `sonarcloud.yml` yang berfungsi untuk integrasi dengan SonarCloud dalam mengevaluasi _code quality_. Selain itu, `Dockerfile` digunakan untuk membangun _container image_ yang nantinya akan digunakan dalam proses _deployment_. Setelah pengujian berhasil, kode akan langsung di-_deploy_ ke PaaS (Koyeb) tanpa perlu perubahan secara manual. Dengan demikian, setiap perubahan kode dapat diuji dan di-_deploy_ dengan lebih efisien. 
