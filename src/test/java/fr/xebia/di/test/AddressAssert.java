package fr.xebia.di.test;

import fr.xebia.di.address.Address;
import org.assertj.core.api.AbstractAssert;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AddressAssert extends AbstractAssert<AddressAssert, Address> {
    AddressAssert(Address actual) {
        super(actual, AddressAssert.class);
    }

    public AddressAssert number(int expectedNumber) {
        assertThat(actual.number).isEqualTo(expectedNumber);
        return this;
    }

    public AddressAssert type(String expectedType) {
        assertThat(actual.type).isEqualTo(expectedType);
        return this;
    }

    public AddressAssert streetName(String expectedStreetName) {
        assertThat(actual.streetName).isEqualTo(expectedStreetName);
        return this;
    }

    public AddressAssert postalCode(Optional<String> expectedPostalCode) {
        assertThat(actual.postalCode).isEqualTo(expectedPostalCode);
        return this;
    }

    public AddressAssert town(String expectedTown) {
        assertThat(actual.town).isEqualTo(expectedTown);
        return this;
    }
}
