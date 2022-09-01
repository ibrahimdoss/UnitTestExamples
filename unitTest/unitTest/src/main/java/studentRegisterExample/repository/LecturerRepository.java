package studentRegisterExample.repository;

import studentRegisterExample.model.Course;
import studentRegisterExample.model.Lecturer;
import studentRegisterExample.model.Semester;

import java.util.Optional;

public interface LecturerRepository {

    Optional<Lecturer> findByCourseAndSemester(Course course, Semester semester);

}
