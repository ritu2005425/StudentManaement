package com.example.utility;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.example.model.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
	
	private String message;
	private T data;

}
