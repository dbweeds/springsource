package com.company.student;

public class StudentInfo {
	private Student student;

	// 초기화 => 생성자, setter
	public void setStudent(Student student) {
		this.student = student;
	}

	public StudentInfo(Student student) {
		super();
		this.student = student;
	}

	public void getStudentInfo() {
		if (student != null) {
			System.out.println("이름 : " + student.getName());
			System.out.println("나이 : " + student.getAge());
			System.out.println("학년 : " + student.getGradeName());
			System.out.println("반 : " + student.getClassNum());
		}
	}

}
