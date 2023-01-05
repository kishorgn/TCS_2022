package com.tcs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.entity.Student;
import com.tcs.exception.InvalidStudentIdException;
import com.tcs.repo.StudentRepo;

@Service
public class StudentService {
	StudentRepo studentRepo;
	@Autowired
	public void setStudentRepo(StudentRepo studentRepo) {
		this.studentRepo = studentRepo;
	}
	public List<Student> findAll(){
		return (List<Student>)studentRepo.findAll();
	}
	public Student findById(int id) throws InvalidStudentIdException {
		Optional<Student> optStudent = studentRepo.findById(id);
		if(!optStudent.isPresent()) {
			throw new InvalidStudentIdException("Invalid studentn id : "+id);
		}
		return optStudent.get();
	}
	public Student save(Student student) {
		return studentRepo.save(student);
	}
	public Student update(Student student) throws InvalidStudentIdException {
		findById(student.getId());
		return studentRepo.save(student);
	}
	public Student delete(int id) throws InvalidStudentIdException {
		Student student = findById(id);
		studentRepo.deleteById(id);
		return student;
	}
}
