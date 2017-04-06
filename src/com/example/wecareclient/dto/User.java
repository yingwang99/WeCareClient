package com.example.wecareclient.dto;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	private int id;
	private String username;
	private String password;
	private String email;  
	private String userGoal;
	private String gender;
	private Date dayOfBirth;
	private String country;
	private double height;
	private double weight;
	private double goalOfWeight;
	private double weeklyGoal;
	
	public User() {
		super();
	}
	public User(int id, String username, String password, String email, String userGoal, String gender, Date dayOfBirth,
			String country, double height, double weight, double goalOfWeight, double weeklyGoal) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.userGoal = userGoal;
		this.gender = gender;
		this.dayOfBirth = dayOfBirth;
		this.country = country;
		this.height = height;
		this.weight = weight;
		this.goalOfWeight = goalOfWeight;
		this.weeklyGoal = weeklyGoal;
	}


	public User(String username, String password, String email, String userGoal, String gender, Date dayOfBirth,
			String country, double height, double weight, double goalOfWeight, double weeklyGoal) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
		this.userGoal = userGoal;
		this.gender = gender;
		this.dayOfBirth = dayOfBirth;
		this.country = country;
		this.height = height;
		this.weight = weight;
		this.goalOfWeight = goalOfWeight;
		this.weeklyGoal = weeklyGoal;
	}
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserGoal() {
		return userGoal;
	}
	public void setUserGoal(String userGoal) {
		this.userGoal = userGoal;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDayOfBirth() {
		return dayOfBirth;
	}
	public void setDayOfBirth(Date dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getGoalOfWeight() {
		return goalOfWeight;
	}
	public void setGoalOfWeight(double goalOfWeight) {
		this.goalOfWeight = goalOfWeight;
	}
	public double getWeeklyGoal() {
		return weeklyGoal;
	}
	public void setWeeklyGoal(double weeklyGoal) {
		this.weeklyGoal = weeklyGoal;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", userGoal=" + userGoal
				+ ", gender=" + gender + ", dayOfBirth=" + dayOfBirth + ", country=" + country + ", height=" + height
				+ ", weight=" + weight + ", goalOfWeight=" + goalOfWeight + ", weeklyGoal=" + weeklyGoal + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((dayOfBirth == null) ? 0 : dayOfBirth.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		long temp;
		temp = Double.doubleToLongBits(goalOfWeight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((userGoal == null) ? 0 : userGoal.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		temp = Double.doubleToLongBits(weeklyGoal);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (dayOfBirth == null) {
			if (other.dayOfBirth != null)
				return false;
		} else if (!dayOfBirth.equals(other.dayOfBirth))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (Double.doubleToLongBits(goalOfWeight) != Double.doubleToLongBits(other.goalOfWeight))
			return false;
		if (Double.doubleToLongBits(height) != Double.doubleToLongBits(other.height))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userGoal == null) {
			if (other.userGoal != null)
				return false;
		} else if (!userGoal.equals(other.userGoal))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (Double.doubleToLongBits(weeklyGoal) != Double.doubleToLongBits(other.weeklyGoal))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}
	
	
}
