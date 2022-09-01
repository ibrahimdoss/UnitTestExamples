package testInterfaceAndDefaultMethod;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public interface CreateDomain<T> {

    T createDomain();

    @Test
    default void createDomainShouldBeImplemented() {
        Assertions.assertNotNull(createDomain());
    }
}
