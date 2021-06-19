package cn.joyway.ala.widget;

import android.app.Dialog;
import android.content.Context;
import android.text.TextPaint;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;


public class PublicStyle {


	public static void setTextPaint(TextView textView) {

		TextPaint textPint = textView.getPaint();
		textPint.setFakeBoldText(true);
	}


	public static void settingDialogPostion(WindowManager windowManager, Context context, Dialog dialog,
			float widthRatio, float heightRatio, float XRatio, float YRatio, int distanHeight) {

		if (null != dialog) {
			Window dialogWindow = dialog.getWindow();

			Display d = windowManager.getDefaultDisplay(); //
			WindowManager.LayoutParams p = dialogWindow.getAttributes(); //
			//
			p.width = (int) (d.getWidth() * widthRatio);
			p.height = (int) (d.getHeight() * heightRatio);
			p.x = (int) (d.getWidth() * XRatio);
			p.y = (int) (d.getHeight() * YRatio) / distanHeight;
			p.alpha = 0.8f;//80%
			dialogWindow.setGravity(Gravity.LEFT | Gravity.TOP);
			dialogWindow.setAttributes(p);
		}

	}
}
