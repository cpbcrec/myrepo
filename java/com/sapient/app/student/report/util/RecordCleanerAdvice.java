package com.sapient.app.student.report.util;

import javax.transaction.Transactional;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sapient.app.student.report.repository.StudentRepository;
import com.sapient.app.student.report.service.ReportBuilderService;

@Aspect
@Component
public class RecordCleanerAdvice 
{
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ReportBuilderService reportBuilder;

	
	@Before("execution(void com.sapient.app.student.report.service.StudentReportService.saveRecord(*))")
	@Transactional
	public void cleaner()
	{
		System.out.println("Inside before advice");
		studentRepository.deleteAll();
		reportBuilder.removeAllFiles();
	}
}
