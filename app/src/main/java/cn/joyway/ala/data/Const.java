package cn.joyway.ala.data;

import android.graphics.Color;

public class Const {

	public static String KEY_CONNECT_PHONE_ALARM = "LINKE_NOTICE_IPHONE_VOICE";
	public static String KEY_VIBRATE = "VIBRATION_SWITCHES";
	public static String KEY_ALARM_TIME = "CALL_ALARMING";
	public static String KEY_RING_NAME= "KEY_RINGING_NAME";
	public static String Ring01 = "Ring01";
	public static String Ring02 = "Ring02";
	public static String Ring03 = "Ring03";
	public static String Ring04 = "Ring04";
	public static String Ring05_LittleStar = "Little Star";
	public static String Ring07_Ding = "Ding";
	public static String Ring08_OdeToJoy = "Ode To Joy";
	public static String RingMute = "Mute";
	public static String RingDefault = "Ring 3";
	public static int RingMute_ResId = -1;
	public static String KEY_RECONNECT_TIME = "DISCONNECT_AGIN";
	public static String KEY_SAFE_DISTANCE = "SCOPE_LOST";
	public static String KEY_CHOICE_VOICE = "CHOICE_VOICE";
	public static String KEY_MAP_CHOOSE = "MAP_CHOOSE";
	public static String KEY_DEVICES_NAME = "KEY_DEVICES_NAME";
	public static String KEY_DEVICES_MAC = "KEY_DEVICES_MAC";
	public static String KEY_IS_PRINT_LOG = "KEY_IS_PRINT_LOG";
	public static String KEY_ENABLE_LOCATING = "KEY_ENABLE_LOCATING";
	public static final String ACTION_REFRESH_DEVICES_INFORI = "cn.joyway.ala.REFRESH_DEVICES_INFORI";
	public static String KEY_LOCATION_LAT = "KEY_LOCATION_LAT";
	public static String KEY_LOCATION_LNG = "KEY_LOCATION_LNG";
	public static String KEY_LOCATION_ADDRESS = "KEY_LOCATION_ADDRESS";
	public static String KEY_TAG_BUTTON_FUNCTION = "KEY_TAG_BUTTON_FUNCTION";
	public static String KEY_MAC = "KEY_MAC";
	public static String KEY_MAC_FALG = "KEY_MAC_FALG";
	/*public static final String KEY_PASSWORD_PREFIX = "PSW";*/
	public static final int KEY_CCONNECT = 0x7A66;
	public static final int KEY_DISCCONNECT = 0x7A67;
	public static final String RECIRE_DISCECETION_OPSERTION = "RECIRE_DISCECETION_OPSERTION";
	public static final String RECIRE_REFRESH_LOGO = "RECIRE_REFRESH_LOGO";
	public static final String KEY_GO_ME_VIEW_FALG = "KEY_GO_ME_VIEW_FALG";
	public static final String KEY_ACTIVITY_FALG = "KEY_ACTIVITY_FALG";
	public static final String RECIRE_LOCATION = "RECIRE_LOCATION";
	public static final String RECIRE_GAODE_LOCATION = "RECIRE_LOCATION";
	public static final int MEDIA_PHOTO_CALLBACK = 0x7A90;
	public static final int MEDIA_PHOTO_CROP = 0x7A91;

	public static final String GOOGLE_MAP = "GoogleMap";
	public static final String GAODE_MAP = "GaodeMap";
	public static final String UNKNOWN_MAP = "UnknownMap";

	public static final int ResultCodeForCloseSearchTag = 0x67;

	// region NUS 传输数据的标志串
	public static final String NUS_FLAG_TAG_CLICK = "Alert=True";
	public static final String NUS_FLAG_PASSWORD_REQUEST = "Psw?";
	public static final String NUS_FLAG_PASSWORD_ANSWER	 = "Psw:";
	public static final String NUS_FLAG_PASSWORD_MODIFY	 = "Psw=";
	public static final String NUS_FLAG_PASSWORD_ERROR = "PswError";
	public static final String NUS_FLAG_PASSWORD_CORRECT = "PswCorrect";
	public static final String NUS_FLAG_PARAM_SAVE_SUCCEED = "ParamSaveOK";
	public static final String NUS_FLAG_PARAM_SAVE_FAILED = "ParamSaveFailed";
	public static final String NUS_FLAG_REBOOT = "Reboot";
	public static final String NUS_FLAG_SLEEP = "Sleep";
	public static final String NUS_FLAG_BAT_ANSWER = "Bat=";
	public static final String NUS_FLAG_BAT_ASK = "Bat?";
	// endregion

	// region tag按钮功能选项 (5个)
	public final static String TagButtonFunction_FindPhone = "find_phone";
	public final static String TagButtonFunction_RecordAudio = "record_audio";
	public final static String TagButtonFunction_RecordVideo = "record_video";
	public final static String TagButtonFunction_TakePhoto = "take_picture";
	public final static String TagButtonFunction_UploadPhoneLocation = "upload_phone_location";
	// endregion

	public static final int CAMERA_CODE = 0x7A101; // 拍照请求码
	public static final int ALBUM_CODE = 0x7A102; // 相册请求码
	public static final int ZOOM_CODE = 0x7A103; // 剪裁请求码

	public static final int RequestCode_CloseSetting_Delete_TurnOff = 0x42;
	public static final int RequestCode_OpenSetting = 0x41;


	public static final int RSSI_AT_ZERO_METER = -60;
	public static final int RSSI_AT_ONE_METER = -60;
	public static final int RSSI_OF_SENSITIVITY = -107;
	public static final float RSSI_DECREASE_FACTOR = 3.1f;
	public static final float DISTANCE_SHAKE_FACTOR = 0.3f;


	public static final int Color_WarningText = Color.parseColor("#FF2200");//ff6600


	public static final String AlertSwitchStatus_None = "AlertSwitchStatus_None";
	public static final String AlertSwitchStatus_Both = "AlertSwitchStatus_Both";
	public static final String AlertSwitchStatus_Lost = "AlertSwitchStatus_Lost";
	public static final String AlertSwitchStatus_Found = "AlertSwitchStatus_Found";
}
