package com.richard.developer.main;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//Author Muragijimana Richard all right reserved Developer of Android and PHP
@SuppressWarnings("unused")
public class AdminPanel extends Activity implements android.view.View.OnClickListener {
	EditText email,password;
	Button btnLogin;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); 
        btnLogin=(Button) findViewById(R.id.btnLogin);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        btnLogin.setOnClickListener(this);
        
     }
	public void onClick(View arg0) {
		String em=email.getText().toString();
		String pass=password.getText().toString();
		Toast.makeText(this, "we are here", Toast.LENGTH_LONG).show();
		if(em.equals("admin@app.com") && pass.equals("@123") ){
			try {
				//here
				Class myclass = Class.forName("com.richard.developer.main.Login");
				Intent intent = new Intent(getApplicationContext(), myclass);
				startActivity(intent);
			} catch (ClassNotFoundException e) {
				// this won't happen but for a good developer it should be here
				Toast.makeText(getApplicationContext(), "class not found", Toast.LENGTH_LONG).show();
			}
		}
	}
	//
}