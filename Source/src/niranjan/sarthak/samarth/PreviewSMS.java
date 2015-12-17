package niranjan.sarthak.samarth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;
public class PreviewSMS extends Activity implements OnClickListener {
	public static EditText sms_message, phno;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.preview_sms);
		sms_message = (EditText)findViewById(R.id.sms_message);
		phno=(EditText) findViewById(R.id.phone_number);
		char[] sms = Preferences.readString(this, Preferences.MESSAGE).toCharArray();
		sms_message.setText(sms,0,sms.length);
	}

	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.send_button) {
			try{
    			  SmsManager.getDefault();
    			  	        Uri uri = Uri.parse("smsto:" + phno.getText().toString());
    			  	        Intent smsSIntent = new Intent(Intent.ACTION_SENDTO, uri);
    			  	        smsSIntent.putExtra("sms_body", sms_message.getText().toString());
    			  	        try{
    			  	            startActivity(smsSIntent);
    			  	        } catch (Exception ex) {
    			  	            Toast.makeText(PreviewSMS.this, "Your sms has failed...",
    			  	                    Toast.LENGTH_LONG).show();
    			  	            ex.printStackTrace();
    			  	        }
    			  	    }
    			  
    			  
    			  catch (Exception e) {
    			  e.printStackTrace();
    		  }
		} else if (id == R.id.cancel_button) {
			this.finish();
		}
	}
	public void onDestroy(){
		super.onDestroy();
	}
	public void onResume(){
		super.onResume();
		Log.v(null, "PreviewSMS's onResume Method !!!");
	}
	public void onPause(){
		super.onPause();
		Log.v(null, "PreviewSMS's onPause Method !!!");
	}
	protected void onSaveInstanceState (Bundle outState){
		super.onSaveInstanceState(outState);
	}
	protected void onRestoreInstanceState (Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
	}
}
