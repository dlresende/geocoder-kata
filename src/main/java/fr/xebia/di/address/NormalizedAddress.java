package fr.xebia.di.address;

public class NormalizedAddress {
    public final int number;
    public final String type;
    public final String streetName;
    public final String postalCode;
    public final String town;

    public NormalizedAddress(int number, String type, String streetName, String postalCode, String town) {
        this.number = number;
        this.type = type;
        this.streetName = streetName;
        this.postalCode = postalCode;
        this.town = town;
    }
}
