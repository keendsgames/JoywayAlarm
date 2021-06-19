package cn.joyway.ala.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.joyway.ala.R;


public class MessageBox extends Dialog implements View.OnClickListener {

	Context context;

	TextView tv_messge, tv_ok, tv_cancel;
	RelativeLayout rl_cancel, rl_ok;
	View hovLine;

	public MessageBox(Context context) {

		super(context);

		this.context = context;
	}

	public MessageBox(Context context, String msg, int cancelButtonVisibility) 
	{
		super(context, R.style.public_dialog_style);
		
		this.context = context;
		this.setContentView(R.layout.view_msgbox);
		
		tv_messge = (TextView) findViewById(R.id.dialog_tv_messge);
		hovLine = findViewById(R.id.hovLine);
		tv_ok = (TextView) findViewById(R.id.dialog_tv_ok);
		tv_cancel = (TextView) findViewById(R.id.dialog_tv_cancel);
		rl_cancel = (RelativeLayout) findViewById(R.id.dialog_rl_cancel);
		rl_ok = (RelativeLayout) findViewById(R.id.dialog_rl_ok);

		PublicStyle.setTextPaint(tv_cancel);
		PublicStyle.setTextPaint(tv_messge);
		PublicStyle.setTextPaint(tv_ok);
		
		tv_messge.setText(msg);
		
		//
		hovLine.setVisibility(cancelButtonVisibility);
		rl_cancel.setVisibility(cancelButtonVisibility);

		//
		rl_ok.setOnClickListener(this);
		rl_cancel.setOnClickListener(this);
	}

	public void setOkTextviewText(String text) {

		tv_ok.setText(text);
	}

	public void setCancelTextviewText(String text) {
		
		tv_cancel.setText(text);
	}

	public void setOkButtonLister(android.view.View.OnClickListener listener) {

		rl_ok.setOnClickListener(listener);
	}

	public void setCancelButtonLister(android.view.View.OnClickListener listener) {
		
		rl_cancel.setOnClickListener(listener);
	}


	public void settingDialogPostion(Activity activity) {

		Window dialogWindow = getWindow();
		WindowManager windowManager = activity.getWindowManager();

		Display d = windowManager.getDefaultDisplay(); //
		WindowManager.LayoutParams p = dialogWindow.getAttributes(); //
		//
		p.width = (int) (d.getWidth() * 0.8);
		p.x = (int) (d.getWidth() * 0.1);
		p.y = (int) (d.getHeight() * 1) / 3 - 50;
		p.alpha = 0.8f;//
		dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
		dialogWindow.setAttributes(p);
	}

	@Override
	public void onClick(View v) {
		
		this.dismiss();
		
	}

}
