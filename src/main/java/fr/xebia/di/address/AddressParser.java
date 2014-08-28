package fr.xebia.di.address;

import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.String.format;

public class AddressParser {
    private static final Pattern PATTERN = Pattern.compile("(?<number>\\d+) (?<type>(?:boulevard)|(?:avenue)) (?<streetName>[^,]+), (?<postalCode>\\d+)?\\s?(?<town>.+)");

    public AddressParser(Locale locale) {
    }

    public Address parse(String standardizedAddress) {
        Matcher matcher = PATTERN.matcher(standardizedAddress);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(format("\"%s\" can't be parsed", standardizedAddress));
        }

        return new Address(parseInt(matcher.group("number")),
                matcher.group("type"),
                matcher.group("streetName"),
                Optional.ofNullable(matcher.group("postalCode")),
                matcher.group("town"));
    }
}
