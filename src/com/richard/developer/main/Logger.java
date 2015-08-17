package com.richard.developer.main;


import com.richard.developer.database.Initiator;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("unused")
public class Logger extends Activity implements  android.view.View.OnClickListener {
	EditText first_team,second_team;
	Button retrieve;
	Button add,update;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_match);
        
        first_team =(EditText) findViewById(R.id.team_one);
        second_team=(EditText) findViewById(R.id.team_two);
        update=(Button) findViewById(R.id.update);
        retrieve=(Button) findViewById(R.id.retrieve_last);
        
        add =(Button) findViewById(R.id.add);
       add.setOnClickListener(this);
       update.setOnClickListener(this);
       retrieve.setOnClickListener(this);
        
    }
	public void onClick(View id) {
		// TODO Auto-generated method stub
		switch (id.getId()) {
		case R.id.add:
			boolean inserted =true;
			try{
			//add entry to database			
			String entry_one =first_team.getText().toString();
			String entry_two =second_team.getText().toString();
			Initiator entry= new Initiator(Logger.this);
			entry.open();
				entry.createEntry(entry_one, entry_two);
			entry.close();
			}catch (Exception e) {
				// TODO: handle exception
				inserted= false;
			}finally{
				if(inserted){
					Dialog d = new Dialog(this);
					d.setTitle("Yeah you are done!");
					TextView tv = new TextView(this);
					tv.setText("success");
					d.setContentView(tv);
					d.setCancelable(true);
					
					d.show();
					
					
				}
			}
			break;
		case R.id.retrieve_last:
			
			try {
				Class classToStart=Class.forName("com.example.androidtablayout.AllMatchPrivate");
				Intent intent = new Intent(this, classToStart);
				startActivity(intent);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				Toast.makeText(this, "class not found", Toast.LENGTH_LONG).show();
			}
			
//			startActivity(intent);
		break;
		case R.id.update:
			
		break;
		default:
			break;
		}
	}
}