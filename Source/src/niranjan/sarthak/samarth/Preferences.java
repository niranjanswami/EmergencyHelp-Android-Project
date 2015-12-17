package niranjan.sarthak.samarth;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class Preferences {
	public static final String PREF_NAME = "LOGIN_PREFERENCES";
	public static final int MODE = Context.MODE_PRIVATE;
	public static final String NAME = "username";
	public static final String PHONE_NUMBER = "phone_number";
	public static final String  HOMEADDRESS = "home address";
	public static final String EMERGENCY_CONTACT = "emergency_contact";
	public static final String EM_NUMBER = "em_number";
	public static final String BLOOD_TYPE = "blood_type";
	public static final String MESSAGE = "message";

	public static final String MLNG = "mlng";
	public static final String MLAT = "mlat";
	public static SharedPreferences getPreferences(Context context) {
		return context.getSharedPreferences(PREF_NAME, MODE);
	}
	public static Editor getEditor(Context context) {
		return getPreferences(context).edit();
	}
	public static void writeLatLng(Context context, String key, LatLng marker){
		boolean val;
		val = getEditor(context).putFloat(key, (float) marker.latitude).commit();
		Log.v("PREFERENCE LAT SAVED", Boolean.toString(val));
		getEditor(context).putFloat(MLNG, (float) marker.longitude).commit();
	}
	public static void writeString(Context context, String key, String value) {
		getEditor(context).putString(key, value).commit();

	}

	public static String readString(Context context, String key){
		return getPreferences(context).getString(key, null);
	}

	public static LatLng readMarker(Context context, String key){
		LatLng p = new LatLng(getPreferences(context).getFloat(MLAT, 0), getPreferences(context).getFloat(MLNG, 0));
		return p;
	}
}
