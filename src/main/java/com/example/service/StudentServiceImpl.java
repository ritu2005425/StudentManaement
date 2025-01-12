package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.example.EXCEPTION.AgeNotAllowedException;
import com.example.EXCEPTION.capacityExceededException;
import com.example.model.Student;
import com.example.repo.StudentRepo;
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepo studentRepo;
	

    @Autowired
    private KafkaTemplate<String,Object> kafkaTemplate;
    
    

	@Override
	public List<Student> getAllstudent() {
		if(studentRepo.findAll().size()>10)
		{
			throw new capacityExceededException("large number of data presemnt");
		}
		return studentRepo.findAll();
	}

	@Override
	public Student getStudentById(int id) {
	Student st=studentRepo.findById(id).orElse(null);
	
		 if(st.getAge()<20)
		 {
			 throw new AgeNotAllowedException("Age is less");
		 }
		return st;
	}

	@Override
	public Student createStudent(Student st) {

        //send booking complete msg in kafka topic with name 'bookingtopic'
  //      kafkaTemplate.send("studettopic","partation1",st.getName()+" with markys "+st.getMarks()+ "% has been added");
   //     kafkaTemplate.send("studettopic","partation1",st);
		return studentRepo.save(st);
	}

	@Override
	public void delStudByID(int id) {
		studentRepo.deleteById(id);
	}

	@Override
	public Student updateStud(Student st, int id) {
	Student student=studentRepo.findById(id).orElseThrow(()->new RuntimeException("USER NOT FOUND"));
	student.setSId(st.getSId());
	student.setAge(st.getAge());
	student.setName(st.getName());
	student.setMarks(st.getMarks());
	return studentRepo.save(student);
	}

}
