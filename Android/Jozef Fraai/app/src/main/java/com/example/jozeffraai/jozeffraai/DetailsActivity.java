package com.example.jozeffraai.jozeffraai;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


public class DetailsActivity extends ActionBarActivity {

	private DataSource datasource;
	private TextView textView;
	private Assignment assignment;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_details);

		datasource = new DataSource(this);
		long assignmentId = getIntent().getLongExtra(MainActivity.EXTRA_ASSIGNMENT_ID, -1);
		assignment = datasource.getAssignment(assignmentId);

		textView = (TextView) findViewById(R.id.details_textview);
		textView.setText(assignment.getAssignment());

	}
}
