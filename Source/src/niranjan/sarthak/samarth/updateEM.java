package niranjan.sarthak.samarth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import niranjan.sarthak.samarth.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class updateEM extends Activity implements OnClickListener {

	public static EditText name, number; 
	public static String em_name, em_number;
	public static TextView fename, fenum;
	
	Pattern vname = Pattern.compile("[A-Za-z]+|[A-Za-z]+\\s[A-Za-z]+");
	Pattern vnumber = Pattern.compile("[0-9]{10}|[0-9]{7}");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.emergency_contact);
		name = (EditText)findViewById(R.id.name_field);
		number = (EditText)findViewById(R.id.number_field);
		number.setInputType(InputType.TYPE_CLASS_PHONE);
		em_name = Preferences.readString(this, Preferences.EMERGENCY_CONTACT);
		em_number = Preferences.readString(this, Preferences.EM_NUMBER);
		name.setText(em_name.toCharArray(), 0, em_name.length());
		number.setText(em_number.toCharArray(), 0, em_number.length());
		fename = (TextView)findViewById(R.id.format_emergency_name);
		fename.setVisibility(TextView.INVISIBLE);
		fenum = (TextView)findViewById(R.id.format_emergency_number);
		fenum.setVisibility(TextView.INVISIBLE);
	}

	public void save(){
		if(name.getText().toString() != null){
			Preferences.writeString(this, Preferences.EMERGENCY_CONTACT, name.getText().toString());
		}
		if(number.getText().toString() != null){
			Preferences.writeString(this, Preferences.EM_NUMBER, number.getText().toString());			
		}
		Toast.makeText(this, "successfully updated!", Toast.LENGTH_SHORT).show();
	}
	
	public boolean validator(){
		boolean ans = false;
		boolean nm = false;
		boolean num = false;
		if(name.getText().toString() != null){
			Matcher mname = vname.matcher(name.getText().toString());
			
			if(mname.matches()){
				name.setTextColor(Color.BLACK);
				fename.setVisibility(TextView.INVISIBLE);
				nm = true;
			} else {
				name.setTextColor(Color.RED);
				fename.setVisibility(TextView.VISIBLE);
				fename.setTextColor(Color.RED);
				nm=false;
				Log.v("VALIDATIONS", "INVALID NAME");
			}
		}
		if(number.getText().toString() != null){
			Matcher mnumber = vnumber.matcher(number.getText().toString());
			if(mnumber.matches()){
				number.setTextColor(Color.BLACK);
				fenum.setVisibility(TextView.INVISIBLE);
				num = true;
			} else {
				num = false;
				number.setTextColor(Color.RED);
				fenum.setVisibility(TextView.VISIBLE);
				fenum.setTextColor(Color.RED);
				Log.v("VALIDATIONS", "INVALID NUMBER");
			}

		}
		
		if(nm && num){
			ans = true;
		}
		return ans;
	}
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.updateEM_button) {
			if(validator()){
    			  save();
    			  this.finish();
    		  }
		} else if (id == R.id.cancel_button) {
			this.finish();
		}
    }
	public void onDestroy(){
		super.onDestroy();
		//STOP Services
	}
	public void onResume(){
		super.onResume();
		Log.v(null, "updateEM's onResume Method !!!");
	}
	public void onPause(){
		super.onPause();
		Log.v(null, "updateEM's onPause Method !!!");
	}
	protected void onSaveInstanceState (Bundle outState){
		super.onSaveInstanceState(outState);
	}
	protected void onRestoreInstanceState (Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
	}
}
