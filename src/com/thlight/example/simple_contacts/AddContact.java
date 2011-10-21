package com.thlight.example.simple_contacts;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts.Data;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends Activity 
{
	private Button mBtnAdd = null;
	private EditText mE_name = null;
	private EditText mE_phone = null;
	private EditText mE_email = null;
	private String mId = null;
	private boolean bFlagForUpdateBtn = false;
	public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contect);
        FindView();
        mId = getIntent().getExtras().getString("id");
        if(mId!=null)
        {
        	mBtnAdd.setText("update");
        	bFlagForUpdateBtn = true;
        	
        }
	        mBtnAdd.setOnClickListener(new Button.OnClickListener()
	    	{
	            public void onClick(View v)
	            {
	            	try {
	            		addContact();
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
	}
	public void addContact() throws Throwable {  
        // 先向raw_contacts表中添加一条空记录,因为表中主要是存储的联系人的id  
        // data表中主要是存储的联系人的信息,所以我们要先获得新增的联系人的id,id为自动增长 
		long id = 0;
        ContentResolver resolver = this.getContentResolver();  
        ContentValues values = new ContentValues();// 参数集合  
        Uri url = null;  
        if(bFlagForUpdateBtn)
        	 {
        		id = Integer.parseInt(mId);
        		
        	 }
        else
        	{
        		id = ContentUris.parseId(url); 
        		url = resolver.insert(RawContacts.CONTENT_URI, values);// 插入空记录
        	}
  
        // 往data表入姓名数据  
        values.clear();  
        values.put(Data.RAW_CONTACT_ID, id);// id  
        values.put(Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE);// 内容类型  
        values.put(StructuredName.GIVEN_NAME, mE_name.getText().toString());// 添加姓名 
        if(bFlagForUpdateBtn)
        	resolver.update(ContactsContract.Data.CONTENT_URI, values, null, null);
        else
        	resolver.insert(ContactsContract.Data.CONTENT_URI, values);  
  
        // 往data表入电话数据  
        values.clear();// 清空values中的数据  
        values.put(Data.RAW_CONTACT_ID, id);  
        values.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);  
        values.put(Phone.NUMBER, mE_phone.getText().toString());  
        values.put(Phone.TYPE, Phone.TYPE_MOBILE); 
        if(bFlagForUpdateBtn)
        	resolver.update(ContactsContract.Data.CONTENT_URI, values, null, null);
        else
        resolver.insert(android.provider.ContactsContract.Data.CONTENT_URI,  
                values);  
        // 往data表入Email数据  
        values.clear();  
        values.put(Data.RAW_CONTACT_ID, id);  
        values.put(Data.MIMETYPE, Email.CONTENT_ITEM_TYPE);  
        values.put(Email.DATA, mE_email.getText().toString());  
        values.put(Email.TYPE, Email.TYPE_WORK);  
        if(bFlagForUpdateBtn)
        	resolver.update(ContactsContract.Data.CONTENT_URI, values, null, null);
        else
        resolver.insert(android.provider.ContactsContract.Data.CONTENT_URI,  
                values);  
    }
}
