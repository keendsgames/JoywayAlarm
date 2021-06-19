package cn.joyway.ala;

import java.util.Locale;

import cn.joyway.ala.data.Const;
import cn.joyway.ala.data.DeviceInfo;
import cn.joyway.lib.bluetooth.BT;
import cn.joyway.lib.bluetooth.OnTagEventHandler;
import cn.joyway.lib.bluetooth.Tag;
import cn.joyway.lib.bluetooth.TagConnectStatus;
import cn.joyway.lib.bluetooth.TagScanEvent;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class MainService extends Service implements OnTagEventHandler {
	@SuppressLint("StaticFieldLeak")
	static MainService _instance = null;
	Context _context = this;

	public static MainService sharedInstance() {
		return _instance;
	}

	@Override
	public void onCreate() {
		System.out.println("MainService started  ********* ");
	}

	@Override
	public void onDestroy() {
		BT.listenTagEvent(this, false);
	}

	//服务启动事件
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {

		_instance = this;

		BT.listenTagEvent(MainService.this, true);
		
		//NOTE: YOU SHOULD call BT.setNeedConnect
		/*for (DeviceInfo info : DBTable_MyTags.getAll()) {
			BT.setNeedConnect(info._mac, true);
		}*/

		return Service.START_STICKY;
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onTagScanStatusChanged(TagScanEvent event) {
		System.out.println("MainService: override onTagScanStatusChanged detected   ********* ");

	}

	@Override
	public void onTagConnectStatusChanged(final String tagMac, TagConnectStatus oldStatus,
										  TagConnectStatus newStatus) {
		System.out.println("MainService started: override onTagConnectStatusChanged detected  ********* ");

		//连上了
		if (newStatus == TagConnectStatus.Connected) {
			//recordTagLocation(tagMac);
			System.out.println("MainService method:  Tag connected *******");
			//连上了就查询一次电量
			BT.appendDataToSend(tagMac, "Bat?");
		}
		//断开了，如果5秒后都没有扫描到tag，也没有连接上，则要视为 “丢失了”
		else if (newStatus == TagConnectStatus.Disconnected) {
			System.out.println("MainService method:  Tag disconnected XXXXXXXX");
			//region 如果是从连接状态切换到断开，就需要在5秒后，根据情况决定是否报警提醒
			if (oldStatus == TagConnectStatus.Connected) {
				final long timeLengthToWait_ms = 5 * 1000;
				BT.appendTimeLengthToScan(timeLengthToWait_ms);//扫描
				BT.forceScanNow();//强制BT模块立刻切换到扫描状态

			}//endregion
		}
	}

	long _tmLastTagData = 0;

	@Override
	public void onTagData(String tagMac, byte[] data_bytes, String data_string) {
		//DeviceInfo tagInfo = DBTable_MyTags.getFirst(tagMac);
		//if (tagInfo == null)
		//	return;
		System.out.println("MainService started: override onTagData detected  ********* ");
		System.out.println("MainService: onTagDataChanged....... tagMac is: " + tagMac);
		System.out.println("MainService: onTagDataChanged.......  data_bytes is: " + data_bytes);
		System.out.println("MainService: onTagDataChanged....... data_string is: " + data_string);
		// Tag按下了按钮
		if (data_string.equals(Const.NUS_FLAG_TAG_CLICK)) {
			System.out.println("MainService method:  **********Tag click detected *******");
		}else if (data_string.indexOf(Const.NUS_FLAG_BAT_ANSWER) == 0){
			int batPercentInt = data_string.charAt(4);
			batPercentInt *= 1.1;
			if (batPercentInt > 100){
				batPercentInt = 100;
			}else if (batPercentInt < 0){
				batPercentInt = 0;
			}
			Tag tag = BT.getTag(tagMac);
			if (tag != null) {
				tag._batteryPercent = batPercentInt;
			}
		}
	}

	@Override
	public void onDataSentToTag(String tagMac, byte[] data_bytes, String data_string) {
		System.out.println("MainService method:  override onDataSentToTag detected ");
	}

	@Override
	public void onTagRssiChanged(String tagMac, int oldRssi, int newRssi) {
		System.out.println("MainService method:  override onTagRssiChanged detected ");
		if (oldRssi == -255 || newRssi == -255)
			return;

		//DeviceInfo tagInfo = DBTable_MyTags.getFirst(tagMac);
		//if (tagInfo == null)
		//	return;

		double distanceNow = BT.calculateDistance(Const.RSSI_AT_ONE_METER,
				Const.RSSI_DECREASE_FACTOR, newRssi);
		System.out.println("Calculated distanceNow is:   "+distanceNow);

	}

}
