package fr.xebia.di.test;

import fr.xebia.di.address.Address;

public class DiAssertions {
    public static AddressAssert assertThat(Address actual) {
        return new AddressAssert(actual);
    }
}
