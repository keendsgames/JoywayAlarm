package cn.joyway.ala.data;

import android.app.AlertDialog;

public class DeviceAlarmInfo {

    public enum AlertDialogType
    {
        ForLookForPhone,
        ForOutSafeRange,
        ForLostConnection,
        ForFound,
    }

    public AlertDialog _alertDialog;
    public AlertDialogType _alertDialogType;

    public DeviceAlarmInfo(AlertDialog alertDialog,AlertDialogType alertDialogType)
    {
        _alertDialog = alertDialog;
        _alertDialogType = alertDialogType;
    }
}
