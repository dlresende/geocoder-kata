package fr.xebia.di.address;

import org.junit.Test;

import static fr.xebia.di.test.DiAssertions.assertThat;
import static java.util.Locale.FRANCE;

public class AddressParserTest {
    @Test
    public void should_parse_address() {
        AddressParser parser = new AddressParser(FRANCE);

        Address address = parser.parse("177 avenue Henri Barbusse, 92700 COLOMBES");

        assertThat(address).number(177)
                .type("avenue")
                .streetName("Henri Barbusse")
                .postalCode("92700")
                .town("COLOMBES");
    }

    @Test(expected = IllegalArgumentException.class)
    public void should_not_parse_not_normalized_address() {
        AddressParser parser = new AddressParser(FRANCE);

        Address address = parser.parse("156 boulevard Haussmann, Paris");
    }
}