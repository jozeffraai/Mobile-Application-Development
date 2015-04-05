package com.example.jozeffraai.jozeffraai;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends ActionBarActivity {

	private ListView listView;
	public static final String EXTRA_ASSIGNMENT_ID = "extraAssignmentId";
	private ArrayAdapter<Assignment> assignmentArrayAdapter;
	private DataSource datasource;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		listView = (ListView) findViewById(R.id.main_list);
		TextView emptyView = (TextView) findViewById(R.id.main_list_empty);
		listView.setEmptyView(emptyView);

		datasource = new DataSource(this);
		List<Assignment> assignments = datasource.getAllAssignments();
		assignmentArrayAdapter = new ArrayAdapter<Assignment>(this, android.R.layout.simple_list_item_1, assignments);
		listView.setAdapter(assignmentArrayAdapter);

		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
				intent.putExtra(EXTRA_ASSIGNMENT_ID,
						assignmentArrayAdapter.getItem(position).getId());
				startActivity(intent);
			}
		});

		//Register the ListView for a context menu
		registerForContextMenu(listView);

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
			if (resultCode == RESULT_OK) {
				long assignmentId = data.getLongExtra(EXTRA_ASSIGNMENT_ID, -1);
				if (assignmentId != -1) {
					Assignment assignment = datasource.getAssignment(assignmentId);
					assignmentArrayAdapter.add(assignment);
				}


			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.main_menu_add) {
			startActivityForResult(new Intent(this, AddAssignmentActivity.class), 1);
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
		//Inflate the context menu from the resource file
		getMenuInflater().inflate(R.menu.context_menu, menu);
		super.onCreateContextMenu(menu, view, menuInfo);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo itemInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
		if (item.getItemId() == R.id.context_menu_delete_item) {
			Assignment assignment = assignmentArrayAdapter.getItem(itemInfo.position);
			assignmentArrayAdapter.remove(assignment);
			datasource.deleteAssignment(assignment);

			return true;
		}
		return super.onContextItemSelected(item);
	}
}
