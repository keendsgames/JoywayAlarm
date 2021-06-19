package cn.joyway.ala;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;


public class BootBroadcastReceiver extends BroadcastReceiver {
		     
	static final String ACTION = "android.intent.action.BOOT_COMPLETED";
	
	@Override
	public void onReceive(Context context, Intent intent)
	{
		System.out.println("BootBroadcastReceiver started  ********* ");
		if (intent.getAction().equals(ACTION)) 
		{
			new Handler().postDelayed(new Runnable()
			{
				@Override
				public void run()
				{
					JoywayAlarmApplication.sharedInstance().init();

					/*
					open main window
		            Intent mainActivityIntent = new Intent(context, Activity_tagList.class);
		            mainActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		            context.startActivity(mainActivityIntent);
		            */
					
				}}, 5*60*1000);
			
        }

	}

}
