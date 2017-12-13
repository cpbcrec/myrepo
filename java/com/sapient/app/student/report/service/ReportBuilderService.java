package com.sapient.app.student.report.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sapient.app.student.report.jpa.domain.model.Student;

@Service
public class ReportBuilderService 
{
	public void writeToReport(Student student)
	{
		File theDir = new File("report");
		if (!theDir.exists()) {
			theDir.mkdir();
		}

		try 
		{
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(new File("report/" + student.getId() + ".json"), student);
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage()); 
		}
	}

	public void removeAllFiles() 
	{
		File theDir = new File("report");
		if (theDir.isDirectory() && theDir.exists()) 
		{
			String[]entries = theDir.list();
			for (String s: entries) 
			{
				File currentFile = new File(theDir.getPath(), s);
				currentFile.delete();
			}
		}
	}

	public String fetchReportById(Integer id) 
	{
		try 
		{
			return new String(Files.readAllBytes(Paths.get("report/" + id + ".json")));
		} 
		catch (IOException e) 
		{
			return "NOT_AVAILABLE";
		}
	}

}
