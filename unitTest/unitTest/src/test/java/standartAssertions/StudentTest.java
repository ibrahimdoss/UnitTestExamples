package standartAssertions;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import studentRegisterExample.model.Student;

import java.util.stream.Stream;

import static org.junit.Assert.*;

public class StudentTest {

    @Test
    @DisplayName("Test every student must have an id, name and surname")
    void shouldCreateStudentWithIdNameAndSurname(){
        /*
        * Assertions
        * assertEquals
        * assertEquals with message
        * assertNotEquals
        * assertTrue with lazily evaluated message
        * assertFalse with boolean supplier
        * assertNull
        * assertNotNull
        * assertArrayEquals
        * assertSame*/

        Student student = new Student("1", "ibrahim","Kurt");
        Assertions.assertEquals("ibrahim", student.getName()); // "ibrahim".equals(student.getName())
        Assertions.assertEquals("ibrahim", student.getName(), "Student's name"); // assertEquals with message
        Assertions.assertNotEquals("ibrahims", student.getName(), "Student's name");//assertNotEquals

        Assert.assertTrue(student.getName().startsWith("i"));
//        Assert.assertTrue(student.getName().startsWith("i"), () -> "Student's name" + "starts with ib");
 //       assertFalse(() -> {
//            Student student1 = new Student("id1", "Ahmet", "Yılmaz");
 //           return student1.getName().endsWith("M");
//            }, () -> "Student's name" + "ends With M");

        final Student student2 = new Student("id2", "Ahmet", "Can");

        assertArrayEquals(new String[]{"ibrahim", "Ahmet"}, Stream.of(student, student2).map(Student::getName).toArray());

        Student student3 = student;

        assertSame(student, student3); // ibrahim == student3
        assertNotSame(student2, student3);



    }
}
