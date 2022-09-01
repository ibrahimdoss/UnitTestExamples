package studentRegisterExample.services;

import studentRegisterExample.model.Course;
import studentRegisterExample.model.Lecturer;
import studentRegisterExample.model.Semester;
import studentRegisterExample.repository.LecturerRepository;

import java.util.Optional;

public class LecturerServiceImpl implements LecturerService{

    private final LecturerRepository lecturerRepository;

    public LecturerServiceImpl(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @Override
    public Optional<Lecturer> findLecturer(Course course, Semester semester) {
        if (course == null || semester == null) {
            throw new IllegalArgumentException("Can't find lecturer without course and semester");
        }
        return lecturerRepository.findByCourseAndSemester(course, semester);
    }
}
