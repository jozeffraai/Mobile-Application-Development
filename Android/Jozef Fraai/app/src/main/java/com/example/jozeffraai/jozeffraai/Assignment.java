package com.example.jozeffraai.jozeffraai;

/**
 * Created by Jozef Fraai on 29-3-2015.
 */
public class Assignment {

	private long id;
	private String assignment;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAssignment() {
		return assignment;
	}

	public void setAssignment(String assignment) {
		this.assignment = assignment;
	}

	//Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return assignment;
	}

}
