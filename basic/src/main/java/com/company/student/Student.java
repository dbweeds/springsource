package com.company.student;

public class Student {
	private String name;
	private String age;
	private String gradeName;
	private String classNum;
	
	public Student(String name, String age, String gradeName, String classNum) {
		super();
		this.name = name;
		this.age = age;
		this.gradeName = gradeName;
		this.classNum = classNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	
	
}
