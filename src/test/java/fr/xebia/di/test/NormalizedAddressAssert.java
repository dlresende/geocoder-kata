package fr.xebia.di.test;

import fr.xebia.di.address.NormalizedAddress;
import org.assertj.core.api.AbstractAssert;

import static org.assertj.core.api.Assertions.assertThat;

public class NormalizedAddressAssert extends AbstractAssert<NormalizedAddressAssert, NormalizedAddress> {
    NormalizedAddressAssert(NormalizedAddress actual) {
        super(actual, NormalizedAddressAssert.class);
    }

    public NormalizedAddressAssert number(int expectedNumber) {
        assertThat(actual.number).isEqualTo(expectedNumber);
        return this;
    }

    public NormalizedAddressAssert type(String expectedType) {
        assertThat(actual.type).isEqualTo(expectedType);
        return this;
    }

    public NormalizedAddressAssert streetName(String expectedStreetName) {
        assertThat(actual.streetName).isEqualTo(expectedStreetName);
        return this;
    }

    public NormalizedAddressAssert postalCode(String expectedPostalCode) {
        assertThat(actual.postalCode).isEqualTo(expectedPostalCode);
        return this;
    }

    public NormalizedAddressAssert town(String expectedTown) {
        assertThat(actual.town).isEqualTo(expectedTown);
        return this;
    }
}
