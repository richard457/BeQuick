package com.richard.developer.main;
import java.util.List;

import com.richard.developer.database.Initiator;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Retriever extends Activity implements android.view.View.OnClickListener{
	EditText f_team,s_team,rowId;
	Button up, delete,handleRet;
	Initiator info;
	Context context;
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.update_match);
		//select from database the last record inserted
		info = new Initiator(this);
		String s="1";
		info.open();
			List<?> data= info.retrieve(s);	
		info.close();
		f_team=(EditText) findViewById(R.id.first);
		s_team=(EditText) findViewById(R.id.second);
		rowId =(EditText) findViewById(R.id.rowId);
		handleRet=(Button) findViewById(R.id.rtnow);
		if(data == null){
			//data is null man
		}else{
			
			String a=(String) data.get(0);
			String b=(String) data.get(0);
			f_team.setText(a);
			s_team.setText(b);
		}
		//buttons
		up=(Button) findViewById(R.id.up);
		delete=(Button) findViewById(R.id.delete);
		handleRet.setOnClickListener(this);
		delete.setOnClickListener(this);
	}
	public void onClick(View view) {
		// TODO Auto-generated method stub
		switch (view.getId()) {
		case R.id.up:
			String Fteam = f_team.getText().toString();
			String Steam= s_team.getText().toString();
			String sRow=rowId.getText().toString();
			long lRow =Long.parseLong(sRow);
			Initiator updator=new Initiator(getApplicationContext());
			updator.open();
				updator.updateEntry(lRow, Fteam, Steam);
			updator.close();
			
			Toast.makeText(this, "you have updated Record", Toast.LENGTH_LONG).show();
			break;
		case R.id.delete:
			String sRow1=rowId.getText().toString();
			long lRow1 =Long.parseLong(sRow1);
			Initiator delete=new Initiator(getApplicationContext());
			delete.open();
				if(delete.DeleteEntry(lRow1)){
					Toast.makeText(this, "you have deleted Record", Toast.LENGTH_LONG).show();
				};
			delete.close();
			
			break;
		case R.id.rtnow:
				String id=rowId.getText().toString();
				long l =Long.parseLong(id);
				Initiator ini =new Initiator(getApplicationContext());
				ini.open();
				
				String returnedFTeam= ini.getFTeam(l);
				String returnedSTeam =ini.getSTeam(l);
				
				ini.close();
				f_team.setText(returnedFTeam);
				s_team.setText(returnedSTeam);
			break;
		default:
			break;
		}
	}
	public String getFirstTeam(String s){
		
		Initiator now =new Initiator(this);
		now.open();
			String returnedFTeam=now.getFirstTeam("1");
			
		now.close();
		return returnedFTeam;
		
	}
	public Retriever() {
		// TODO Auto-generated constructor stub
		
	}
	
}
