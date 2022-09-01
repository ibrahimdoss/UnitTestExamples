package tekrarEdenTestler;

import org.junit.jupiter.api.*;
import studentRegisterExample.model.Course;
import studentRegisterExample.model.LecturerCourseRecord;
import studentRegisterExample.model.Semester;
import studentRegisterExample.model.Student;
import testInterfaceAndDefaultMethod.TestLifecycleReporter;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentTestWithRepeatedTest implements TestLifecycleReporter {

    private Student student;

    @BeforeAll
    void setUp() {
        student = new Student("id1", "Ahmet", "Yilmaz");
    }

    @DisplayName("Add Course to Student")
    @RepeatedTest(value = 5, name = "{displayName} => Add one course to student and student has {currentRepetition} courses")
    void addCourseToStudent(RepetitionInfo repetitionInfo) {

        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course(String.valueOf(repetitionInfo.getCurrentRepetition())), new Semester());
        student.addCourse(lecturerCourseRecord);
        assertEquals(repetitionInfo.getCurrentRepetition(), student.getStudentCourseRecords().size());
    }
}
//Testin kac defa calısacağını value ile veriyoruz.
//Bir testin arka arkaya calısmasını, kac defa calısmasını istediğimiz zaman kullanırız.