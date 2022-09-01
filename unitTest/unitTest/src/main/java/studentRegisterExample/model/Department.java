package studentRegisterExample.model;

import java.util.Set;

public class Department {

    private String code;
    private String name;
    private Set<Lecturer> lecturers;
    private Set<Course> courses;
    private Set<Student> students;
    private Faculty faculty;

    @Override
    public String toString() {
        return code + ":" + name;
    }
}
