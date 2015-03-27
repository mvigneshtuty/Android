package org.codehowpedia.databases;

import java.util.ArrayList;

import org.codehowpedia.databases.adapters.UserDetailsAdapter;
import org.codehowpedia.databases.fragments.InputFormFragment;
import org.codehowpedia.databases.domain.UserDetails;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DatabasesActivity extends Activity {
	String tag = "DatabaseActivity";
	private ArrayAdapter<String> arrAdapter;
	private UserDetailsAdapter userDetailAdapter;
	//private ArrayList<String> userDetailsList;
	//private UserDetails[] userDetailsList;
	private ArrayList<UserDetails> userDetailsList;
	private ListView listView;
	SQLiteAdapter dbAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.i(tag, "inflating database activity!");
		setContentView(R.layout.activity_databases);
		// adding fragments
		FragmentManager fManager = getFragmentManager();
		FragmentTransaction fTrans = fManager.beginTransaction();
		// creating objects for fragments
		InputFormFragment inputFormFrag = new InputFormFragment();
		// DisplayUserDetailsFragment dispDetailsFrag = new
		// DisplayUserDetailsFragment();
		// replacing the DatabaseActivity contents with fragments
		Log.i(tag, "preparing fragment inflation..");
		fTrans.replace(R.id.input_form, inputFormFrag);
		// fTrans.replace(R.id.display_details, dispDetailsFrag);
		Log.i(tag, "one fragment inflated..");
		// committing the fragment transactions
		fTrans.commit();
		dbAdapter = new SQLiteAdapter(this);
		dbAdapter.open();
		// dbAdapter.deleteContact(2);
		// dbAdapter.deleteContact(3);
		// dbAdapter.deleteContact(4);
		// dbAdapter.deleteContact(5);
		// dbAdapter.deleteContact(6);
		// dbAdapter.deleteContact(7);
		// dbAdapter.deleteContact(8);
		// dbAdapter.close();
		populateListView();
		// listView = (ListView) findViewById(R.id.lst_userDetails);
		// userDetailsList = new ArrayList<String>();
		// userDetailsList.add("Hello!");
		// userDetailsList.add("Vignesh!");
		// listView.setAdapter(new ArrayAdapter<String>(this,
		// android.R.layout.simple_list_item_1, userDetailsList));

	}

	public void saveDetails(View view) {
		EditText edtTxt_Email = (EditText) findViewById(R.id.edtxt_email);
		EditText edtTxt_userName = (EditText) findViewById(R.id.edtxt_userName);
		String emailId = edtTxt_Email.getText().toString().trim();
		String userName = edtTxt_userName.getText().toString().trim();
		String validationResult = getString(R.string.pass);
		if (!(emailId.length() > 0)) {
			validationResult = getString(R.string.fail);
			Toast.makeText(this, "Email id required", Toast.LENGTH_SHORT)
					.show();
		} else if (!(userName.length() > 0)) {
			validationResult = getString(R.string.fail);
			Toast.makeText(this, "User name required", Toast.LENGTH_SHORT)
					.show();
		}
		if (getString(R.string.pass).equals(validationResult)) {
			// Toast.makeText(this, "proceeding for saving details..",
			// Toast.LENGTH_SHORT).show();
			// creating SQLite adapter
			dbAdapter = new SQLiteAdapter(this);
			dbAdapter.open();
			// inserting user details
			long id = dbAdapter.insertContact(userName, emailId);
			Log.i(tag, "After 1st insert : id value : " + id + "");
			// fetching all existing user details
			// Cursor cursor = dbAdapter.getAllContacts();
			// if (cursor.moveToFirst()) {
			// do {
			// displayContacts(cursor);
			// } while (cursor.moveToNext());
			// }

			// // id = dbAdapter.insertContact("Spring framework",
			// // "mvc@spring.com");
			// // Log.i(tag, "After 2nd insert : id value : "+id+"");
			populateListView();
			Toast.makeText(this, R.string.save_successful, Toast.LENGTH_SHORT)
					.show();
			// dbAdapter.close();

		}
		// Toast.makeText(this, emailId.length() + " ; "+userName.length(),
		// Toast.LENGTH_SHORT).show();
		// edtTxtEmail.getT
		// Toast.makeText(this, R.string.save_successful, Toast.LENGTH_SHORT)
		// .show();
	}

	public void displayContacts(Cursor cursor) {
		UserDetails uDet = new UserDetails();
		uDet.setId(cursor.getString(0));
		uDet.setUserName(cursor.getString(1));
		uDet.setEmailId(cursor.getString(2));
		userDetailsList.add(uDet);
//		userDetailsList.add("Id : " + cursor.getString(0) + " \n " + "Name : "
//				+ cursor.getString(2) + " \n " + "Email : "
//				+ cursor.getString(1));
		// userDetailsList.add("Name : " + cursor.getString(2) + " ;  " +
		// "Email : " + cursor.getString(1));
		// Toast.makeText(this, "Id : " + cursor.getString(0) + " ; \n " +
		// "Name : " + cursor.getString(2) + " ; \n " + "Email : " +
		// cursor.getString(1), Toast.LENGTH_LONG).show();
	}

	public void populateListView() {
		Cursor cursor = dbAdapter.getAllContacts();
		//userDetailsList = new UserDetails[5];
		userDetailsList = new ArrayList<UserDetails>();
		if (cursor.moveToFirst()) {

			do {
				displayContacts(cursor);
			} while (cursor.moveToNext());
		}

		dbAdapter.close();
		listView = (ListView) findViewById(R.id.lst_userDetails);
		registerForContextMenu(listView);
		// userDetailsList = new ArrayList<String>();
//		arrAdapter = new ArrayAdapter<String>(getApplicationContext(),
//				android.R.layout.simple_list_item_1, userDetailsList);
		userDetailAdapter = new UserDetailsAdapter(this, R.layout.contents_displaydetails_listview, userDetailsList);
		listView.setAdapter(userDetailAdapter);
		TextView txtView = (TextView) findViewById(R.id.user_details);
		if(userDetailsList.isEmpty()){
			
			txtView.setText(R.string.no_details_avaliable);
		}
		else{
			txtView.setText(R.string.user_details);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.databases, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo ctxMenuInfo = (AdapterContextMenuInfo) item
				.getMenuInfo();
		switch (item.getItemId()) {
		case R.id.delete_user:
			 dbAdapter = new SQLiteAdapter(this);
			 dbAdapter.open();
		//	long selectedId = ((AdapterView.AdapterContextMenuInfo) ctxMenuInfo).id;
			int position = ((AdapterView.AdapterContextMenuInfo) ctxMenuInfo).position;
			UserDetails uDetObj = userDetailsList.get(position);
			String selectedUserId = uDetObj.getId();
			dbAdapter.deleteContact(Long.parseLong(selectedUserId));
			populateListView();
			Toast.makeText(
					this,
					"user details deleted, name : "+uDetObj.getUserName(), Toast.LENGTH_SHORT)
					.show();
			// dbAdapter.close();
//			Toast.makeText(
//					this,
//					"delete selected! for Id : " + selectedUserId
//							+ " ; email : " + uDetObj.getEmailId(), Toast.LENGTH_SHORT)
//					.show();
//			Toast.makeText(this, userDetailsList.get(position),
//					Toast.LENGTH_SHORT).show();
			return true;
		default:
			return super.onContextItemSelected(item);
		}

	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		// TODO Auto-generated method stub
		super.onCreateContextMenu(menu, v, menuInfo);
		if (v.getId() == R.id.lst_userDetails) {
			getMenuInflater().inflate(R.menu.userdetails_menu, menu);
		}
	}
}
