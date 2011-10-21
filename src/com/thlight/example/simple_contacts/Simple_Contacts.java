package com.thlight.example.simple_contacts;

import java.util.ArrayList;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;


import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Contacts.Data;
import android.provider.ContactsContract.RawContacts;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.content.ContentUris;
import android.net.Uri;
import android.database.Cursor;


public class Simple_Contacts extends Activity 
{
	private String TAG = "Contacts";
	private ListView mListview = null;
	ArrayList<People> mlist = new ArrayList();
	private String[] mNameArray = null;
	private Button mBtnAddContact = null;
	
	private static final int PICK_CONTACT = 3;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mListview = (ListView)findViewById(R.id.listView1);
        mBtnAddContact = (Button)findViewById(R.id.b_simple_contacts_add);
        Cursor cursor= getContentResolver().query(Contacts.CONTENT_URI, null, null, null, null); 
        while (cursor.moveToNext()) 
        { 
        	   People people = new People();
	    	   String contactId = cursor.getString(cursor.getColumnIndex( 
	    	   ContactsContract.Contacts._ID)); 
//	    	   Log.i(TAG,contactId);
	    	   people.setID(contactId);
	    	   String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)); 
	    	   //    	   Log.i(TAG,hasPhone);
	    	   
	    	   String name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)); 
	//    	   Log.i(TAG,name);
	    	   people.setName(name);
	    	   if (hasPhone.equals("1")) 
	    	   { 
	    		  people.setHasPhone(true);
	    	      // You know it has a number so now query it like this
	    	      Cursor phones = getContentResolver().query( ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId, null, null); 
	    	      while (phones.moveToNext()) 
	    	      { 
	    	         String phoneNumber = phones.getString(phones.getColumnIndex( ContactsContract.CommonDataKinds.Phone.NUMBER));                 
	    	         people.setCell(phoneNumber);
	    	         Log.i(TAG,phoneNumber);
	    	      } 
	    	      
	    	      phones.close(); 
	    	      Cursor emails = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI,null,ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + contactId,null, null);

	    	      while (emails.moveToNext())
	    	      {
	    	      String emailAddress = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));
	    	      people.setEmail(emailAddress);
	    	      }
	    	      mlist.add(people);
	    	   }
	    	   else
	    		   people.setHasPhone(false);
	    	   
	    	  
        }
        mNameArray = new String [mlist.size()];
        for(int i=0; i<mlist.size(); i++)
        	mNameArray[i] = mlist.get(i).getName();
        mListview.setAdapter(new ArrayAdapter<String>
		          (this,android.R.layout.simple_list_item_1, mNameArray));
        
        mListview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long itemId) {
            
                   Intent intent = new Intent();
                   Bundle bundle = new Bundle();
                   Log.i(TAG,mlist.get(position).getID());
                   bundle.putString("id", mlist.get(position).getID());
                   bundle.putString("name", mlist.get(position).getName());
                   bundle.putString("tel", mlist.get(position).getTel());
                   bundle.putString("cell", mlist.get(position).getCell());
                   bundle.putString("email", mlist.get(position).getEmail());
                   intent.putExtras(bundle);
                   intent.setClass(Simple_Contacts.this, DetailView.class);
                   startActivity(intent);
                    
                }
                
                
        });
        mBtnAddContact.setOnClickListener(new Button.OnClickListener()
    	{
            public void onClick(View v)
            {
            	try {
        			Intent intent = new Intent();
        			intent.setClass(Simple_Contacts.this, AddContact.class);
        			startActivity(intent);
        		} catch (Throwable e) {
        			// TODO Auto-generated catch block
        			e.printStackTrace();
        		}
          	}        	
    	});
        

    }


}