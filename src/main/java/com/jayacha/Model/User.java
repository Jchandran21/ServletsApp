//$Id$
package com.jayacha.Model;

public class User {
	public static int numberOfUsers = 0;
	private String name;
	private int age;
	private String gender;
	private String username;
	private String password;
	public User(String name, int age, String gender, String username, String password) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.username = username;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public User() {
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
