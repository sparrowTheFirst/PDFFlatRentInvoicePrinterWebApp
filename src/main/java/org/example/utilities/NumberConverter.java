package org.example.utilities;

import java.util.HashMap;
import java.util.Map;

public class NumberConverter {

    private static String[][] numeralTable = new String[][]{
            {"", "jedenaście ", "dwanaście ", "trzynaście ", "czternaście ", "piętnaście ", "szesnaście ", "siedemnaście ", "osiemnaście ", "dziewiętnaście "},
            {"", "jeden ", "dwa ", "trzy ", "cztery ", "pięć ", "sześć ", "siedem ", "osiem ", "dziewięć "},
            {"", "dziesięć ", "dwadzieścia ", "trzydzieści ", "czterdzieści ", "pięćdziesiąt ", "sześćdziesiąt ", "siedemdziesiąt ", "osiemdziesiąt ", "dziewięćdziesiąt "},
            {"", "sto ", "dwieście ", "trzysta ", "czterysta ", "pięćset ", "sześćset ", "siedemset ", "osiemset ", "dziewięćset "},
            {"", "tysiąc ", "tysiące ", "tysięcy "}
    };

    public static String getAmountInWords(String amount) {
        String[] split = amount.split(",");
        int orderOfMagnitude = split[0].length();
        int integerValue = Integer.parseInt(split[0]);
        String decimalValue = split[1];
        StringBuilder result = new StringBuilder();
        Map<Integer, Integer> map = new HashMap<>();
        int tempValue = integerValue;

        for (int i = 1; i < orderOfMagnitude + 1; i++) {
            map.put(i, tempValue % 10);
            tempValue /= 10;
        }

        for (int i = orderOfMagnitude; i > 0; i--) {
            if (orderOfMagnitude > 1) {
                switch (i) {

                    case 1:
                        if (map.get(2) == 1 && map.get(i) != 0) {
                            result.append(numeralTable[0][map.get(i)]);
                        } else {
                            result.append(numeralTable[i][map.get(i)]);
                        }
                        break;

                    case 2:
                        if (map.get(i) == 1 && map.get(1) != 0) {
                            break;
                        } else {
                            result.append(numeralTable[i][map.get(i)]);
                        }
                        break;

                    case 3:
                        result.append(numeralTable[i][map.get(i)]);
                        break;

                    case 4:
                        if (map.get(i) < 5) {
                            if (map.get(i) == 1) {
                                result.append(numeralTable[1][1] + numeralTable[4][1]);
                            } else {
                                result.append(numeralTable[1][map.get(i)] + numeralTable[4][2]);
                            }
                        } else {
                            result.append(numeralTable[1][map.get(i)] + numeralTable[4][3]);
                        }
                        break;
                }
            } else {
                result.append(numeralTable[i][map.get(i)]);
            }
        }
        result.append("złotych " + decimalValue + "/100");
        return result.toString();
    }
}