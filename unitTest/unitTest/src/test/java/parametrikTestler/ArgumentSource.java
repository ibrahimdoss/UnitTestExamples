package parametrikTestler;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import studentRegisterExample.model.Course;
import studentRegisterExample.model.LecturerCourseRecord;
import studentRegisterExample.model.Semester;
import studentRegisterExample.model.Student;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;
public class ArgumentSource {

    private Student student;

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    class ArgumentsSourceDemo {
        private int studentCourseSize = 0;

        @BeforeAll
        void setUp() {
            student = new Student("id1", "Ahmet", "Yilmaz");
        }

        @ParameterizedTest
        @ArgumentsSource(CourseCodeAndTypeProvider.class)
        void addCourseToStudent(String courseCode, Course.CourseType courseType) {

            final Course course = new Course(courseCode);
            course.setCourseType(courseType);
            final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            studentCourseSize++;
            assertEquals(studentCourseSize, student.getStudentCourseRecords().size());
            assertTrue(student.isTakeCourse(new Course(courseCode)));
            assumingThat(courseCode.equals("101"),
                    () -> assertEquals(Course.CourseType.MANDATORY, courseType)
            );
            assumingThat(courseCode.equals("103"),
                    () -> assertEquals(Course.CourseType.ELECTIVE, courseType)
            );
            assumingThat(courseCode.equals("105"),
                    () -> assertEquals(Course.CourseType.MANDATORY, courseType)
            );
        }
    }

    static class CourseCodeAndTypeProvider implements ArgumentsProvider {
        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
            return Stream.of(
                    Arguments.of("101", Course.CourseType.MANDATORY),
                    Arguments.of("103", Course.CourseType.ELECTIVE),
                    Arguments.of("105", Course.CourseType.MANDATORY)

            );
        }
    }
}
