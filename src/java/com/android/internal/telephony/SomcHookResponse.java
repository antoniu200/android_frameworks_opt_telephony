package com.android.internal.telephony;

import android.hardware.radio.V1_0.RadioResponseInfo;

import java.util.ArrayList;

import vendor.somc.hardware.radio.V1_0.ISomcHookResponse;

public class SomcHookResponse extends ISomcHookResponse.Stub {
    RIL mRil;

    public SomcHookResponse(RIL ril) {
        mRil = ril;
    }

    @Override
    public void sendSomcRequestRawResponse(
            RadioResponseInfo responseInfo, ArrayList<Byte> data) {
        RILRequest rr = mRil.processResponse(responseInfo);

        if (rr != null) {
            byte[] ret = null;

            if (responseInfo.error == 0) {
                ret = RIL.arrayListToPrimitiveArray(data);
                RadioResponse.sendMessageResponse(rr.mResult, ret);
            }

            mRil.processResponseDone(rr, responseInfo, ret);
        }
    }
}
