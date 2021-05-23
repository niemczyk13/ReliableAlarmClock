package com.niemiec.reliablealarmclock;

import android.os.Bundle;
import android.system.Os;

import com.niemiec.reliablealarmclock.add.alarm.ActualTime;
import com.niemiec.reliablealarmclock.view.fragment.dialog.CalendarDialogFragment;
import com.niemiec.reliablealarmclock.view.fragment.dialog.CalendarMinDateValidator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.fragment.app.DialogFragment;



public class CalendarMinDateValidatorTest {

    @Test
    public void calculateTheOffsetForTheSelectedTimeTest() {
        int tomorrow = 86400000;
        int today = -1000;


        ActualTime actualTime ;

        //Assert.assertEquals(tomorrow, CalendarMinDateValidator.);
    }


}
