package org.example.utilities;

public class SalesmanPID {

    private static final String NAME = "Estate Administration";
    private static final String ADDRESS = "Grodzka 2";
    private static final String POSTCODE = "97-300";
    private static final String CITY = "Piotrków Trybunaski";

    public static String getData() {
        return NAME + "\n" + POSTCODE + " " + CITY + "\n" + ADDRESS;
    }
}
