### DOCKER REDIS KURULUMU

    docker run --name lokalredis -d -p 6379:6379 redis:7.2.3-alpine3.18
###   DOCKER DESKTOP ÜZERİNDE UYGULAMALARIMIZI YÜKLEME KOMUTLARI
    Sırası ile lokalde kurulması gerekenler
    1- docker run --name mongodb -e "MONGO_INITDB_ROOT_USERNAME=admin" -e "MONGO_INITDB_ROOT_PASSWORD=root" -p 27017:27017 mongo:7.0-rc-jammy
    1.1 kurulumun ardından mongo compas ile bağlanıp, mongosh içinde yeni bir yetkili kullanıcı oluştur
    1.1.1 db.createUser({user: "defaultUser",pwd: "bilge!*123",roles: ["readWrite", "dbAdmin"]})
    2- docker run --name postgresdb -e POSTGRES_PASSWORD=root -p 5433:5432 -d postgres
    3- docker run -d --name some-rabbit -p 5672:5672 -p 15672:15672 -e RABBITMQ_DEFAULT_USER="BilgeAdmin" -e RABBITMQ_DEFAULT_PASS="Aa123456" rabbitmq:3-management
    
![adminlogin.JPG](..%2F..%2F..%2F..%2FDownloads%2Fadminlogin.JPG)
![adminauthorize.JPG](..%2F..%2F..%2F..%2FDownloads%2Fadminauthorize.JPG)
![addSeller.JPG](..%2F..%2F..%2F..%2FDownloads%2FaddSeller.JPG)
![addProduct.JPG](..%2F..%2F..%2F..%2FDownloads%2FaddProduct.JPG)
![updateproduct.JPG](..%2F..%2F..%2F..%2FDownloads%2Fupdateproduct.JPG)
![getProductById.JPG](..%2F..%2F..%2F..%2FDownloads%2FgetProductById.JPG)
![getallproduct.JPG](..%2F..%2F..%2F..%2FDownloads%2Fgetallproduct.JPG)
![getProductByCategory.JPG](..%2F..%2F..%2F..%2FDownloads%2FgetProductByCategory.JPG)
![updateproductstock.JPG](..%2F..%2F..%2F..%2FDownloads%2Fupdateproductstock.JPG)
![updatestockproduct.JPG](..%2F..%2F..%2F..%2FDownloads%2Fupdatestockproduct.JPG)
![getProfileByToken.JPG](..%2F..%2F..%2F..%2FDownloads%2FgetProfileByToken.JPG)
![updateUser.JPG](..%2F..%2F..%2F..%2FDownloads%2FupdateUser.JPG)
![addAddress.JPG](..%2F..%2F..%2F..%2FDownloads%2FaddAddress.JPG)
![PostmanFoto.JPG](..%2F..%2F..%2F..%2FDownloads%2FPostmanFoto.JPG)
![register.JPG](..%2F..%2F..%2F..%2FDownloads%2Fregister.JPG)
![addOrder.JPG](..%2F..%2F..%2F..%2FDownloads%2FaddOrder.JPG)
![getOrder.JPG](..%2F..%2F..%2F..%2FDownloads%2FgetOrder.JPG)
![getOrderByUserId.JPG](..%2F..%2F..%2F..%2FDownloads%2FgetOrderByUserId.JPG)
![updateorderstatus.JPG](..%2F..%2F..%2F..%2FDownloads%2Fupdateorderstatus.JPG)
![db.JPG](..%2F..%2F..%2F..%2FDownloads%2Fdb.JPG)