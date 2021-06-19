package cn.joyway.ala.data;

import java.util.HashMap;

public class TagRangeStatus {

    private static HashMap<String, Boolean> isTagInRange = new HashMap<>();

    public static boolean isTagInRange(String mac) {
        if (isTagInRange.containsKey(mac))
            return isTagInRange.get(mac);
        else
            return false;
    }

    public static void setTagInRange(String mac, boolean inRange) {
        isTagInRange.put(mac, inRange);
    }

}
