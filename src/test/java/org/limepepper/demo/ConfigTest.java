package org.limepepper.demo;

import org.junit.Test;
import org.limepepper.demo.helper.StringHelper;

import static org.junit.Assert.*;

public class ConfigTest {

    @Test
    public void testCornerSquares() {
        System.out.println(StringHelper.formatBits(Config.cornerSquares));
    }
}
