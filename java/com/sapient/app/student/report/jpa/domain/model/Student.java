package com.sapient.app.student.report.jpa.domain.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sapient.app.student.report.util.Result;

@Entity
@Table(name = "student") 
public class Student implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Student() 
	{ 
		
	}

    public Student(Integer id, String name, String className, List<Subject> subjects) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.subjects = subjects;
    }

    @Id
    private Integer id;

    private String name;

    @Column(name = "class_name")
    private String className;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,  orphanRemoval = true)
    @JoinColumn(name = "student_id")
    private List<Subject> subjects;

    private Long rank;

    private Long total;

    @Enumerated(EnumType.STRING)
    private Result result;

    @Column(name = "isProcessed", columnDefinition = "tinyint(1) default 0")
    private boolean isProcessed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public Long getRank() {
        return rank;
    }

    public void setRank(Long rank) {
        this.rank = rank;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

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
