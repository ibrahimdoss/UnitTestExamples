package parametrikTestler;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.JavaTimeConversionPattern;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import studentRegisterExample.model.Course;
import studentRegisterExample.model.LecturerCourseRecord;
import studentRegisterExample.model.Semester;
import studentRegisterExample.model.Student;

import java.time.LocalDate;
import java.util.Random;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;
public class TypeConversionAndDisplayName {

    private Student student;


    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class TypeConversionAndCustomDisplayNameDemo {
        // enum conversion

        @BeforeAll
        void setUp() {
            student = new Student("id1", "Ahmet", "Yilmaz");
        }

        @ParameterizedTest
        @ValueSource(strings = {"MANDATORY", "ELECTIVE"})
        void addCourseStudent(Course.CourseType courseType) {

            final Course course = Course.newCourse()
                    .withCode(String.valueOf(new Random().nextInt(200)))
                    .withCourseType(courseType).course();

            final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }

        // user guide for other options


        // factory method or constructor conversion
        @ParameterizedTest
        @ValueSource(strings = {"101", "103"})
        void addCourseStudent(Course course) {
            final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }

        // conversion using SimpleConverter with1 @ConvertWith
        @ParameterizedTest
        @ValueSource(strings = {"101", "103", "105"})
        void addCourseStudentWithConverter(@ConvertWith(CourseConverter.class) Course course) {
            final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }


        // conversion with @JavaTimeConversionPattern
        @DisplayName("Add course with localdate info")
        @ParameterizedTest(name = "{index} ==> Parametreler: {arguments}")
        @ValueSource(strings = {"01.09.2018", "01.01.2018", "01.06.2018"})
        void addCourseStudentWithLocalDate(@JavaTimeConversionPattern("dd.MM.yyyy") LocalDate localDate) {
            final Course course = Course.newCourse().withCode(String.valueOf(new Random().nextInt(100))).course();
            final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester(localDate));
            student.addCourse(lecturerCourseRecord);
            assertFalse(student.getStudentCourseRecords().isEmpty());
            assertTrue(student.isTakeCourse(course));
        }


        //display name {index}, {arguments}, {0} usage
    }

    static class CourseConverter extends SimpleArgumentConverter {

        @Override
        protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
            return new Course(((String) source));
        }
    }
}
