package org.limepepper.demo.helper;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

abstract public class StringHelper {

    static public String repeat(String s, int n) {
        return new String(new char[n]).replace("\0", s);
    }

    public static String getBinaryStringWithLeadingZeros(long number) {
        return String.format("%64s", Long.toBinaryString(number)).replace(' ', '0');
    }

    public static String breakAt8chars(String line) {
        //String line1 = String.join(" ", line.split(""));
        String buff = line.replaceAll("(.{8})", "$1\n");
        String[] items = buff.split("\n");
        return Arrays.stream(items).map(s -> String.join(" ", s.split("")) + "\n").collect(Collectors.joining());

    }

    public static String formatBits(long bits) {
        return breakAt8chars(new StringBuilder(getBinaryStringWithLeadingZeros(bits)).reverse().toString());
    }

    // Pad the number with zeros to the left up to the specified length
    public static String padWithZeros(long number, int length) {
        return String.format("%0" + length + "d", number);
    }

    /**
     * Java method  to calculate number of set bits in a given bit sequence.
     *
     * @param number is the integer but represent binary value
     * @return count of set bits in bit sequence
     */
    public static int countBits(long number) {
        if (number == 0) {
            return (int) number;
        }

        int count = 0;
        while (number != 0) {
            number &= (number - 1);
            count++;
        }
        return count;
    }
}
