package fr.xebia.di.address;

import fr.xebia.di.address.postalcode.PostalCodeResolver;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.String.format;

public class AddressNormalizer {
    public static final Pattern NORMALIZER = Pattern.compile("(?<number>\\d+) (?<type>(?:boulevard)|(?:avenue)) (?<streetName>[^,]+), (?<postalCode>\\d+)?\\s?(?<town>.+)");
    private final Locale locale;
    private final PostalCodeResolver postalCodeResolver;

    public AddressNormalizer(Locale locale, PostalCodeResolver postalCodeResolver) {
        if (locale != Locale.FRANCE) {
            throw new IllegalArgumentException(format("locale should be %s", Locale.FRANCE));
        }
        this.locale = locale;
        this.postalCodeResolver = postalCodeResolver;
    }

    public String normalize(String address) {
        StringBuilder normalized = new StringBuilder();
        Matcher matcher = NORMALIZER.matcher(address);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(format("\"%s\" can't be normalized", address));
        }

        String postalCode = matcher.group("postalCode");
        String town = matcher.group("town");

        if (matcher.group("postalCode") == null) {
            postalCode = postalCodeResolver.resolve(town);
        }

        normalized.append(matcher.group("number"))
                .append(" ").append(matcher.group("type"))
                .append(" ").append(matcher.group("streetName"))
                .append(", ").append(postalCode)
                .append(" ").append(town.toUpperCase());

        return normalized.toString();
    }
}
