package cn.joyway.ala.widget;

import cn.joyway.ala.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


@SuppressLint("InflateParams")
public class ActionBar extends RelativeLayout {

	LayoutInflater mInflater;
	ViewGroup mBarView;
	TextView mTitleTV;
	TextView mRightTV;
	ImageView mLeftImageview;
	RelativeLayout mRightRayout;
	RelativeLayout actionbar_left_layout;

	ImageView mRightImageview;

	public ActionBar(Context context) {
		super(context);
		init(context);
	}

	public ActionBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	void init(Context context) {
		mInflater = (LayoutInflater) context.getApplicationContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mBarView = (ViewGroup) mInflater.inflate(R.layout.widget_actionbar,
				null);

		addView(mBarView);
		mTitleTV = (TextView) mBarView.findViewById(R.id.actionbar_title);
		mRightTV = (TextView) mBarView.findViewById(R.id.actionbar_right_txt);
		mLeftImageview = (ImageView) mBarView
				.findViewById(R.id.actionbar_left_image);
		mRightImageview = (ImageView) mBarView
				.findViewById(R.id.actionbar_right_image);
		actionbar_left_layout = (RelativeLayout) mBarView
				.findViewById(R.id.rl_backToTagList);
		mRightRayout = (RelativeLayout) mBarView
				.findViewById(R.id.actionbar_right_layout);
	}


	public void setTitle(Object object) {
		if (object instanceof Integer) {
			mTitleTV.setText((Integer) object);
		} else if (object instanceof String) {
			mTitleTV.setText(String.valueOf(object));
		}
	}


	public void setRightTxt(Object object) {
		if (object instanceof Integer) {
			mRightTV.setText((Integer) object);
		} else if (object instanceof String) {
			mRightTV.setText(String.valueOf(object));
		}
	}

	public void setActionBarBackground(int resid) {
		mBarView.setBackgroundResource(resid);
	}


	public void setLeftImage(int resid) {
		mLeftImageview.setBackgroundResource(resid);
	}


	public void setRightImage(int resid) {
		mRightImageview.setBackgroundResource(resid);
	}


	public void setLeftListenner(OnClickListener listenner) {
		mLeftImageview.setOnClickListener(listenner);
	}


	public void setLeftLayoutListenner(OnClickListener listenner) {
		actionbar_left_layout.setOnClickListener(listenner);
	}

	public void setLeftGongOrVisibility(int visibility) {
		mLeftImageview.setVisibility(visibility);
	}


	public void setRightImageviewGongOrVisibility(int visibility) {
		mRightImageview.setVisibility(visibility);
	}

	public void setRightListenner(OnClickListener listenner) {
		mRightRayout.setOnClickListener(listenner);
	}

	public void setRighTexttListenner(OnClickListener listenner) {
		mRightTV.setOnClickListener(listenner);
	}
	

	public void setRightTextGongOrVisibility(int visibility) {
		mRightTV.setVisibility(visibility);
	}

	public void setRightGongOrVisibility(int visibility) {
		mRightRayout.setVisibility(visibility);
	}
}
