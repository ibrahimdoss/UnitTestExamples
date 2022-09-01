package studentRegisterExample.repository;

import studentRegisterExample.model.Student;

import java.util.Optional;

public interface StudentRepository {

    Optional<Student> findById(String id);

    Student save(Student student);

    void delete(Student student);
}
