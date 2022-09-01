package studentRegisterExample.repository;

import studentRegisterExample.model.Course;
import studentRegisterExample.model.Department;

import java.util.Optional;

public interface CourseRepository {

    Optional<Course> findByExample(Course course);

    Optional<Course> findByDepartmentAndCodeAndName(Department department, String code, String name);
}
