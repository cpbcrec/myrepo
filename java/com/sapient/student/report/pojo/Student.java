package com.sapient.student.report.pojo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.sapient.app.student.report.util.Result;


@XmlRootElement(name = "Student")
public class Student implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1317839392845402254L;
	
	public Student() { }

    public Student(Integer id, String name, String className, Subjects subjects) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.subjects = subjects;
    }

    private Integer id;

    private String name;

    private String className;

    private Subjects subjects;

    private Long rank;

    private Long total;

    private Result result;

    private boolean isProcessed;

    @XmlElement(name = "id")
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

    @XmlElement(name = "className")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @XmlElement(name = "subjects")
    public Subjects getSubjects() {
        return subjects;
    }

    public void setSubjects(Subjects subjects) {
        this.subjects = subjects;
    }

    @XmlTransient
    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    @XmlTransient
    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    @XmlTransient
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public boolean isProcessed() {
        return isProcessed;
    }

    public void setProcessed(boolean processed) {
        isProcessed = processed;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", className='" + className + '\'' +
                ", subjects=" + subjects +
                ", rank=" + rank +
                ", total=" + total +
                ", result=" + result +
                ", isProcessed=" + isProcessed +
                '}';
    }
	
}
