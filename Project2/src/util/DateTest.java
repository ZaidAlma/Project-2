package src.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void testValidLeaperYear() {
        Date validDate = new Date(2,29,2020);
        assertTrue(validDate.isValid());
    }

    @Test
    public void testValidRegularDate() {
        Date validDate = new Date(2,20,2020);
        assertTrue(validDate.isValid());
    }

    @Test
    public void testInvalidLeapYear() {
        Date validDate = new Date(2,29,2021);
        assertFalse(validDate.isValid());
    }

    @Test
    public void testInvalidDay() {
        Date validDate = new Date(2,35,2020);
        assertFalse(validDate.isValid());
    }

    @Test
    public void testInvalidMonth() {
        Date validDate = new Date(15,22,2020);
        assertFalse(validDate.isValid());
    }

    @Test
    public void testInvalid0() {
        Date validDate = new Date(11,0,2020);
        assertFalse(validDate.isValid());
    }




}