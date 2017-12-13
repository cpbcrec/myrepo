package com.sapient.app.student.report.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapient.app.student.report.jpa.domain.model.Student;
import com.sapient.app.student.report.service.StudentReportService;
import com.sapient.student.report.pojo.Students;


@Controller
public class StudentReportController 
{
	@Autowired
	private StudentReportService studentReportService;
	
	@GetMapping("/")
    public String home1() {
        return "/index";
    }
	
	@GetMapping("/login")
    public String login() {
        return "/login";
    }
	
	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String home() 
	{
        return "greeting";
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public void process(@RequestBody Students students)
	{
		studentReportService.saveRecord(students);	
		
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public @ResponseBody Student get(@PathVariable("id") Integer id) {
        return studentReportService.findById(id);
    }

    @RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
    public String getReport(@PathVariable("id") Integer id) {
        return studentReportService.getReport(id);
    }
	
}
