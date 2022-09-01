package schoolProcessExTest;

import org.junit.jupiter.api.extension.ParameterResolver;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import studentRegisterExample.model.Course;
import studentRegisterExample.model.LecturerCourseRecord;
import studentRegisterExample.model.Semester;
import studentRegisterExample.model.Student;

import java.math.BigDecimal;
import java.util.Map;

import static studentRegisterExample.model.StudentCourseRecord.Grade.*;


public class ParameterResolverForGpaCalculation implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {

        if (parameterContext.getParameter().getType() == Student.class && parameterContext.getIndex() == 0) {
            return true;
        }

        if (parameterContext.getParameter().getType() == Map.class && parameterContext.getIndex() == 1) {
            return true;
        }

        if (parameterContext.getParameter().getType() == BigDecimal.class && parameterContext.getIndex() == 2) {
            return true;
        }

        return false;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {

        if (parameterContext.getParameter().getType() == Student.class) {
            return new Student("id1", "Ahmet", "Yilmaz");
        }

        if (parameterContext.getParameter().getType() == Map.class) {
            final LecturerCourseRecord lecturerCourseRecord101 = new LecturerCourseRecord(new Course("101"), new Semester());
            lecturerCourseRecord101.setCredit(3);
            final LecturerCourseRecord lecturerCourseRecord103 = new LecturerCourseRecord(new Course("103"), new Semester());
            lecturerCourseRecord103.setCredit(2);
            final LecturerCourseRecord lecturerCourseRecord105 = new LecturerCourseRecord(new Course("105"), new Semester());
            lecturerCourseRecord105.setCredit(1);

            return Map.of(
                    lecturerCourseRecord101, A1,
                    lecturerCourseRecord103, B1,
                    lecturerCourseRecord105, C
            );
        }

        if (parameterContext.getParameter().getType() == BigDecimal.class) {
            return BigDecimal.valueOf(3.33);
        }

        throw new IllegalArgumentException("Could't provide unsupported parameter");
    }
}

