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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UpdateUser extends Activity implements OnClickListener {

	public static EditText name, address, phone;
	public static String username, user_address, number, blood;
	public static TextView fname, fnum, faddress;
	public static Spinner spinner;
	
	Pattern vname = Pattern.compile("[A-Za-z]+|[A-Za-z]+\\s[A-Za-z]+");
	Pattern vnumber = Pattern.compile("[0-9]{10}|[0-9]{7}");
	Pattern vaddr = Pattern.compile("[0-9]{1,2}\\s([A-Za-z]+\\s?([A-Za-z]{2,})?)\\s[A-Za-z]+,\\s[A-Za-z]{2,}");
	Pattern vblood = Pattern.compile("([O|o][+|-])|([A|a][+|-])|([B|b][+|-])|((AB|ab)[+|-])");
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_information);
		
		name = (EditText)findViewById(R.id.name_field);
		address = (EditText)findViewById(R.id.address_field);
		phone = (EditText)findViewById(R.id.number_field);
		phone.setInputType(InputType.TYPE_CLASS_PHONE);
		username = Preferences.readString(this, Preferences.NAME);
		user_address = Preferences.readString(this, Preferences.HOMEADDRESS);
		number = Preferences.readString(this, Preferences.PHONE_NUMBER);
		blood = Preferences.readString(this, Preferences.BLOOD_TYPE);
		name.setText(username.toCharArray(), 0, username.length());
		address.setText(user_address.toCharArray(), 0, user_address.length());
		phone.setText(number.toCharArray(),0, number.length());
		fname = (TextView)findViewById(R.id.format_name);
		fname.setVisibility(TextView.INVISIBLE);
		fnum = (TextView)findViewById(R.id.format_number);
		fnum.setVisibility(TextView.INVISIBLE);
		faddress = (TextView)findViewById(R.id.format_address);
		faddress.setVisibility(TextView.INVISIBLE);
		spinner = (Spinner)findViewById(R.id.blood_type_spinner);
		spinner.setSelection(Integer.parseInt(blood), true);
	}
	
	public void save(){
		if(name.getText().toString() != null){
			Preferences.writeString(this, Preferences.NAME, name.getText().toString());
		}
		if(address.getText().toString() != null){
			Preferences.writeString(this, Preferences.HOMEADDRESS, address.getText().toString());
		}
		if(phone.getText().toString() != null){
			Preferences.writeString(this, Preferences.PHONE_NUMBER, phone.getText().toString());
		}
		if(spinner.getSelectedItemPosition() != 0){
			Preferences.writeString(this, Preferences.BLOOD_TYPE, Integer.toString(spinner.getSelectedItemPosition()));
		}
		
		Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show();
	}
	
	public boolean validator(){
		boolean ans = false;
		boolean nm = false;
		boolean num = false;
		boolean addr = false;
		boolean bld = false;
		if(name.getText().toString() != null){
			Matcher mname = vname.matcher(name.getText().toString());
			
			if(mname.matches()){
				nm = true;
				name.setTextColor(Color.BLACK);
				fname.setVisibility(TextView.INVISIBLE);
			} else {
				nm=false;
				Log.v("VALIDATIONS", "INVALID NAME");
				name.setTextColor(Color.RED);
				fname.setVisibility(TextView.VISIBLE);
				fname.setTextColor(Color.RED);
			}
		}
		if(phone.getText().toString() != null){
			Matcher mnumber = vnumber.matcher(phone.getText().toString());
			if(mnumber.matches()){
				num = true;
				phone.setTextColor(Color.BLACK);
				fnum.setVisibility(TextView.INVISIBLE);
			} else {
				num = false;
				Log.v("VALIDATIONS", "INVALID NUMBER");
				phone.setTextColor(Color.RED);
				fnum.setVisibility(TextView.VISIBLE);
				fnum.setTextColor(Color.RED);
			}
		}
		if(address.getText().toString() != null){
			Matcher maddr = vaddr.matcher(address.getText().toString());
			
			if(maddr.matches()){
				addr = true;
				address.setTextColor(Color.BLACK);
				faddress.setVisibility(TextView.INVISIBLE);
			} else {
				addr = false;
				Log.v("VALIDATIONS", "INVALID ADDRESS");
				address.setTextColor(Color.RED);
				faddress.setVisibility(TextView.VISIBLE);
				faddress.setTextColor(Color.RED);
			}
		}
		if(spinner.getSelectedItemPosition() != 0){
			bld = true;
		}
		else {
			spinner.setBackgroundColor(Color.RED);
			bld = false;
		}
		if(nm && num && addr && bld){
			ans = true;
		}
		return ans;
	}
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.updateUser_button) {
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
		Log.v(null, "updateUser's onResume Method !!!");
	}
	public void onPause(){
		super.onPause();
		Log.v(null, "updateUser's onPause Method !!!");
	}
	protected void onSaveInstanceState (Bundle outState){
		super.onSaveInstanceState(outState);
	}
	protected void onRestoreInstanceState (Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
	}
}