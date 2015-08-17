package com.richard.developer.main;

import java.net.PasswordAuthentication;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Properties;
import java.util.zip.Inflater;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import com.richard.developer.database.Initiator;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AllMatchPublic extends ListActivity {
	@SuppressWarnings("unused")
	private TextView text;
	
	private List<String> listvalue;
	Session session = null;
	ProgressDialog pdialog = null;
	Context context = null;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        text =(TextView) findViewById(R.id.paymentAmount);
        
        
        
        listvalue = new ArrayList<String>();
        setContentView(R.layout.all_matches);
        @SuppressWarnings("unused")
		Initiator ini=new Initiator(this);
        Initiator info = new Initiator(this);
		info.open();
		@SuppressWarnings("rawtypes")
		List data= info.getData();
		info.close();
		for(int i=0; i<data.size(); i++){
			String b=(String) data.get(i);
			listvalue.add(b);
		}
      ArrayAdapter<String> richardAdapter= new ArrayAdapter<String>(this, R.layout.row_layout,R.id.listText,listvalue);
      setListAdapter(richardAdapter);
    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
    	
    	super.onListItemClick(l, v, position, id);
    	//String selectedItem= (String) getListView().getItemAtPosition(position);
    	
    	
    	
    	LayoutInflater toBeinflated= LayoutInflater.from(this);
    	View setprompt=toBeinflated.inflate(R.layout.prompts, null);
    	AlertDialog.Builder buildpromptDialog= new AlertDialog.Builder(this);
    	buildpromptDialog.setView(setprompt);
    	LayoutInflater inflater = this.getLayoutInflater();
    	final View dialogView = inflater.inflate(R.layout.prompts, null);
    	
    	buildpromptDialog.setCancelable(false)
    	
    	.setPositiveButton("Pay With PayPal", new DialogInterface.OnClickListener() {
			//send email down here
    	//ss
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
				 EditText valueView = (EditText) dialogView.findViewById(R.id.emailer);
					Intent positveActivity = new Intent(getApplicationContext(), Emailer.class);
					positveActivity.putExtra("email", valueView.getText().toString());
					startActivity(positveActivity);
				
				
			}
		})
		.setNegativeButton("Cancel Payment", new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				
			}
		});
    	//text.setText((int) Math.random());//
    	buildpromptDialog.setIcon(R.drawable.ic_people);
    	buildpromptDialog.show();
    }    
}
