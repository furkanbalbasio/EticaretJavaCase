![getallproduct](https://github.com/user-attachments/assets/6c371d11-6566-44b5-beb2-6f7997456850)### DOCKER REDIS KURULUMU

    docker run --name lokalredis -d -p 6379:6379 redis:7.2.3-alpine3.18
###   DOCKER DESKTOP ÜZERİNDE UYGULAMALARIMIZI YÜKLEME KOMUTLARI
    Sırası ile lokalde kurulması gerekenler
    1- docker run --name mongodb -e "MONGO_INITDB_ROOT_USERNAME=admin" -e "MONGO_INITDB_ROOT_PASSWORD=root" -p 27017:27017 mongo:7.0-rc-jammy
    1.1 kurulumun ardından mongo compas ile bağlanıp, mongosh içinde yeni bir yetkili kullanıcı oluştur
    1.1.1 db.createUser({user: "defaultUser",pwd: "bilge!*123",roles: ["readWrite", "dbAdmin"]})
    2- docker run --name postgresdb -e POSTGRES_PASSWORD=root -p 5433:5432 -d postgres
    3- docker run -d --name some-rabbit -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER="BilgeAdmin" -e RABBITMQ_DEFAULT_PASS="Aa123456" rabbitmq:3-management

![getProductByCategory](https://github.com/user-attachments/assets/3c71cbb7-a32a-4ad8-8f36-4cfb5814e3c2)![updateproduct](https://github.com/user-attachments/assets/ca800b8c-d406-44b6-9100-7fa7c2c771d5)
![addProduct](https://github.com/user-attachments/assets/2f22c2e2-bf7f-4147-9950-cca37b422f9a)
![addSeller](https://github.com/user-attachments/assets/926a42c5-89bb-4ee0-8d7d-95d102385355)
![adminauthorize](https://github.com/user-attachments/assets/46e58e1c-e53a-4459-8180-d433816af824)
![adminlogin](https://github.com/user-attachments/assets/6d2b33ff-4986-4285-96c2-7247f3921290)
![db](https://github.com/user-attachments/assets/feb82b27-de00-4ad0-9090-ca7c88f685f8)
![updateorderstatus](https://github.com/user-attachments/assets/49bcd462-b679-4708-91fa-fb8ec7ad9c55)
![getOrderByUserId](https://github.com/user-attachments/assets/a525784f-ecde-45b6-9acd-3f8c4d964ddc)
![getOrder](https://github.com/user-attachments/assets/0b426f22-63b1-4dc7-8783-68f28fc85608)
![addOrder](https://github.com/user-attachments/assets/96b24e4c-9f7f-4e9b-9a2e-cbe1a1e06060)
![register](https://github.com/user-attachments/assets/4749f241-2ae5-405a-b75a-5870651d8344)
![PostmanFoto](https://github.com/user-attachments/assets/b8f4ee18-e398-48ea-baea-700fae5561ef)
![addAddress](https://github.com/user-attachments/assets/696ba68d-088e-4dfe-ba37-b8bcca911fa1)
![updateUser](https://github.com/user-attachments/assets/38da2118-23da-46cf-8b18-828c1404aefc)
![getProfileByToken](https://github.com/user-attachments/assets/a114f0d8-160a-47c6-8ec7-0907accdc72e)
![updatestockproduct](https://github.com/user-attachments/assets/5c8ed432-f3f8-4b1a-a026-91b8b0f5bf59)
![updateproductstock](https://github.com/user-attachments/assets/81177998-a11b-4b70-8707-3c90af0008c2)

