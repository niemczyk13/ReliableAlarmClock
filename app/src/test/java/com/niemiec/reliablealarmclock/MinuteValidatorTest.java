package com.niemiec.reliablealarmclock;

import com.niemiec.reliablealarmclock.validator.MinuteValidator;

import org.junit.Assert;
import org.junit.Test;

public class MinuteValidatorTest {
    @Test
    public void checkTheCorrectnessOfTheEnteredHourTest() {
        String a = "0";
        String b = "1";
        String c = "5";
        String d = "6";
        String e = "9";
        String f = "01";
        String g = "59";

        Assert.assertEquals("0", MinuteValidator.checkTheCorrectnessOfTheEnteredMinute(a));
        Assert.assertEquals("1", MinuteValidator.checkTheCorrectnessOfTheEnteredMinute(b));
        Assert.assertEquals("5", MinuteValidator.checkTheCorrectnessOfTheEnteredMinute(c));
        Assert.assertEquals("06", MinuteValidator.checkTheCorrectnessOfTheEnteredMinute(d));
        Assert.assertEquals("09", MinuteValidator.checkTheCorrectnessOfTheEnteredMinute(e));
        Assert.assertEquals("01", MinuteValidator.checkTheCorrectnessOfTheEnteredMinute(f));
        Assert.assertEquals("59", MinuteValidator.checkTheCorrectnessOfTheEnteredMinute(g));

    }

    @Test
    public void checkTheCorrectnessOfTheEnteredMinuteWhenMinuteChangFocusTest() {
        String a = "0";
        String b = "1";
        String c = "5";
        String d = "6";
        String e = "9";

        Assert.assertEquals("00", MinuteValidator.checkTheCorrectnessOfTheEnteredMinuteWhenMinuteChangFocus(a));
        Assert.assertEquals("01", MinuteValidator.checkTheCorrectnessOfTheEnteredMinuteWhenMinuteChangFocus(b));
        Assert.assertEquals("05", MinuteValidator.checkTheCorrectnessOfTheEnteredMinuteWhenMinuteChangFocus(c));
        Assert.assertEquals("06", MinuteValidator.checkTheCorrectnessOfTheEnteredMinuteWhenMinuteChangFocus(d));
        Assert.assertEquals("09", MinuteValidator.checkTheCorrectnessOfTheEnteredMinuteWhenMinuteChangFocus(e));
    }
}
