package com.neaniesoft.myweather.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by mdpearce on 26/09/2016.
 */
public class TextUtilsTest {
    @Test
    public void TextUtils_isDigitsOnly() throws Exception {
        assertTrue(TextUtils.isDigitsOnly("90210"));
        assertTrue(TextUtils.isDigitsOnly("0"));
        assertFalse(TextUtils.isDigitsOnly("90210 S"));
        assertFalse(TextUtils.isDigitsOnly("asd"));
        assertFalse(TextUtils.isDigitsOnly("Hello 123"));
    }

    @Test
    public void TextUtils_formatDoubleValue() throws Exception {
        assertEquals("0.0", TextUtils.formatDoubleValue(0.0));
        assertEquals("0.0", TextUtils.formatDoubleValue((double)0));
        assertEquals("0.1", TextUtils.formatDoubleValue(0.1));
        assertEquals("1.0", TextUtils.formatDoubleValue(1.001));
        assertEquals("100.1", TextUtils.formatDoubleValue(100.101));
        assertNull(TextUtils.formatDoubleValue(null));
    }

    @Test
    public void TextUtils_formatIntValue() throws Exception {
        assertEquals("0", TextUtils.formatIntValue(0));
        assertEquals("0", TextUtils.formatIntValue((int)0.1));
        assertEquals("1", TextUtils.formatIntValue(1));
        assertEquals("999", TextUtils.formatIntValue(999));
        assertEquals("-1", TextUtils.formatIntValue(-1));
        assertNull(TextUtils.formatIntValue(null));
    }

    @Test
    public void TextUtils_formatDirection() throws Exception {
        assertEquals("N", TextUtils.formatDirection(0.0));
        assertEquals("S", TextUtils.formatDirection(180.0));
        assertEquals("NNW", TextUtils.formatDirection(348.74));
        assertEquals("N", TextUtils.formatDirection(348.75));
        assertEquals("N", TextUtils.formatDirection(11.24));
        assertEquals("NNE", TextUtils.formatDirection(11.25));
    }

}