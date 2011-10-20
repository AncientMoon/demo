package com.thlight.example.simple_contacts;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailView extends Activity 
{
	TextView text1 = null;
	TextView text2 = null;
	TextView text3 = null;
	TextView text4 = null;
	TextView text5 = null;
	private String TAG = "Contacts";
	private String id = null;
	private String name = null;
	private String tel = null;
	private String cell = null;
	private String email = null;
	
	public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailview);
        text1 = (TextView)findViewById(R.id.t_detail1);
        text2 = (TextView)findViewById(R.id.t_detail2);
        text3 = (TextView)findViewById(R.id.t_detail3);
        text4 = (TextView)findViewById(R.id.t_detail4);
        text5 = (TextView)findViewById(R.id.t_detail5);
        
        id = getIntent().getExtras().getString("id");
        name = getIntent().getExtras().getString("name");
        tel = getIntent().getExtras().getString("tel");
        cell = getIntent().getExtras().getString("cell");
        email = getIntent().getExtras().getString("email");
        Log.i(TAG,name);
        text1.setText(id);
        text2.setText(name);
        text3.setText(tel);
        text4.setText(cell);
        text5.setText(email);
    }
}
