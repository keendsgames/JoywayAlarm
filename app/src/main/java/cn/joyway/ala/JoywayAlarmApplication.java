package cn.joyway.ala;

import java.util.List;
import cn.joyway.lib.AudioMgr;
import cn.joyway.lib.ConvertEx;
import cn.joyway.lib.PathHelper;
import cn.joyway.lib.bluetooth.BT;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;

public class JoywayAlarmApplication extends Application
{
	@SuppressLint("StaticFieldLeak")
	public static JoywayAlarmApplication mInstance = null;

	Context mContext = JoywayAlarmApplication.this;

	public void init()
	{
        ConvertEx.KEY = "ee607a84-49d6-4ede-aa92-98c11f8a88d2";

		PathHelper.init(JoywayAlarmApplication.sharedInstance());

		AudioMgr.init(JoywayAlarmApplication.sharedInstance());

		BT.init(JoywayAlarmApplication.sharedInstance(), 200,
				5000);

		BT.addScanFilter_tagNameEqual("JW-ALARM");
/*
		//BT.setNeedConnect("JW_ALARM",true);
		BT.setNeedConnect("CD:FB:08:65:D1:C9",true);
		//System.out.println("JoywayAlarmApplication setting NEED Connect  ******** ***** ");

		if (BT.isTagConnected("CD:FB:08:65:D1:C9"))
		System.out.println("JoywayAlarmApplication detected tag connected.... ******** ***** ");
		else
		System.out.println("JoywayAlarmApplication detected tag NOT connected.... ******** ***** ");
*/
		BT.setTagDefaultDisplayName(this.getString(R.string.app_name));
		BT.setScanPeriodTimeLength(4500, 5000);
		BT.setTagBeaconNotScannedTimeoutTimeLength(60*1000);
		BT.enableRssiSmoother(true, new float[]{
				0.1f,0.15f,   0.5f,  0.15f,0.1f});

		//app启动之后先扫描一下。
		// 解决BUG：app已经连接设备，此刻重启手机，手动启动app后，app不能连接设备
		//【现在】joywaylib里已经在连接失败的事件里，添加了扫描时间。
		//为了提高用户体验，这里也要添加10秒时间扫描。
		BT.appendTimeLengthToScan(1000*10);

		//
		//CrashHandler.getInstance(JoywayAlarmApplication.sharedInstance()).register();
	}

	@Override
	public void onCreate()
	{
		super.onCreate();
		
		mInstance = this;

		System.out.println("JoywayAlarmApplication starting :  ********success ***** ");

		init();

	}
	
	

	public static JoywayAlarmApplication sharedInstance() {
		return mInstance;
	}

	public boolean isAppOnForeground() {
		// Returns a list of application processes that are running on the
		String packageName = getApplicationContext().getPackageName();
		ActivityManager activityManager = (ActivityManager) getApplicationContext()
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
		if (appProcesses == null)
			return false;
		for (RunningAppProcessInfo appProcess : appProcesses) {
			// The name of the process that this object is associated with.
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				System.out.println("Tag device Name found:  ********success ***** "+packageName);
				return true;
			}
		}
		return false;
	}

	@Override
	public void onTerminate()
	{
		super.onTerminate();
		System.exit(0);
	}

	
	
	
	
}
