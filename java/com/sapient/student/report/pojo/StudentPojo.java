package com.sapient.student.report.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Students")
public class StudentPojo 
{
	private Students students;

	@XmlElement(name = "Student")
    public Students getStudents ()
    {
        return students;
    }

    public void setStudents (Students Students)
    {
        this.students = Students;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Students = "+students+"]";
    }
}
