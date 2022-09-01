package testSablonlarıTestTemplate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import studentRegisterExample.model.LecturerCourseRecord;
import studentRegisterExample.model.Student;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentTestWithTemplate {

    private Student student;

    @BeforeAll
    void setUp() {
        student = new Student("id1", "Ahmet", "Yilmaz");
    }

    @ExtendWith(RepeatedStudentTestTemplateInvocationContextProvider.class)
    @TestTemplate
    void addCourseToStudent(LecturerCourseRecord lecturerCourseRecord, int numberOfInvocation) {

        student.addCourse(lecturerCourseRecord);
        assertEquals(numberOfInvocation, student.getStudentCourseRecords().size());
    }
}
