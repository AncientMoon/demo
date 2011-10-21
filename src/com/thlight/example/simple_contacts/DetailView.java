package com.thlight.example.simple_contacts;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
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
	private Button mEdit = null;
	private Button mDelete = null;
	
	public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailview);
        text1 = (TextView)findViewById(R.id.t_detail1);
        text2 = (TextView)findViewById(R.id.t_detail2);
        text3 = (TextView)findViewById(R.id.t_detail3);
        text4 = (TextView)findViewById(R.id.t_detail4);
        text5 = (TextView)findViewById(R.id.t_detail5);
        mEdit = (Button)findViewById(R.id.b_detailview_edit);
        mDelete = (Button)findViewById(R.id.b_detailview_delete);
        
        id = getIntent().getExtras().getString("id");
        name = getIntent().getExtras().getString("name");
        tel = getIntent().getExtras().getString("tel");
        cell = getIntent().getExtras().getString("cell");
        email = getIntent().getExtras().getString("email");


        text1.setText(id);
        text2.setText(name);
        
       
        if(tel==null)
        	text3.setVisibility(8);
        else
        	text3.setText(tel);
        text4.setText(cell);
        text5.setText(email);
        
        mEdit.setOnClickListener(new Button.OnClickListener()
    	{
            public void onClick(View v)
            {
            	try {
            		Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putString("id", id);
                    bundle.putString("name", name);
                    bundle.putString("tel", tel);
                    bundle.putString("cell", cell);
                    bundle.putString("email", email);
                    intent.putExtras(bundle);
                    intent.setClass(DetailView.this, EditContact.class);
                    startActivity(intent);
        		} catch (Throwable e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
          	}        	
    	});
        mDelete.setOnClickListener(new Button.OnClickListener()
    	{
            public void onClick(View v)
            {
            	try {
            		new AlertDialog.Builder(DetailView.this)
    				.setTitle("Alert")
    				.setMessage("是否刪除此聯絡人")
    				.setCancelable(false)
    				.setOnKeyListener(new DialogInterface.OnKeyListener() {
    					@Override
    					public boolean onKey(DialogInterface dialog, int keyCode,
    							KeyEvent event) {
    						if (keyCode == KeyEvent.KEYCODE_SEARCH
    								&& event.getRepeatCount() == 0) {
    							return true; // Pretend we processed it
    						}
    						return false; // Any other keys are still processed as
    										// normal
    					}
    				})
    				.setPositiveButton("Submit",
    						new DialogInterface.OnClickListener() {
    							public void onClick(DialogInterface dialog,
    									int which) {
    							//	myVib.vibrate(50);
    								DeleteContact();
    								finish();
    							}
    						})
    				.setNegativeButton("Cancel",
    						new DialogInterface.OnClickListener() {
    							public void onClick(DialogInterface dialog,
    									int which) {
    							//	myVib.vibrate(50);
    								finish();
    							}
    						}).show();
	            		
	            		
	            
        		} catch (Throwable e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
          	}        	
    	});
    }
	private void DeleteContact()
    {
    	ContentResolver resolver = this.getContentResolver();  
        ContentValues values = new ContentValues();// 参数集合  
        Uri url = RawContacts.CONTENT_URI;
        resolver.delete(url, "_ID = '"+id+"'", null);
    }
}
