package testLifecycle;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import studentRegisterExample.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentTestWithTestLifecycle {

    private Student mehmet = new Student("id1", "Mehmet", "Yilmaz");

    @BeforeEach
    static void setUp() {

    }

    @Test
    void stateCannotChangeWhenLifecyleIsPerMethod() {
        assertEquals("Mehmet", mehmet.getName());
        mehmet = new Student("id2", "Ahmet", "Yilmaz");
    }

    @Test
    void stateCanChangeWhenLifecyleIsPerClass() {
        assertEquals("Mehmet", mehmet.getName());
        mehmet = new Student("id2", "Ahmet", "Yilmaz");
    }
}

//Yukarıdaki durumda calıstırdığımız da success verecek ama PER_CLASS kullandığımız zaman fail verecek cünkü
//instance'dan bi tane olusmasını istediğimiz icin 2.test classı onu görecek ve fail verecek. Çünkü bi tane testInstance var
//diğer tüm metotlarda bu instance calıstırılıyor. Normal şartlarda her iki metot da success veriyor çünkü instanceları birden
//fazla olusturuluyor. Burada yaptığımız degisiklik diğerlerinde görünür oluyor. Normalde gözükmüyor.

// PER_CLASS kullanıyorsak yukarıdakilerin static olmasına gerek yoktur.