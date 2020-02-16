package org.example.utilities;

public class NumberConverter {

    public static String getAmountInWords(String amount) {

        String[][] orderOfMagnitudeWordsTable = new String[][]{
                {"", "jeden ", "dwa ", "trzy ", "cztery ", "pięć ", "sześć ", "siedem ", "osiem ", "dziewięć "},
                {"", "jedenaście ", "dwanaście ", "trzynaście ", "czternaście ", "piętnaście ", "szesnaście ", "siedemnaście ", "osiemnaście ", "dziewiętnaście "},
                {"", "dziesięć ", "dwadzieścia ", "trzydzieści ", "czterdzieści ", "pięćdziesiąt ", "sześćdziesiąt ", "siedemdziesiąt ", "osiemdziesiąt ", "dziewięćdziesiąt "},
                {"", "sto ", "dwieście ", "trzysta ", "czterysta ", "pięćset ", "sześćset ", "siedemset ", "osiemset ", "dziewięćset "},
                {"", "tysiąc ", "tysiące ", "tysięcy "}
        };

        String[] split = amount.split(",");
        int orderOfMagnitude = split[0].length();
        int[] orderOfMagnitudeArray = new int[orderOfMagnitude];
        int integerValue = Integer.parseInt(split[0]);
        int decimalValue = Integer.parseInt(split[1]);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < orderOfMagnitude; i++) {
            orderOfMagnitudeArray[i] = integerValue % 10;
            integerValue /= 10;
        }
        for (int i = orderOfMagnitude - 1; i >= 0; i--) {
            if (i > 1) {
                result.append(orderOfMagnitudeWordsTable[i + 1][orderOfMagnitudeArray[i]]);
            } else {
                if (orderOfMagnitudeArray[i] > 1 && i == 1) {
                    result.append(orderOfMagnitudeWordsTable[i + 1][orderOfMagnitudeArray[i]]);
                } else {
                    result.append(orderOfMagnitudeWordsTable[i][orderOfMagnitudeArray[i]]);
                }
            }
        }
        result.append("złotych " + decimalValue + "/100");
        return result.toString();
    }
}
