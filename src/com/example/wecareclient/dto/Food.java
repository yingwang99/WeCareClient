


package com.example.wecareclient.dto;

import java.io.Serializable;

public class Food implements Serializable{
	private int _id;
	private String kind;
	private String categray;
	private int g;
	private double calorie;
	private String meal_type;
	private String add_time;
	
	public Food(){}
	
	public Food(String kind, String categray, int g, double calorie) {
		super();
		this.kind = kind;
		this.categray = categray;
		this.g = g;
		this.calorie = calorie;
	}
	
	public Food(int _id,String kind, String categray, int g, double calorie) {
		super();
		this._id =_id;
		this.kind = kind;
		this.categray = categray;
		this.g = g;
		this.calorie = calorie;
	}

	public Food(int _id, String kind, String categray, int g, double calorie, String add_time) {
		super();
		this._id = _id;
		this.kind = kind;
		this.categray = categray;
		this.g = g;
		this.calorie = calorie;
		this.add_time = add_time;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getCategray() {
		return categray;
	}

	public void setCategray(String categray) {
		this.categray = categray;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public double getCalorie() {
		return calorie;
	}

	public void setCalorie(double calorie) {
		this.calorie = calorie;
	}
	
	public String getMeal_type() {
		return meal_type;
	}

	public void setMeal_type(String meal_type) {
		this.meal_type = meal_type;
	}
	
	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + _id;
		long temp;
		temp = Double.doubleToLongBits(calorie);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((categray == null) ? 0 : categray.hashCode());
		temp = Double.doubleToLongBits(g);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((kind == null) ? 0 : kind.hashCode());
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
		Food other = (Food) obj;
		if (_id != other._id)
			return false;
		if (Double.doubleToLongBits(calorie) != Double.doubleToLongBits(other.calorie))
			return false;
		if (categray == null) {
			if (other.categray != null)
				return false;
		} else if (!categray.equals(other.categray))
			return false;
		if (Double.doubleToLongBits(g) != Double.doubleToLongBits(other.g))
			return false;
		if (kind == null) {
			if (other.kind != null)
				return false;
		} else if (!kind.equals(other.kind))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Food [_id=" + _id + ", kind=" + kind + ", categray=" + categray + ", g=" + g + ", calorie=" + calorie
				+ "]";
	}
	
}
