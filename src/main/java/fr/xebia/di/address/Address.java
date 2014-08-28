package fr.xebia.di.address;

import java.util.Optional;

public class Address {
    public final int number;
    public final String type;
    public final String streetName;
    public final Optional<String> postalCode;
    public final String town;

    public Address(int number, String type, String streetName, Optional<String> postalCode, String town) {
        this.number = number;
        this.type = type;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.town = town;
    }
}
