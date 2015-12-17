package niranjan.sarthak.samarth;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import niranjan.sarthak.samarth.R;

public class MainActivity extends Activity {
	private static final int HOSPITAL = 1;
	private static final int POLICE = 2;
	private static final int FIRESTATION = 3;
	private static final int AMBULANCE = 4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		if(Preferences.readString(this, Preferences.NAME) == null){
			startActivity(new Intent(this, Registration.class));
			if(Preferences.readString(this, Preferences.NAME) == null){
				this.finish();
			}
		} else {
			setContentView(R.layout.activity_main);
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.sos_button) {
			startActivity(new Intent(this, Sos.class));
		} else if (id == R.id.firestation_button) {
			Intent firestation = new Intent(this, Maps.class);
			firestation.putExtra("key", FIRESTATION);
			startActivity(firestation);
		} else if (id == R.id.hospital_button) {
			Intent hospital = new Intent(this, Maps.class);
			hospital.putExtra("key", HOSPITAL);
			startActivity(hospital);
		} else if (id == R.id.ambulance_button) {
			Intent ambulance = new Intent(this, Maps.class);
			ambulance.putExtra("key", AMBULANCE);
			startActivity(ambulance);
		} else if (id == R.id.police_button) {
			Log.v("NIRU", "Police");
			Intent police = new Intent(this, Maps.class);
			police.putExtra("key", POLICE);
			startActivity(police);
		} else if (id == R.id.bloodbank_button) {
			Log.v("NIRU", "BloodBank");
			startActivity(new Intent(this,BloodBank.class));
		} else if (id == R.id.emergencycontact_button) {
			try{
      		  StringBuilder number = new StringBuilder();
      		  number.append("tel:");
      		  number.append(Preferences.readString(this, Preferences.EM_NUMBER));
      		  Intent callIntent = new Intent (Intent.ACTION_CALL);
      		  callIntent.setData(Uri.parse(number.toString()));
      		  startActivity(callIntent);
      		}catch (ActivityNotFoundException activityException) {
      			Log.e("First Response", "Call failed");
      		}
		} else if (id == R.id.home_button) {
			try{
    		  StringBuilder number = new StringBuilder();
    		  number.append("tel:");
    		  number.append(Preferences.readString(this, Preferences.PHONE_NUMBER));
    		  Intent callIntent = new Intent (Intent.ACTION_CALL);
    		  callIntent.setData(Uri.parse(number.toString()));
    		  startActivity(callIntent);
    		}catch (ActivityNotFoundException activityException) {
    			Log.e("First Response", "Call failed");
    		}
		} else if (id == R.id.settings_button) {
			startActivity(new Intent(this,Settings.class));
		}
    }
	public void onResume(){
		super.onResume();
		Log.v(null, "MainActivity's onResume Method !!!");
	}

	public void onPause(){
		super.onPause();
		Log.v(null, "MainActivity's onPause Method !!!");
	}
	protected void onSaveInstanceState (Bundle outState){
		super.onSaveInstanceState(outState);
	}
	protected void onRestoreInstanceState (Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
	}
}