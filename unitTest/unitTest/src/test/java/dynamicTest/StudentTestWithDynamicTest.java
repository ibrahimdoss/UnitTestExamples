package dynamicTest;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.ThrowingConsumer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import studentRegisterExample.model.Course;
import studentRegisterExample.model.LecturerCourseRecord;
import studentRegisterExample.model.Semester;
import studentRegisterExample.model.Student;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class StudentTestWithDynamicTest {

    private Student student;

    @BeforeEach
    void setUp() {
        student = new Student("id1", "Ahmet", "Yilmaz");
    }

    @TestFactory
    Stream<DynamicNode> addCourseToStudentWithCourseCodeAndCourseType(TestReporter testReporter) {

        return Stream.of("101", "103", "105")
                .map(courseCode -> dynamicContainer("Add course<" + courseCode + "> to student",
                        Stream.of(Course.CourseType.MANDATORY, Course.CourseType.ELECTIVE)
                                .map(courseType -> dynamicTest("Add course<" + courseType + "> to student", () -> {
                                    final Course course = Course.newCourse().withCode(courseCode).withCourseType(courseType).course();
                                    final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
                                    student.addCourse(lecturerCourseRecord);
                                    assertTrue(student.isTakeCourse(course));
                                    testReporter.publishEntry("Student Course Size", String.valueOf(student.getStudentCourseRecords().size()));
                                }))
                ));

    }

    @TestFactory
    Stream<DynamicTest> addCourseToStudentWithCourseCode() {
        final Iterator<String> courseCodeGenerator = Stream.of("101", "103", "105").iterator();
        Function<String, String> displayNameGenerator = courseCode -> "Add course<" + courseCode + "> to student";
        ThrowingConsumer<String> testExecutor = courseCode -> {
            final Course course = Course.newCourse().withCode(courseCode).withCourseType(Course.CourseType.MANDATORY).course();
            final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(course, new Semester());
            student.addCourse(lecturerCourseRecord);
            assertTrue(student.isTakeCourse(course));

        };


        return DynamicTest.stream(courseCodeGenerator, displayNameGenerator, testExecutor);
    }
}
