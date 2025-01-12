package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;
import com.example.service.StudentService;
import com.example.utility.ApiResponse;

@RestController

@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200") 
public  class StudentController {
	@Autowired
	private StudentService studentService;
	
	@GetMapping("/getall")
	public ResponseEntity<List<Student>> getAllStudent()
	{
		return new ResponseEntity(studentService.getAllstudent(),HttpStatus.OK);	
	}
	
	@GetMapping("/getall/dto")
	public ResponseEntity<List<Student>> getAllStudentdto()
	{
		ApiResponse api=new ApiResponse();
////		api.setStatus(HttpStatus.OK);
//		api.setMessage("student retrived succsfullly");
//		api.setData(studentService.getAllstudent());
		return new ResponseEntity(new ApiResponse<>("student reteived",studentService.getAllstudent()),HttpStatus.OK);
	//	return new ResponseEntity(api,HttpStatus.OK);	
	}
	
	
	
	@GetMapping("/getbyid/{sId}")
	public ResponseEntity<Student> getStudeById(@PathVariable("sId") int sId)
	{
		return new ResponseEntity(studentService.getStudentById(sId),HttpStatus.OK);	
	}
	
	
	@GetMapping("/add/{a}/{b}")
	public int getRetreive(@PathVariable("a") int a,@PathVariable("b") int b,
			@RequestHeader("Operation") String Operation)
	{
		int c = 0;
		if(Operation.equalsIgnoreCase("add"))
		{
			c=a+b;
		}
		else
		{
			c=a-b;
		}
		return c;
	
	}
	
	@GetMapping("/addrequestparam")
	public int getRetreiveRequest(@RequestParam("a") int a,@RequestParam("b") int b,
			@RequestHeader("Operation") String Operation)
	{
		int c = 0;
		if(Operation.equalsIgnoreCase("add"))
		{
			c=a+b;
		}
		else
		{
			c=a-b;
		}
		return c;
	
	}
	
	
	@DeleteMapping("/delbyid/{sId}")
	public ResponseEntity<?> delStudeById(@PathVariable("sId") int sId)
	{
		studentService.delStudByID(sId);
		return new ResponseEntity(HttpStatus.OK);	
	}
	
	@PostMapping("/addstude")
	public ResponseEntity<Student> AddStudent(@RequestBody Student student)
	{
		return new ResponseEntity(studentService.createStudent(student),HttpStatus.CREATED);	
	}
	
	@PutMapping("/updatestud/{sId}")
	public ResponseEntity<Student> updateStudent(@PathVariable("sId") int sId,@RequestBody Student student)
	{
		return new ResponseEntity(studentService.updateStud(student,sId),HttpStatus.OK);	
		
	}
	
	

}
