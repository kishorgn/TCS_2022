package com.tcs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tcs.entity.Student;
import com.tcs.exception.InvalidStudentIdException;
import com.tcs.service.StudentService;

@Controller
public class StudentController {
	StudentService studentService;
	@Autowired
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	@RequestMapping(value = "student", method = RequestMethod.GET)
	public ModelAndView getStudents() {
		ModelAndView modelAndView = new ModelAndView("student");
		List<Student> students = studentService.findAll();
		modelAndView.addObject("students", students);
		return modelAndView;
	}
	@RequestMapping(value = "newStudent", method = RequestMethod.GET)
	public ModelAndView newStudent() {
		ModelAndView modelAndView = new ModelAndView("newStudent");
		modelAndView.addObject("student",new Student());
		return modelAndView;
	}
	@RequestMapping(value = "student", method = RequestMethod.POST)
	public ModelAndView saveStudent(@ModelAttribute Student student) {
		student = studentService.save(student);
		ModelAndView modelAndView = new ModelAndView("redirect:student");
		modelAndView.addObject("msg","Successfully saved with id : "+student.getId());
		return modelAndView;
	}
	
	@RequestMapping("editStudent/{sid}")
	public ModelAndView editStudent(@PathVariable String sid) throws InvalidStudentIdException {
		int id = Integer.parseInt(sid);
		ModelAndView modelAndView = new ModelAndView("editStudent");
		Student student = studentService.findById(id);
		modelAndView.addObject("student", student);
		return modelAndView;
	}
	
	@RequestMapping(value = "updateStudent", method = RequestMethod.POST)
	public ModelAndView updateStudent(@ModelAttribute Student student) throws InvalidStudentIdException {
		System.out.println("c");
		System.out.println("Student to be updated : "+student);
		studentService.update(student);
		ModelAndView modelAndView = new ModelAndView("redirect:student");
		modelAndView.addObject("msg", "Updated Successfully");
		System.out.println("d");
		return modelAndView;
		
	}
	
}
