package studentRegisterExample.services;

import studentRegisterExample.model.Course;
import studentRegisterExample.model.Lecturer;
import studentRegisterExample.model.Semester;

import java.util.Optional;

public interface LecturerService {

    Optional<Lecturer> findLecturer(Course course, Semester semester);

}
