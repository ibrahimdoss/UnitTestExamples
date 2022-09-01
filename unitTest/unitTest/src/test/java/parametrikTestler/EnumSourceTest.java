package parametrikTestler;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import studentRegisterExample.model.Course;
import studentRegisterExample.model.LecturerCourseRecord;
import studentRegisterExample.model.Semester;
import studentRegisterExample.model.Student;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class EnumSourceTest {

    private Student student;


    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class EnumSourceDemo {

        @BeforeAll
        void setUp() {
             student = new Student("id1", "Ahmet", "Yilmaz");
        }

        @ParameterizedTest
        @EnumSource(Course.CourseType.class)
        void addCourseStudent(Course.CourseType courseType) {

            final Course course = Course.newCourse()
                    .withCode(String.valueOf(new Random().nextInt(200)))
                    .withCourseType(courseType).course();

            final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }

        @ParameterizedTest
        @EnumSource(value = Course.CourseType.class, names = "MANDATORY")
        void addMandatoryCourseToStudent(Course.CourseType courseType) {
            final Course course = Course.newCourse()
                    .withCode(String.valueOf(new Random().nextInt(200)))
                    .withCourseType(courseType).course();

            final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
            assertEquals(Course.CourseType.MANDATORY, course.getCourseType());
        }

        @ParameterizedTest
        @EnumSource(value = Course.CourseType.class, mode = EnumSource.Mode.EXCLUDE, names = "MANDATORY")
        void addElectiveCourseToStudent(Course.CourseType courseType) {
            final Course course = Course.newCourse()
                    .withCode(String.valueOf(new Random().nextInt(200)))
                    .withCourseType(courseType).course();

            final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
            assertEquals(Course.CourseType.ELECTIVE, course.getCourseType());
        }

    }

}

    