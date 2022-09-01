package studentRegisterExample.services;

import studentRegisterExample.model.Course;
import studentRegisterExample.model.Department;

import java.util.Optional;

public interface CourseService {

    Optional<Course> findCourse(Course course);

    Optional<Course> findCourse(Department department, String code, String name);
}
