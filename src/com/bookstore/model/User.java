package com.bookstore.model;

import java.io.Serializable;

public class User  implements Serializable {
	private String userid;
	private String password;
	private String firstName;
	private String lastName;
	private int age;
	
	public User(String userid,String password, String firstName,String lastName,int age) {
		this.userid = userid;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder("\n**********************\n");
		sb.append("Userid:").append(userid).append("\n");
		sb.append("firstName:").append(firstName).append("\n");
		sb.append("lastName:").append(lastName).append("\n");
		sb.append("age:").append(age).append("\n");
		sb.append("\n**********************\n");
		return sb.toString();
	}
	
	
}
