package com.example.jozeffraai.jozeffraai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;


public class AddAssignmentActivity extends ActionBarActivity {

	private EditText assignmentEditText;
	private DataSource datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_assignment);

		datasource = new DataSource(this);
		assignmentEditText = (EditText) findViewById(R.id.add_assignment_assignment_edittext);

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_add_assignment, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.add_assignment_menu_save) {
			long assignmentId =
					datasource.createAssignment(assignmentEditText.getText().toString());
			Intent resultIntent = new Intent();
			resultIntent.putExtra(MainActivity.EXTRA_ASSIGNMENT_ID, assignmentId);
			setResult(Activity.RESULT_OK, resultIntent);
			finish();
			return true;

		}


		return super.onOptionsItemSelected(item);
	}
}
