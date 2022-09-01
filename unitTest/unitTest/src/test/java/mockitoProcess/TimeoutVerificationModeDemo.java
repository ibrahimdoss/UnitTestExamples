package mockitoProcess;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import studentRegisterExample.model.Student;
import studentRegisterExample.repository.StudentRepository;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class TimeoutVerificationModeDemo {

    @Mock
    private StudentRepository studentRepository;

    @Test
    void readStudent() {
        when(studentRepository.findById("id1")).thenReturn(Optional.of(new Student("", "", "")));

        new Thread(() -> {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            studentRepository.findById("id1");
        }).start();

        verify(studentRepository, timeout(100).times(1)).findById("id1");

    }
}
