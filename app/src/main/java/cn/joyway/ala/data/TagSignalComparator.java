package cn.joyway.ala.data;

import java.util.Comparator;

import cn.joyway.lib.bluetooth.BT;
import cn.joyway.lib.bluetooth.Tag;

public class TagSignalComparator implements Comparator<DeviceInfo>
{
	@Override
	public int compare(DeviceInfo info0, DeviceInfo info1) 
	{
		Tag tag0 = BT.getTag(info0._mac);
		Tag tag1 = BT.getTag(info1._mac);
		return tag0.getRssiNew() - tag1.getRssiNew();
	}
}
