package cn.joyway.ala.data;

public class DeviceInfo 
{
	public String _mac;
	public String _name;
	public String _alertSwitchStatus;

	public DeviceInfo()
	{
		_mac = "No mac";
		_name = "No name";
		_alertSwitchStatus = Const.AlertSwitchStatus_Both;
	}
	
	public DeviceInfo(String mac, String name, String alertSwitchStatus)
	{
		_mac = mac;
		_name = name;
		_alertSwitchStatus = alertSwitchStatus;
	}
}
