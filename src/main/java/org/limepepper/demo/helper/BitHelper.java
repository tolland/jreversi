package org.limepepper.demo.helper;

abstract public class BitHelper {
    /**
     * Count the number of bits set to 1 in a long.
     * @param x
     * @return
     */
    public static int countBits(long x) {
        int count = 0;
        while (x != 0) {
            x &= x - 1;
            count++;
        }
        return count;
    }

    public static long setBit(long x, int position) {
        return x | (1L << position);
    }

    public static long clearBit(long x, int position) {
        return x & ~(1L << position);
    }

    public static boolean isBitSet(long x, int position) {
        return (x & (1L << position)) != 0;
    }

    public static long toggleBit(long x, int position) {
        return x ^ (1L << position);
    }

    public static long setBitValue(long x, int position, boolean value) {
        if (value) {
            return setBit(x, position);
        } else {
            return clearBit(x, position);
        }
    }

    public static boolean getBitValue(long x, int position) {
        return isBitSet(x, position);
    }

    public static long getBitRange(long x, int start, int end) {
        return (x >> start) & ((1L << (end - start + 1)) - 1);
    }




}
