package com.niemiec.reliablealarmclock;

import com.niemiec.reliablealarmclock.validator.HourValidator;

import org.junit.Assert;
import org.junit.Test;

public class HourValidatorTest {

    @Test
    public void checkTheCorrectnessOfTheEnteredHourTest() {
        String a = "1";
        String b = "11";
        String c = "02";
        String d = "27";
        String e = "23";
        String f = "0";
        String g = "00";
        String h = "7";
        String i = "9";
        String j = "10";
        String k = "24";

        Assert.assertEquals("1", HourValidator.checkTheCorrectnessOfTheEnteredHour(a));
        Assert.assertEquals("11", HourValidator.checkTheCorrectnessOfTheEnteredHour(b));
        Assert.assertEquals("02", HourValidator.checkTheCorrectnessOfTheEnteredHour(c));
        Assert.assertEquals("2", HourValidator.checkTheCorrectnessOfTheEnteredHour(d));
        Assert.assertEquals("23", HourValidator.checkTheCorrectnessOfTheEnteredHour(e));
        Assert.assertEquals("0", HourValidator.checkTheCorrectnessOfTheEnteredHour(f));
        Assert.assertEquals("00", HourValidator.checkTheCorrectnessOfTheEnteredHour(g));
        Assert.assertEquals("07", HourValidator.checkTheCorrectnessOfTheEnteredHour(h));
        Assert.assertEquals("09", HourValidator.checkTheCorrectnessOfTheEnteredHour(i));
        Assert.assertEquals("10", HourValidator.checkTheCorrectnessOfTheEnteredHour(j));
        Assert.assertEquals("00", HourValidator.checkTheCorrectnessOfTheEnteredHour(k));

    }

    @Test
    public void checkTheCorrectnessOfTheEnteredHourWhenHourChangFocusTest() {
        String a = "0";
        String b = "1";
        String c = "2";
        String d = "3";
        String e = "9";
        String f = "00";
        String g = "01";
        String h = "02";
        String i = "03";
        String j = "09";
        String k = "23";

        Assert.assertEquals("00", HourValidator.checkTheCorrectnessOfTheEnteredHourWhenHourChangFocus(a));
        Assert.assertEquals("01", HourValidator.checkTheCorrectnessOfTheEnteredHourWhenHourChangFocus(b));
        Assert.assertEquals("02", HourValidator.checkTheCorrectnessOfTheEnteredHourWhenHourChangFocus(c));
        Assert.assertEquals("03", HourValidator.checkTheCorrectnessOfTheEnteredHourWhenHourChangFocus(d));
        Assert.assertEquals("09", HourValidator.checkTheCorrectnessOfTheEnteredHourWhenHourChangFocus(e));
        Assert.assertEquals("00", HourValidator.checkTheCorrectnessOfTheEnteredHourWhenHourChangFocus(f));
        Assert.assertEquals("01", HourValidator.checkTheCorrectnessOfTheEnteredHourWhenHourChangFocus(g));
        Assert.assertEquals("02", HourValidator.checkTheCorrectnessOfTheEnteredHourWhenHourChangFocus(h));
        Assert.assertEquals("03", HourValidator.checkTheCorrectnessOfTheEnteredHourWhenHourChangFocus(i));
        Assert.assertEquals("09", HourValidator.checkTheCorrectnessOfTheEnteredHourWhenHourChangFocus(j));
        Assert.assertEquals("23", HourValidator.checkTheCorrectnessOfTheEnteredHourWhenHourChangFocus(k));

    }
}
