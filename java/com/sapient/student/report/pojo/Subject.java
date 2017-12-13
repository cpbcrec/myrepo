package com.sapient.student.report.pojo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "subject")
public class Subject implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2349214941266119013L;
	
	public Subject() { }

    public Subject(String name, Long marks) {
        this.name = name;
        this.marks = marks;
    }

    private Integer id;

    private String name;

    private Long marks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "marks")
    public Long getMarks() {
        return marks;
    }

    public void setMarks(Long marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
	
}
