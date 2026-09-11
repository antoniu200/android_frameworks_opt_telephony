package com.android.internal.telephony;

import java.util.ArrayList;

import vendor.somc.hardware.radio.V1_0.ISomcHookIndication;

public class SomcHookIndication extends ISomcHookIndication.Stub {
    RIL mRil;

    public SomcHookIndication(RIL ril) {
        mRil = ril;
    }

    @Override
    public void somcHookRaw(int indicationType, ArrayList<Byte> data) {
        mRil.processIndication(indicationType);
    }
}
