package extensionModel;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.logging.Logger;

public class TestLoggerExtension implements AfterAllCallback, BeforeAllCallback {

    private static Logger log = Logger.getLogger(TestLoggerExtension.class.getName());

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        log.info(String.format("Test is started...%s", context.getDisplayName()));
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        log.info(String.format("Test is ended...%s", context.getDisplayName()));

    }

}
