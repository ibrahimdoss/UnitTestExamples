package schoolProcessExTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import studentRegisterExample.model.*;

import java.time.LocalDate;

public class LecturerTest {

    private Lecturer lecturer;

    @BeforeEach
    void setUp(){
        lecturer = new Lecturer();
    }

    private LecturerCourseRecord lecturerCourseRecord(){
        return new LecturerCourseRecord(new Course(), new Semester());
    }

    @Test
    @DisplayName("Case 1")
    void whenACourseIsAddedToLecturerThenLecturerCourseSizeIncrease(){
        //Assertions.assertEquals(0, lecturer.getLecturerCourseRecords().size());
        lecturer.addLecturerCourseRecord(lecturerCourseRecord());
        Assertions.assertEquals(1, lecturer.getLecturerCourseRecords().size());
    }

    @Test
    @DisplayName("Case 2")
    void lecturerCourseRecordHasLecturerInfoWhenAddedToALecturer(){
        final LecturerCourseRecord lecturerCourseRecord = lecturerCourseRecord();
        lecturer.addLecturerCourseRecord(lecturerCourseRecord);
        Assertions.assertEquals(lecturer, lecturerCourseRecord.getLecturer());
    }

    @Test
    @DisplayName("Case 3")
    void throwsIllegalArgumentExceptionWhenANullCourseIsAddedToLecturer(){
        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(null, new Semester());
        final IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class, () ->lecturer.addLecturerCourseRecord(lecturerCourseRecord));
        Assertions.assertEquals("Can't add a null course to lecturer", illegalArgumentException.getMessage());
    }

    @Test
    @DisplayName("Case 4")
    void throwsNotActiveSemesterExceptionWhenACourseIsAddedForNotActiveSemesterToLecturer(){

        final Semester activeSemester = new Semester();
        final LocalDate lastYear = LocalDate.of(activeSemester.getYear()-1, 1, 1);
        final Semester notActiveSemester = new Semester(lastYear);

        final LecturerCourseRecord lecturerCourseRecord = new LecturerCourseRecord(new Course(), notActiveSemester);
        Assertions.assertThrows(NotActiveSemesterException.class, () -> lecturer.addLecturerCourseRecord(lecturerCourseRecord));
    }
}
