package com.balbasio.EticaretJavaCase.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {

    SIFRE_UYUSMUYOR(1000,"Girilen şifreler uyuşmuyor",HttpStatus.BAD_REQUEST),
    KULLANICI_ADI_VEYA_SIFRE_HATALI(1001,"Kullanıcı adı ya da şifre hatalıdır.",HttpStatus.BAD_REQUEST),
    BAD_REQUEST_ERROR(1002,"Girilen bilgiler Hatalı, kontrol ederek tekar giriniz.", HttpStatus.BAD_REQUEST),
    TOKEN_HATASI(1003,"Token üretilemedi, lütfen tekrar giriş yapmayı deneyiniz.", HttpStatus.INTERNAL_SERVER_ERROR),
    KAYITLI_KULLANICI_ADI(1004,"Bu kullanıcı adı zaten kayıtlıdır",HttpStatus.BAD_REQUEST),
    KAYITLI_ADRES_BULUNAMADI(1005,"Bu kullanıcı adresi zaten kayıtlıdır",HttpStatus.BAD_REQUEST),
    KULLANICI_YETKİ_HATASI(1006,"Bu kullanıcının yetkisi yoktur",HttpStatus.BAD_REQUEST),
    FARKLI_URUN_HATASI(1007,"Bu ürün size ait değildir.",HttpStatus.BAD_REQUEST),
    STOK_TUKENDI(1008,"Stok tükendi",HttpStatus.BAD_REQUEST),
    ORDER_BULUNAMADI(1009,"Sipariş bulunamadı",HttpStatus.BAD_REQUEST);



    private int code;
    private String message;
    private HttpStatus httpStatus;


}
