package com.niemiec.reliablealarmclock;

import com.niemiec.reliablealarmclock.view.fragment.dialog.CalendarMinDateValidator;

import org.junit.Assert;
import org.junit.Test;

public class CalendarMinDateValidatorTest {

    @Test
    public void calculateTheOffsetForTheSelectedTimeTest() {
        int tomorrow = 86400000;
        int today = -1000;


        String actualH = "21";
        String actualM = "31";

        Assert.assertEquals(tomorrow, CalendarMinDateValidator.calculateTheOffsetForTheSelectedTime("21", "31", actualH, actualM));
        Assert.assertEquals(tomorrow, CalendarMinDateValidator.calculateTheOffsetForTheSelectedTime("01", "00", actualH, actualM));
        Assert.assertEquals(tomorrow, CalendarMinDateValidator.calculateTheOffsetForTheSelectedTime("00", "00", actualH, actualM));
        Assert.assertEquals(today, CalendarMinDateValidator.calculateTheOffsetForTheSelectedTime("23", "59", actualH, actualM));
        Assert.assertEquals(today, CalendarMinDateValidator.calculateTheOffsetForTheSelectedTime("21", "32", actualH, actualM));

    }


}
