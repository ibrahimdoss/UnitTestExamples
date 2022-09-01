package testInstancePostProcessor;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class DropCourseTestInstancePostProcessor implements TestInstancePostProcessor {

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        final StudentWithNestedTests.DropCourseFromStudent dropCourseFromStudent = (StudentWithNestedTests.DropCourseFromStudent) testInstance;
    }
}
