package com.example.service;

import java.util.List;

import com.example.model.Student;

public interface StudentService {
	
	public List<Student> getAllstudent();
	public Student getStudentById(int id);
	public Student createStudent(Student st);
	public void delStudByID(int id);
	public Student updateStud(Student st,int id);

}
