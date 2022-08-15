package dbtest.dbtestdto;

import java.sql.Date;

public class DBTestDTO {
	
	private String name;
	private int age;
	private double height;
	private Date logtime; 
	
	public DBTestDTO() {
		
	}
	public DBTestDTO(String name, int age, double height) {
		this.name = name;
		this.age = age;
		this.height = height;
	}
	public DBTestDTO(String name, int age, double height, Date logtime) {
		this.name = name;
		this.age = age;
		this.height = height;
		this.logtime = logtime;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public void setLogtime(Date logtime) {
		this.logtime = logtime;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
	public double getHeight() {
		return height;
	}
	public Date getLogtime() {
		return logtime;
	}
}
