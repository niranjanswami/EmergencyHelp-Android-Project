package niranjan.sarthak.samarth;

import java.io.IOException;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class BloodBank extends Activity implements OnClickListener {
	public static EditText guest_name, guest_number, gps_field;
	public static CheckBox username, usersblood, usersnumber, gps_checkbox;

	public static final String GUESTNAME = "guestname";
	public static final String GUESTBLOOD = "guestblood";
	public static final String GUESTNUMBER = "guestnumber";
	public static final String GPSFIELD = "gpsfield";

	public static CharSequence gname, gblood, gnumber, gpfield;
	public static Spinner spinner;

	LocationManager lm;
	LocationListener locationListener;
	Location loc;
	Geocoder gc;
	List<Address> addresses;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blood_bank);

		guest_name = (EditText) findViewById(R.id.guest_name_field);
		// guest_blood = (EditText)findViewById(R.id.guest_blood_type_field);
		guest_number = (EditText) findViewById(R.id.guest_phone_number_field);
		guest_number.setInputType(InputType.TYPE_CLASS_PHONE);
		gps_field = (EditText) findViewById(R.id.location_field);
		username = (CheckBox) findViewById(R.id.name_checkBox);
		usersblood = (CheckBox) findViewById(R.id.blood_type_checkBox);
		usersnumber = (CheckBox) findViewById(R.id.phone_number_checkBox);
		gps_checkbox = (CheckBox) findViewById(R.id.gps_checkBox);
		lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationListener = new MyLocationListener();
		loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		gc = new Geocoder(this);
		spinner = (Spinner) findViewById(R.id.blood_type_spinner);
	}

	private class MyLocationListener implements LocationListener {
		@Override
		public void onLocationChanged(Location loc) {
			if (loc != null) {
				try {
					addresses = gc.getFromLocation(loc.getLatitude(),
							loc.getLongitude(), 1);
				} catch (IOException e) {
					Log.d("ERROR", "UNABLE TO GET LOCATION");
				}
			}
		}

		@Override
		public void onProviderDisabled(String provider) {
			Context context = getBaseContext();
			String title = "Warning!";
			String message = "Provider: " + provider + " disabled";
			String button1String = "Ok";
			AlertDialog.Builder ad = new AlertDialog.Builder(context);
			ad.setTitle(title);
			ad.setMessage(message);
			ad.setPositiveButton(button1String,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int arg1) {
							dialog.cancel();
						}
					});
			ad.show();
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}

	public void preview() {
				Boolean msg = false;
		StringBuilder message = new StringBuilder();

		message.append("Hi, this is ");
		if (username.isChecked()) {
			message.append(Preferences.readString(this, Preferences.NAME));
		} else {
			if (guest_name.length() != 0) {
				message.append(guest_name.getText().toString());
			} else {
				msg = true;
			}
		}
		message.append(". I got into an accident and am in need of blood. I have a blood type of ");
		LinearLayout lay = (LinearLayout) findViewById(R.id.blood_layout);
		if (usersblood.isChecked()) {
			message.append(spinner.getItemAtPosition(Integer
					.parseInt(Preferences.readString(this,
							Preferences.BLOOD_TYPE))));
			lay.setBackgroundColor(Color.LTGRAY);
		} else {
			if (username.isChecked()) {
				lay.setBackgroundColor(Color.LTGRAY);
				usersblood.setBackgroundColor(Color.RED);
				msg = true;
			} else {
				if (spinner.getSelectedItemPosition() != 0) {
					message.append(spinner.getSelectedItem().toString());
					lay.setBackgroundColor(Color.LTGRAY);
				} else {

					lay.setBackgroundColor(Color.RED);
					msg = true;
				}
			}
		}
		message.append(" and am in ");
		if (gps_field.length() != 0) {
			gps_field.setBackgroundColor(Color.WHITE);
			message.append(gps_field.getText().toString());
			// msg=true;
		} else {
			Log.v("NIRU", "Red");
			gps_field.setBackgroundColor(Color.RED);
			msg = true;
		}
		message.append(". If you are near this area and have this blood type, please contact me at: ");
		if (usersnumber.isChecked()) {
			message.append(Preferences.readString(this,
					Preferences.PHONE_NUMBER));
		} else {
			if (guest_number.length() != 0) {
				message.append(guest_number.getText().toString());
			} else {
				msg = true;
			}
		}
		message.append(".\nThank you!");
		if (msg == false) {
			Preferences.writeString(this, Preferences.MESSAGE,
					message.toString());
			startActivity(new Intent(this, PreviewSMS.class));
		} else {
		}
	}

	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.previewSMS_button) {
			preview();
		} else if (id == R.id.home_button) {
			this.finish();
		}
	}
	public void onResume() {
		super.onResume();
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
				locationListener);
		Log.v(null, "Bloodbank's onResume Method !!!");
	}
	public void onPause() {
		super.onPause();
		lm.removeUpdates(locationListener);
		Log.v(null, "Bloodbank's onPause Method !!!");
	}
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		if (guest_name.length() != 0) {
			outState.putString(GUESTNAME, guest_name.getText().toString());
		}
		if (spinner.getSelectedItem() != null) {
			outState.putString(GUESTBLOOD, spinner.getSelectedItem().toString());
		}
		if (guest_number.length() != 0) {
			outState.putString(GUESTNUMBER, guest_number.getText().toString());
		}
		if (gps_field.length() != 0) {
			outState.putString(GPSFIELD, gps_field.getText().toString());
		}
	}
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if (savedInstanceState.getString(GUESTNAME) != null) {
			gname = savedInstanceState.getString(GUESTNAME).subSequence(0,
					savedInstanceState.getString(GUESTNAME).length());
		}

		if (savedInstanceState.getString(GUESTBLOOD) != null) {
			gblood = savedInstanceState.getString(GUESTBLOOD).subSequence(0,
					savedInstanceState.getString(GUESTBLOOD).length());
		}

		if (savedInstanceState.getString(GUESTNUMBER) != null) {
			gnumber = savedInstanceState.getString(GUESTNUMBER).subSequence(0,
					savedInstanceState.getString(GUESTNUMBER).length());
		}

		if (savedInstanceState.getString(GPSFIELD) != null) {
			gpfield = savedInstanceState.getString(GPSFIELD).subSequence(0,
					savedInstanceState.getString(GPSFIELD).length());
		}
	}
}