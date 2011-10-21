package com.thlight.example.simple_contacts;

import java.security.Provider;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.Phones;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts.Data;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditContact extends Activity 
{	
	private Button mBtnAdd = null;
	private EditText mE_name = null;
	private EditText mE_phone = null;
	private EditText mE_email = null;
	private String id = null;
	private String name = null;
	private String tel = null;
	private String cell = null;
	private String email = null;
	private String TAG = "Contacts";
	private Provider mMyProvider;
	public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contect);
        id = getIntent().getExtras().getString("id");
        name = getIntent().getExtras().getString("name");
        tel = getIntent().getExtras().getString("tel");
        cell = getIntent().getExtras().getString("cell");
        email = getIntent().getExtras().getString("email");
        FindView();
        mBtnAdd.setOnClickListener(new Button.OnClickListener()
    	{
            public void onClick(View v)
            {
            	try {
            		Log.i(TAG, "---------------------HERE oR NOT");
            		edit();
            		Log.i(TAG, "HERE oR NOT");
            		finish();
        		} catch (Throwable e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
          	}        	
    	});
        
    }
	public void FindView()
	{
		mBtnAdd = (Button)findViewById(R.id.b_add_contacts_add);
		mE_name = (EditText)findViewById(R.id.e_add_contacts_name);
		mE_phone = (EditText)findViewById(R.id.e_add_contacts_phone);
		mE_email = (EditText)findViewById(R.id.e_add_contacts_email);
		mE_name.setText(name);
		mE_phone.setText(cell);
		mE_email.setText(email);
		mBtnAdd.setText("Update");
	}
	public void edit()
	{

		ContentResolver resolver = this.getContentResolver();  
        ContentValues values = new ContentValues();// 参数集合  
     //   Uri url = resolver.insert(RawContacts.CONTENT_URI, values);// 插入空记录 
     // 往data表入姓名数据  
        values.clear();  
        values.put(Data.RAW_CONTACT_ID, id);// id  
        values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);// 内容类型  
        values.put(StructuredName.GIVEN_NAME, mE_name.getText().toString());// 添加姓名  
        resolver.update(Contacts.CONTENT_URI, values, "_ID = '"+id+"'", null);
  
        // 往data表入电话数据  
        values.clear();// 清空values中的数据  
        values.put(Data.RAW_CONTACT_ID, id);  
        values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);  
        values.put(Phone.NUMBER, mE_phone.getText().toString());  
        values.put(Phone.TYPE, Phone.TYPE_MOBILE);  
        resolver.update(Contacts.CONTENT_URI , values, "_ID = '"+id+"'", null); 
        // 往data表入Email数据  
        values.clear();  
        values.put(Data.RAW_CONTACT_ID, id);  
        values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);  
        values.put(Email.DATA, mE_email.getText().toString());  
        values.put(Email.TYPE, Email.TYPE_WORK);  
        resolver.update(Contacts.CONTENT_URI , values, "_ID = '"+id+"'", null);  
	}
}
