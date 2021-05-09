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
}
