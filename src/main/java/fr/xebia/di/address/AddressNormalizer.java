package fr.xebia.di.address;

import fr.xebia.di.address.postalcode.PostalCodeResolver;

import java.util.Locale;

import static java.lang.String.format;

public class AddressNormalizer {
    private final Locale locale;
    private final PostalCodeResolver postalCodeResolver;

    public AddressNormalizer(Locale locale, PostalCodeResolver postalCodeResolver) {
        if (locale != Locale.FRANCE) {
            throw new IllegalArgumentException(format("locale should be %s", Locale.FRANCE));
        }
        this.locale = locale;
        this.postalCodeResolver = postalCodeResolver;
    }

    public NormalizedAddress normalize(Address address) {
        String postalCode = address.postalCode;
        if (address.postalCode == null) {
            postalCode = postalCodeResolver.resolve(address.town);
        }

        return new NormalizedAddress(address.number, address.type, address.streetName, postalCode, address.town.toUpperCase(locale));
    }
}
