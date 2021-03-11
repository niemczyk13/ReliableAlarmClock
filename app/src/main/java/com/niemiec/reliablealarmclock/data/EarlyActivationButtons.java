package com.niemiec.reliablealarmclock.data;

import android.widget.EditText;
import android.widget.RadioButton;

public class EarlyActivationButtons {
    private static RadioButton precentChoiceButton;
    private static RadioButton timeChoiceButton;
    private static EditText precentOrTime;

    public static void setDefaultValue(RadioButton precent, EditText pOrT, int value) {
        precent.setChecked(true);
        pOrT.setText(Integer.toString(5));
    }
}
