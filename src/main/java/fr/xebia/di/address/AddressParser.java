package fr.xebia.di.address;

import java.util.Locale;
import java.util.regex.Matcher;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class AddressParser {
    public AddressParser(Locale locale) {
    }

    public Address parse(String standardizedAddress) {
        Matcher matcher = AddressNormalizer.NORMALIZER.matcher(standardizedAddress);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(format("\"%s\" can't be parsed", standardizedAddress));
        }

        if (matcher.group("postalCode") == null) {
            throw new IllegalArgumentException(format("\"%s\" can't be parsed", standardizedAddress));
        }

        return new Address(parseInt(matcher.group("number")),
                matcher.group("type"),
                matcher.group("streetName"),
                matcher.group("postalCode"),
                matcher.group("town"));
    }
}
