package fr.xebia.di.address;

import java.util.Locale;

import static java.lang.String.format;

public class AddressNormalizer {
    private final Locale locale;

    public AddressNormalizer(Locale locale) {
        if (locale != Locale.FRANCE) {
            throw new IllegalArgumentException(format("locale should be %s", Locale.FRANCE));
        }
        this.locale = locale;
    }

    public String normalize(String address) {
        return address;
    }
}
