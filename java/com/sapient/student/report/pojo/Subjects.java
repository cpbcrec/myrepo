package com.sapient.student.report.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "subjects")
public class Subjects 
{
	private List<Subject> subject;

	@XmlElement(name = "subject")
    public List<Subject> getSubject ()
    {
        return subject;
    }

    public void setSubject (List<Subject> subject)
    {
        this.subject = subject;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [subject = "+subject+"]";
    }
}
