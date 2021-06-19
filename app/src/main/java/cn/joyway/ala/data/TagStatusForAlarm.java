package cn.joyway.ala.data;

import java.util.HashMap;

public class TagStatusForAlarm {

    private static HashMap<String, DeviceAlarmInfo> _alertDialogMap = new HashMap<>();
    /*private static HashMap<String, Long> _tagLatestLostAlarmTime_ms = new HashMap<>();

    public static void setTagLatestLostAlarmTime_ms(String tagMac, Long timetick) {
        _tagLatestLostAlarmTime_ms.put(tagMac, timetick);
    }

    public static long getTagLatestLostAlarmTime_ms(String tagMac) {
        if (_tagLatestLostAlarmTime_ms.containsKey(tagMac)) {
            return _tagLatestLostAlarmTime_ms.get(tagMac);
        }else{
            return 0;
        }
    }*/

    public static void addDeviceInfoAndShowAlertDialog(String mac, DeviceAlarmInfo deviceAlarmInfo)
    {
        _alertDialogMap.put(mac, deviceAlarmInfo);

        if(deviceAlarmInfo._alertDialog!=null)
        {
            deviceAlarmInfo._alertDialog.show();
        }
    }

    public static DeviceAlarmInfo getDeviceAlarmInfo(String mac)
    {
        if(_alertDialogMap.containsKey(mac))
        {
            return _alertDialogMap.get(mac);
        }
        return null;
    }

    public static boolean mapContainsDeviceAlarmInfo(String mac)
    {
        return _alertDialogMap.containsKey(mac);
    }

    public static void dismissAndRemoveDeviceAlarmInfo(String mac)
    {
        if(_alertDialogMap.containsKey(mac))
        {
            DeviceAlarmInfo deviceAlarmInfo = _alertDialogMap.get(mac);
            if( deviceAlarmInfo!=null && deviceAlarmInfo._alertDialog!=null )
            {
                deviceAlarmInfo._alertDialog.dismiss();
            }
            _alertDialogMap.remove(mac);
        }
    }

    public static void removeDeviceAlarmInfo(String mac)
    {
        _alertDialogMap.remove(mac);
    }
}
