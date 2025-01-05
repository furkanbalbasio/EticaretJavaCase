DOCKER REDIS KURULUMU
    docker run --name lokalredis -d -p 6379:6379 redis:7.2.3-alpine3.18
DOCKER DESKTOP ÜZERİNDE UYGULAMALARIMIZI YÜKLEME KOMUTLARI
    Sırası ile lokalde kurulması gerekenler
    1- docker run --name mongodb -e "MONGO_INITDB_ROOT_USERNAME=admin" -e "MONGO_INITDB_ROOT_PASSWORD=root" -p 27017:27017 mongo:7.0-rc-jammy
    1.1 kurulumun ardından mongo compas ile bağlanıp, mongosh içinde yeni bir yetkili kullanıcı oluştur
    1.1.1 db.createUser({user: "defaultUser",pwd: "bilge!*123",roles: ["readWrite", "dbAdmin"]})
    2- docker run --name postgresdb -e POSTGRES_PASSWORD=root -p 5433:5432 -d postgres
    3- docker run -d --name some-rabbit -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER="BilgeAdmin" -e RABBITMQ_DEFAULT_PASS="Aa123456" rabbitmq:3-management

GET ALL PRODUCT METODU
Uygulamadaki tüm ürünleri getirmeye yarayan metottur. Metodun swagger'daki çalışma mantığı aşağıda verilmiştir.
![getallproduct](https://github.com/user-attachments/assets/6c371d11-6566-44b5-beb2-6f7997456850)

GET PRODUCT BY CATEGORY METODU
Uygulamadaki tüm ürünleri kategorilere göre sınıflandırıp getirmeye yarayan metottur. Ürün kategori listesinden kategori seçilerek hangi kategorilerin listeleneceği kullanıcı tarafından belirlenmektedir. Metodun swagger'daki çalışma mantığı aşağıda verilmiştir.
![getProductByCategory](https://github.com/user-attachments/assets/3c71cbb7-a32a-4ad8-8f36-4cfb5814e3c2)

UPDATE PRODUCT METODU
Uygulamada var olan ürünlerin güncellenmesini sağlayan metottur. Güncellenmesi istenen ürün kullanıcı tarafından id'si girilerek belirlenir. Metodun swagger'daki çalışma mantığı aşağıda verilmiştir.
![updateproduct](https://github.com/user-attachments/assets/ca800b8c-d406-44b6-9100-7fa7c2c771d5)

ADD PRODUCT METODU
Uygulamadaki ürün eklemeye yarayan metottur. Sadece satıcı ve admin uygulamaya ürün ekleme yetkisine sahiptir. Metodun swagger'daki çalışma mantığı aşağıda verilmiştir.
![addProduct](https://github.com/user-attachments/assets/2f22c2e2-bf7f-4147-9950-cca37b422f9a)

GET PRODUCT BY ID METODU
Kullanıcıdan getirmek istediği ürünün id'sini alarak ürün getirilir.
![getProductById](https://github.com/user-attachments/assets/41308b37-f135-4ab1-a636-31e6e5a4d2a7)

DELETE ORDER METODU
Seçilen sipariş id'sine göre siparişi silme işlemini yapar.
![deleteOrder](https://github.com/user-attachments/assets/fe835caf-6f5f-4998-8e99-27b6a63211af)

ADD SELLER METODU
Uygulamada kullanıcılara satıcı rolünü admin tarafından atamasını yapmaya yarayan metottur. Metodun swagger'daki çalışma mantığı aşağıda verilmiştir.
![addSeller](https://github.com/user-attachments/assets/926a42c5-89bb-4ee0-8d7d-95d102385355)

SWAGGER AUTHORIZE
Swagger'da her bir kullanıcı için işlem yapılacağı zaman bir authorize işlemi yapılmalıdır. Swagger'da görüntülü olarak gösterilmiştir.
![adminauthorize](https://github.com/user-attachments/assets/46e58e1c-e53a-4459-8180-d433816af824)

LOGIN METODU
Kullanıcıların giriş yapmak için kullanılan metottur. Bu metotta kullanıcı, kullanıcı adını ve şifresini girerek uygulamaya giriş yaparlar. Giriş yaptıklarında bir token döner ve bu token sayesinde kullanıcıların diğer metotlardaki erişimleri belirlenir.
![adminlogin](https://github.com/user-attachments/assets/6d2b33ff-4986-4285-96c2-7247f3921290)

DATABASE
Database'in tablolarının da bulunduğu genel bir görüntü ile database içeriği belirtilmiştir.
![db](https://github.com/user-attachments/assets/feb82b27-de00-4ad0-9090-ca7c88f685f8)

UPDATE ORDER STATUS METODU
Bir siparişin durumunu güncellemesini sağlayan metottur. Bu metotta sipariş durumları tamamlanmış, beklemede ve iptal edilmiş olarak güncellenebilir. Bu metoda erişim satıcı ve admin tarafından yapılabilir.
![updateorderstatus](https://github.com/user-attachments/assets/49bcd462-b679-4708-91fa-fb8ec7ad9c55)

GET ORDER BY USER ID METODU
Kullanıcının tüm siparişlerini getirmeye yarar. Siparişlerinin getirilmesi istenen kullanıcının id'si girilerek siparişlerine ulaşılır. Bu metoda satıcı ve adminin erişim yetkisi vardır.
![getOrderByUserId](https://github.com/user-attachments/assets/a525784f-ecde-45b6-9acd-3f8c4d964ddc)

GET ORDER BY ID METODU
Bu metotta id'si verilen sipariş getirilir. Bu metoda satıcı ve admin erişebilir.
![getOrder](https://github.com/user-attachments/assets/0b426f22-63b1-4dc7-8783-68f28fc85608)

ADD ORDER METODU
Sipariş oluşturmak için kullanılan metottur. Bu metotta siparişin ayrıntıları kullanıcı tarafından belirlenerek oluşturulur.
![addOrder](https://github.com/user-attachments/assets/96b24e4c-9f7f-4e9b-9a2e-cbe1a1e06060)

REGISTER METODU
Kullanıcının kayıt işlemlerinin yapıldığı metottur.
![register](https://github.com/user-attachments/assets/4749f241-2ae5-405a-b75a-5870651d8344)

POSTMAN
Redis uygulamasının postman tarafından görüntülenmesini anlatan görsele yer verilmiştir.
![PostmanFoto](https://github.com/user-attachments/assets/b8f4ee18-e398-48ea-baea-700fae5561ef)

ADD ADDRESS METODU
Kullanıcının profiline adres eklemesini sağlayan metottur.
![addAddress](https://github.com/user-attachments/assets/696ba68d-088e-4dfe-ba37-b8bcca911fa1)

UPDATE USER METODU
Kullanıcının profil bilgilerini değiştirebildiği metottur.
![updateUser](https://github.com/user-attachments/assets/38da2118-23da-46cf-8b18-828c1404aefc)

GET PROFILE BY TOKEN
Kullanıcının giriş kısmından alınan token bilgisi kullanılarak bilgilerinin görüntülendiği metottur.
![getProfileByToken](https://github.com/user-attachments/assets/a114f0d8-160a-47c6-8ec7-0907accdc72e)

UPDATE STOCK PRODUCT METODU
Bir ürünün stoklardaki miktarını değiştirmeye yarayan metottur. Bu metoda admin ve satıcının erişim hakkı vardır. Bu metoda ait 2 fotoğrafa yer verilmiştir.
![updatestockproduct](https://github.com/user-attachments/assets/5c8ed432-f3f8-4b1a-a026-91b8b0f5bf59)
![updateproductstock](https://github.com/user-attachments/assets/81177998-a11b-4b70-8707-3c90af0008c2)

