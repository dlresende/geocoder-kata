package fr.xebia.di.test;

import fr.xebia.di.address.Address;
import fr.xebia.di.address.NormalizedAddress;

public class DiAssertions {
    public static AddressAssert assertThat(Address actual) {
        return new AddressAssert(actual);
    }

    public static NormalizedAddressAssert assertThat(NormalizedAddress actual) {
        return new NormalizedAddressAssert(actual);
    }
}
