package mockitoProcess;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import studentRegisterExample.model.Course;
import studentRegisterExample.model.Lecturer;
import studentRegisterExample.model.Semester;
import studentRegisterExample.repository.LecturerRepository;
import studentRegisterExample.services.LecturerService;
import studentRegisterExample.services.LecturerServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

public class LecturerServiceTest {

    @Test
    void findLecturer() {
        final Course course = new Course("101");
        final Semester semester = new Semester();

        final LecturerRepository lecturerRepository = Mockito.mock(LecturerRepository.class);
        final Lecturer lecturer = new Lecturer();
        Mockito.when(lecturerRepository.findByCourseAndSemester(course, semester)).thenReturn(Optional.of(lecturer));

        final LecturerService lecturerService = new LecturerServiceImpl(lecturerRepository);
        final Optional<Lecturer> lecturerOpt = lecturerService.findLecturer(course, semester);
        assertThat(lecturerOpt)
                .isPresent()
                .get()
                .isSameAs(lecturer)
        ;

        Mockito.verify(lecturerRepository).findByCourseAndSemester(course, semester);
    }
}
