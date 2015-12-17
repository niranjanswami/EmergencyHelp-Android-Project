package niranjan.sarthak.samarth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import niranjan.sarthak.samarth.R;
public class Settings extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
	}

	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.updateEM_button) {
			startActivity(new Intent(this,updateEM.class));
		} else if (id == R.id.updateUser_button) {
			startActivity(new Intent(this,UpdateUser.class));
		} else if (id == R.id.home_button) {
			startActivity(new Intent(this,MainActivity.class));
    		 this.finish();
		}
    }
	public void onDestroy(){
		super.onDestroy();
	}
	public void onPause(){
		super.onPause();
		Log.v(null, "Setting's onPause Method !!!");
	}
	public void onResume(){
		super.onResume();
		Log.v(null, "Setting's onResume Method !!!");
	}
	protected void onSaveInstanceState (Bundle outState){
		super.onSaveInstanceState(outState);
	}
	protected void onRestoreInstanceState (Bundle savedInstanceState){
		super.onRestoreInstanceState(savedInstanceState);
	}
}

