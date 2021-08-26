package cn.joyway.ala.activity;
//modified from http://www.joyway.cn/page_download.aspx
/*
changed Activity_main as the main method
modified Jun 10 '21
experiment with layout and printing statements
modified Jun 16 '21
connect tag by Mac Address in Activity_base
tag clicks:
2 short clicks:Alert=true ==> panic
long click (3+sec):SOS=true ==> reset
tag address input field and connect button
JoywayAlarm1.zip
modified Jun 18 '21  JoywayAlarm2.zip
sound and connect button text messages added
Aug 11 hardcoded mac C7:9D:FE:F0:31:48
working on updating data storage routine
modified Aug 19 '21 JoywayAlarm3.zip
define internal and external storage to store setup data: mac,sos msg,phoneno,soskey
menu,setup and exit pages
send sms by default message
app name: Panic Button


 */
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.media.ToneGenerator;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.gsm.SmsManager;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cn.joyway.ala.R;
import cn.joyway.ala.data.Const;
import cn.joyway.lib.bluetooth.BT;
import cn.joyway.lib.bluetooth.OnTagEventHandler;
import cn.joyway.lib.bluetooth.TagConnectStatus;
import cn.joyway.lib.bluetooth.TagScanEvent;


@SuppressLint("Registered")
public class Activity_base extends Activity implements OnTagEventHandler

{
	public String devicetag="";
	public Context mContext;
	public byte[] dbyte=null;
	public String ddd="";
	public ImageView tagimage;
	public TextView tagmac;
	//public String tagMacAd="CD:FB:08:65:D1:C9";
	public String tagMacAd,sosMsg,phoneNo,sosKey;
	public Button button1;
	@SuppressLint("StaticFieldLeak")


	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		mContext = Activity_base.this;
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
		//final EditText editText=findViewById(R.id.score1);
		tagmac = (TextView) findViewById (R.id.Keen);
		tagmac.setText("Tag Mac Address: "+tagMacAd );
		tagimage = (ImageView) findViewById (R.id.tagimage);

//reading data file from internal storage .............
		System.out.println("starting reading routine in Activity_base...");
		try {
            FileInputStream fileIn=openFileInput("mytextfile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn);
            BufferedReader r = new BufferedReader(new InputStreamReader(fileIn));
			String line;

			line=r.readLine();
			tagMacAd=line.trim();
			System.out.println("Data read from file."+tagMacAd);
			tagmac.setText("Tag Mac Address: "+tagMacAd );
			line=r.readLine();
			sosMsg=line.trim();
			System.out.println("Data read from file."+sosMsg);
			line=r.readLine();
			phoneNo=line.trim();
			System.out.println("Data read from file."+phoneNo);
			line=r.readLine();
			sosKey=line.trim();
			System.out.println("Data read from file."+sosKey);
			r.close();
			System.out.println("closing read file Activity_base...");
		} catch (Exception e) {
			System.out.println("error in reading setup...");
		}
//reading data from internal storage end........

/*
//reading data file from external storage .............
		System.out.println("starting reading routine in setup...");
		try {
			File root = new File(Environment.getExternalStorageDirectory(),"smscontacts");
			File secondInputFile = new File(root,"mysms.dat");

			InputStream secondInputStream = new BufferedInputStream(new FileInputStream(secondInputFile));
			BufferedReader r = new BufferedReader(new InputStreamReader(secondInputStream));
			String line;
			//while ((line = r.readLine()) != null) {
			//System.out.println("read line is in gamepanel= "+line);
			//} //while end
			// read strings
			line=r.readLine();
			tagMacAd=line.trim();
			System.out.println("Data read from file."+tagMacAd);
			tagmac.setText("Tag Mac Address: "+tagMacAd );
			line=r.readLine();
			sosMsg=line.trim();
			System.out.println("Data read from file."+sosMsg);
			line=r.readLine();
			phoneNo=line.trim();
			System.out.println("Data read from file."+phoneNo);
			line=r.readLine();
			sosKey=line.trim();
			System.out.println("Data read from file."+sosKey);
			r.close();
			secondInputStream.close();
			System.out.println("closing read file setup...");
		} catch (Exception e) {
			System.out.println("error in reading setup...");
		}
//reading data from external storage end........
*/
		//Connect ble button
		button1 = findViewById(R.id.button1);
		button1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				//tagMacAd = "C7:9D:FE:F0:31:48";
				System.out.println("button1 click  ******** ***** ");
				tagmac.setText("Tag Mac Address: "+tagMacAd );
				//System.out.println("tagMac input: "+tagMacAd);
				BT.setNeedConnect(tagMacAd,true);
				System.out.println("Main activity  setting NEED Connect  ******** ***** ");
				if (BT.isTagConnected(tagMacAd)) {
					System.out.println("Main activity detected tag connected.... ******** ***** ");
					button1.setText("Connected");
				}
				else
				button1.setText("Connecting");
			}
		});

		System.out.println("Activity_base started. Listening to TagEvents........... ");

		//String tagMacAd="CD:FB:08:65:D1:C9";

		//list available ble devices
		ArrayList arrlist=new ArrayList();
		arrlist= BT.getTagsWhoNeedConnectButDisconnectedNow();
		System.out.println("Tags waiting to be connected: *************** ");
		for (int i = 0; i < arrlist.size();i++)
		{
			System.out.println(arrlist.get(i));
		}

		BT.listenTagEvent(this, true);
		System.out.println("Main activity now listening tag event ");
	}



	@Override
	protected void onDestroy()
	{
		BT.listenTagEvent(this, false);

		super.onDestroy();
	}


	@Override
	public void onTagScanStatusChanged(TagScanEvent event) {
		System.out.println("Activity_base:: On TagScanStatus Changed..... ");
		if (BT.isTagConnected(tagMacAd))
			System.out.println("Main activity detected tag connected.... ******** ***** ");
		else
			System.out.println("Main activity detected tag NOT connected.... ******** ***** ");

	}

	@Override
	public void onTagConnectStatusChanged(String tagMac, TagConnectStatus oldStatus, TagConnectStatus newStatus) {
		//System.out.println("Activity_base: On TagConnectStatusChanged...... ");
	}

	@Override
	public void onTagData(String tagMac1, byte[] data_bytes, String data_string) {
		System.out.println("Activity_base: On TagData....... ");
		//data_string = tagMac;

		// Tag按下了按钮
		if (data_string.equals(Const.NUS_FLAG_TAG_CLICK)) {
			System.out.println("MainActivity_base On TagData........:  **********Tag click detected *******");
		}

		if (BT.isTagConnected(tagMacAd)) {
			System.out.println("Main activity detected tag connected.... ******** ***** ");
			button1.setText("Connected");
		}
		else {
			System.out.println("Main activity detected tag NOT connected.... ******** ***** ");
			button1.setText("Not Connected");
		}
		System.out.println("Main onTagScanStatusChanged calling onTagData....... tagMac is: " + tagMac1);
		System.out.println("Main onTagScanStatusChanged calling: onTagData.......  data_bytes is: " + data_bytes);
		System.out.println("Main onTagScanStatusChanged calling: onTagData....... data_string is: " + data_string);

		//if double click, set alarm  and sound
		if (data_string.equalsIgnoreCase("alert=true")) {
			//tagimage.setImageResource(R.drawable.alert_switch_lost);
			System.out.println("Alert click detected ");
			tagimage.setImageResource(R.drawable.tagalert);
			//Beep sound alarm
			ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);
			toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 200);

		//send an SMS
			System.out.println("Panic button tapped.... ********SMS sent to "+phoneNo+" ***** ");
			String scAddress = null;
			// Set pending intents to broadcast
			// when message sent and when delivered, or set to null.
			PendingIntent sentIntent = null, deliveryIntent = null;
			// Check for permission first.
			//checkForSmsPermission();
			// Use SmsManager.
			// Use SmsManager.
			SmsManager smsManager = SmsManager.getDefault();
			smsManager.sendTextMessage(phoneNo, scAddress, sosMsg,
					sentIntent, deliveryIntent);

		}
		//long clicks alarm reset
		else {
			//tagimage.setImageResource(R.drawable.alert_switch_none);
			System.out.println("Reset click detected");
			tagimage.setImageResource(R.drawable.tagnormal);
			//Notify sound reset
			Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
			Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
			r.play();
			setResult(RESULT_OK);
			finish();
		}

	}

	@Override
	public void onTagRssiChanged(String tagMac, int oldRssi, int newRssi)
	{
		devicetag=tagMac;
		//System.out.println("Activity_base: On TagRssiChanged..... ");
		//System.out.println("Activity_base: onTagRssiChanged....... tagAddress string is: "+tagMac+ "  oldRssi: "+oldRssi+"  newRssi: "+newRssi);
	}


	@Override
	public void onDataSentToTag(String tagMac, byte[] data_bytes, String data_string)
	{
		System.out.println("Activity_base:On DataSentToTag..... ");
		System.out.println("Activity_base:onDataSentToTag....... tagMac string is: "+tagMac);
	}


}