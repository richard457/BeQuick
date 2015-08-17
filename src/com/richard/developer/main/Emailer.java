package com.richard.developer.main;

import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;





import java.util.Properties;


public class Emailer extends Activity implements android.view.View.OnClickListener{

	Session session = null;
	ProgressDialog pdialog = null;
	Context context = null;
	EditText reciep, sub, msg;
	String rec, subject, textMessage;
	final Random rand = new Random();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        
        context = this;
        
        Button login = (Button) findViewById(R.id.btn_submit);
        reciep = (EditText) findViewById(R.id.et_to);
       
//        sub = (EditText) findViewById(R.id.et_sub);
//        msg = (EditText) findViewById(R.id.et_text);
        
        login.setOnClickListener(this);
    }
	
	class RetreiveFeedTask extends AsyncTask<String, Void, String> {

		@Override
		protected String doInBackground(String... params) {
			
			try{
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress("beastar457@gmail.com"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
				message.setSubject(subject);
				message.setContent(textMessage, "text/html; charset=utf-8");
				Transport.send(message);
			} catch(MessagingException e) {
				e.printStackTrace();
			} catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			pdialog.dismiss();
			reciep.setText("");
			
			Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_LONG).show();
		}
	}
//
	public void onClick(View v) {
		// TODO Auto-generated method stub
		rec = reciep.getText().toString();
//		subject = sub.getText().toString();
		int diceRoll = rand.nextInt(6) + 100;
		subject ="you have booked a macth this is your code:"+diceRoll;
//		textMessage = msg.getText().toString();
		textMessage ="Thank you for using our service ";
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("beastar457@gmail.com", "umwana7890");
			}
		});
		
		pdialog = ProgressDialog.show(context, "", "Sending Mail...", true);
		
		RetreiveFeedTask task = new RetreiveFeedTask();
		task.execute();
	}
}
