package com.richard.developer.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Initiator {
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_FIRST_TEAM= "team_first";
	public static final String KEY_SECOND_TEAM= "team_second";
	private static final String DATABASE_NAME= "funGames";

	private static final String DATABASE_TABLE= "matchesTable";
	private static final int DATABASE_VERSION= 1;
	private DbHelper ourHelper;
	private final  Context ourContext;
	private SQLiteDatabase ourDatabase;
	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" +
					KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_FIRST_TEAM + " TEXT NOT NULL, " +
					KEY_SECOND_TEAM + " TEXT NOT NULL );"
			);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
			onCreate(db);
		}
		
		
	}
	public Initiator(Context c){
		ourContext  =c;
	}
	public Initiator open() throws SQLException{
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
		
	}
	public void close(){
		ourHelper.close();
		}
	public long createEntry(String entry_one, String entry_two) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_FIRST_TEAM, entry_one);
		cv.put(KEY_SECOND_TEAM, entry_two);
		return ourDatabase.insert(DATABASE_TABLE, null, cv);
	}
	public List<String> getData() {
		// TODO Auto-generated method stu
		String[] columns = new String[]{KEY_ROWID, KEY_FIRST_TEAM, KEY_SECOND_TEAM};
		Cursor c = ourDatabase.query(DATABASE_TABLE, columns,null, null, null, null, null);
		
		List<String> t =new ArrayList<String>();
		@SuppressWarnings("unused")
		int iRow = c.getColumnIndex(KEY_ROWID);
		
		@SuppressWarnings("unused")
		int iFTeam=c.getColumnIndex(KEY_FIRST_TEAM);
		int iFSeam=c.getColumnIndex(KEY_SECOND_TEAM);
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			t.add(c.getString(iFSeam));
			t.add(c.getString(iFTeam));
		}
		return t;
	}
	public String getFirstTeam(String l) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_FIRST_TEAM, KEY_SECOND_TEAM};
		Cursor c=ourDatabase.query(DATABASE_TABLE,  columns, KEY_ROWID + "=" +l, null, null, null, null);
		if(c !=null){
			c.moveToFirst();
			String fname =c.getString(1);
			return fname;
		}
		return null;
		
	
	}

	public List<String> retrieve(String id) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_FIRST_TEAM, KEY_SECOND_TEAM};
		Cursor c=ourDatabase.query(DATABASE_TABLE, columns, KEY_ROWID, null, KEY_ROWID, id, null);
		List<String> t =new ArrayList<String>();
		if(c !=null){
			c.moveToFirst();
			String fname =c.getString(1);
		   t.add(fname);
		}
		return t;
		
	}
	public String getFTeam(long l) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_FIRST_TEAM, KEY_SECOND_TEAM}; 
		Cursor c=ourDatabase.query( DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null, null);
		if(c !=null){
			c.moveToFirst();
			String fTeam=c.getString(1); 
			return fTeam;
		}
		return null;
	}
	public String getSTeam(long l) {
		// TODO Auto-generated method stub
		String[] columns = new String[]{KEY_ROWID, KEY_FIRST_TEAM, KEY_SECOND_TEAM}; 
		Cursor c=ourDatabase.query( DATABASE_TABLE, columns, KEY_ROWID + "=" + l, null, null, null, null, null);
		if(c !=null){
			c.moveToFirst();
			String STeam=c.getString(2); 
			return STeam;
		}
		return null; 
	}
	public void updateEntry(long lRow, String fteam, String steam) {
		// TODO Auto-generated method stub
		ContentValues cvUpdate = new ContentValues(); 
		cvUpdate.put(KEY_FIRST_TEAM, fteam);
		cvUpdate.put(KEY_SECOND_TEAM, steam);
		ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
	}
	public boolean DeleteEntry(long dRow) {
		// TODO Auto-generated method stub
		ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" +dRow, null);
		return true;
	}
} 
