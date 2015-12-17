
package niranjan.sarthak.samarth;

import java.util.ArrayList;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "services_db";
	private static final int DATABASE_VERSION = 1;

	public static final String NAME = "NAME";
	public static final String CONTACT = "CONTACT";
	public static final String LAT = "LAT";
	public static final String LNG = "LNG";
	public static final String TABLE_NAME = "ambulances";
	public static final String TABLE_HEADER = NAME+", "+CONTACT+", "+LAT+", "+LNG;

	@SuppressWarnings("unused")
	private ArrayList<String> result = new ArrayList<String>();
	
	public Database(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		result = new ArrayList<String>();

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}
}