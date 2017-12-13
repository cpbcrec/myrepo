package com.sapient.app.student.report.service;

import com.sapient.app.student.report.jpa.domain.model.Student;
import com.sapient.app.student.report.repository.StudentRepository;
import com.sapient.app.student.report.util.Result;

public class StudentReportWorker implements Runnable 
{
	private Student student;
	private StudentRepository studentRepository;

	public StudentReportWorker(Student student, StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
		this.student = student;
	}


	@Override
	public void run() 
	{
		Long total = student.getSubjects().stream().mapToLong(subject -> subject.getMarks()).sum();
		student.setTotal(total);

		if (student.getSubjects().stream().anyMatch(subject -> subject.getMarks() < 35)) 
		{
			student.setResult(Result.FAIL);
		} 
		else 
		{
			student.setResult(Result.PASS);
		}

		student.setProcessed(true);
		student = studentRepository.save(student);
	}

}
