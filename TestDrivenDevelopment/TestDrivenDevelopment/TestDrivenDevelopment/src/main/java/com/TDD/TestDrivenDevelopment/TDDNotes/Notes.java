package com.TDD.TestDrivenDevelopment.TDDNotes;


public enum Notes {

  //  Buradaki test notları test paketi icerisindeki test1, test2 vs gibi adlandırılmıs degerlere karsılık gelen acıklamalardır.

      /*@Test
    public void it_should_create_order(){
        // given

        // when
        OrderService service = new OrderService();
        OrderDto order = service.createOrder();

        // then

    }*/

    //test1 kısmında order null olmamalı diye kontrol etmesini sağladık, ama createOrder null'a esit o yüzden burada hata alıyoruz.
// o zaman bir daha prod koduna gidip baska bir sey döndürüyoruz.  return new OrderDto(); ve success veriyor artık.

/* test2'de order'ın totalprice ile bizim beklediğimiz değer 61.5 olarak isEqualTo ile karsılastırıyoruz.
* ilk durumda hata aldık expexted : 61.5 yani 61.5 olması gerekiyor diyor altında actual : null yani null değer verdin
* o yüzden hata verdi bize.
* Cözüm olarak return OrderDto.builder()
                .totalPrice(BigDecimal.valueOf(61.5))
                .build(); OrderService kısmına eklendi yani 61.5 değeri dönderdik ki değerler aynı olup success versin.*/


    /*Test3 direkt success verecek null degil cünkü.*/
    /* test4 hata verecek ilk basta cünkü degerleri degistirdik amount 10, unitPrice 15 total 150 olması gerekir ve test4
     * zaten 150 bekliyoruz ama orderService de biz return ile 61.5 döndürdüğümüz icin hata verdi.
     * Cözümü ise BigDecimal totalPrice = request.getUnitPrice().multiply(BigDecimal.valueOf(request.getAmount()));
     * return OrderDto.builder().totalPrice(totalPrice).build(); eklediğimiz zaman hata vermeyecek cünkü güncel amount degerinden
     * hesaplayıp o degeri aldık.*/

    /*Burada test4'dün cözümünü de eklediğimiz zaman artık item 5 de olsa 10 da olsa 15 de olsa success verecek
     * logic aynı zaten o zaman neden 2 tane test yazıyoruz sorusuna gelen cevap ise Parameterized  test yapılabilir.*/
}
