package testInstancePostProcessor;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import studentRegisterExample.model.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

public class StudentWithNestedTests {

    @ExtendWith(DropCourseTestInstancePostProcessor.class)
    @Nested
    @DisplayName("Drop Course from Student")
    @Tag("dropCourse")
    class DropCourseFromStudent {

        //throws illegal argument exception for null lecturer course record
        //throws illegal argument exception if the student did't register course before
        //throws not active semester exception if the semester is not active
        //throws not active semester exception if the add drop period is closed for the semester
        //drop course from student

        Student studentAhmet;        Semester addDropPeriodOpenSemester;
        Semester addDropPeriodClosedSemester;
        Semester notActiveSemester;

        @TestFactory
        Stream<DynamicTest> dropCourseFromStudentFactory() {

            return Stream.of(
                    dynamicTest("throws illegal argument exception for null lecturer course record", () -> {
                        assertThrows(IllegalArgumentException.class, () -> studentAhmet.dropCourse(null));
                    }),
                    dynamicTest("throws illegal argument exception if the student did't register course before", () -> {
                        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), new Semester());
                        assertThrows(IllegalArgumentException.class, () -> studentAhmet.dropCourse(lecturerCourseRecord));
                    }),
                    dynamicTest("throws not active semester exception if the semester is not active", () -> {
                        assumeTrue(!notActiveSemester.isActive());
                        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), notActiveSemester);
                        Student studentMehmet = new Student("id1", "Mehmet", "Yilmaz", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
                        assertThrows(NotActiveSemesterException.class, () -> studentMehmet.dropCourse(lecturerCourseRecord));
                    }),
                    dynamicTest("throws not active semester exception if the add drop period is closed for the semester", () -> {
                        assumeTrue(!addDropPeriodClosedSemester.isAddDropAllowed());
                        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), addDropPeriodClosedSemester);
                        Student studentMehmet = new Student("id1", "Mehmet", "Yilmaz", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
                        assertThrows(NotActiveSemesterException.class, () -> studentMehmet.dropCourse(lecturerCourseRecord));
                    }),
                    dynamicTest("drop course from student", () -> {
                        assumeTrue(addDropPeriodOpenSemester.isAddDropAllowed());
                        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course("101"), addDropPeriodOpenSemester);
                        Student studentMehmet = new Student("id1", "Mehmet", "Yilmaz", Set.of(new StudentCourseRecord(lecturerCourseRecord)));
                        assertEquals(1, studentMehmet.getStudentCourseRecords().size());
                        studentMehmet.dropCourse(lecturerCourseRecord);
                        assertTrue(studentMehmet.getStudentCourseRecords().isEmpty());
                    })
            );
        }

    }
}
