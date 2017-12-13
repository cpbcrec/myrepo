package com.sapient.app.student.report.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.sapient.app.student.report.repository.StudentRepository;
import com.sapient.app.student.report.util.Result;
import com.sapient.student.report.pojo.Student;
import com.sapient.student.report.pojo.Students;
import com.sapient.student.report.pojo.Subject;

@Service
public class StudentReportService 
{
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
    private ThreadPoolTaskScheduler taskScheduler;
	
	@Autowired
	private ReportBuilderService reportBuilder;

    @PostConstruct
    void init() 
    {
        taskScheduler.scheduleAtFixedRate(task, 5000 * 5);
    }
    
    public com.sapient.app.student.report.jpa.domain.model.Student findById(Integer id) {
        return studentRepository.findOne(id);
    }
    
    public List<com.sapient.app.student.report.jpa.domain.model.Student> findAllReport()
    {
    	return studentRepository.findAll(); 
    }

    /**
     * Returns null if file not present, else return file contents as string
     *
     * @param studentId
     * @return file content as String
     */
    public String getReport(Integer studentId) {
        return reportBuilder.fetchReportById(studentId);
    }
    
    @Transactional
    public void recordCleaner() 
    {
        studentRepository.deleteAll();
        reportBuilder.removeAllFiles();
    }

    @Transactional
	public void saveRecord(Students students) 
	{
    	recordCleaner();
		List<Student> list = students.getStudents(); 

		for (Student obj : list)
		{
			List<com.sapient.app.student.report.jpa.domain.model.Subject> listOfSub = new ArrayList<>();

			com.sapient.app.student.report.jpa.domain.model.Student stud = new com.sapient.app.student.report.jpa.domain.model.Student();
			stud.setId(obj.getId());
			stud.setClassName(obj.getClassName());
			stud.setName(obj.getName()); 
			for (Subject ob : obj.getSubjects().getSubject())
			{
				com.sapient.app.student.report.jpa.domain.model.Subject sub = new com.sapient.app.student.report.jpa.domain.model.Subject();
				sub.setName(ob.getName());
				sub.setMarks(ob.getMarks()); 
				listOfSub.add(sub);
			}
			stud.setSubjects(listOfSub);
			studentRepository.save(stud);

		}
	}
	Runnable task = () -> {
        Long processedCount = studentRepository.countAllProcessed();
        Long totalCount = studentRepository.count();

        if (totalCount <= 0) 
        {
            return;
        }
        
        if (processedCount.equals(totalCount) && totalCount > 0) {
            List<com.sapient.app.student.report.jpa.domain.model.Student> students = studentRepository.findAll();
            Collections.sort(students, (stud1, stud2) -> {
                return stud2.getTotal().compareTo(stud1.getTotal());
            });

            IntStream.range(0, students.size()).forEach(id -> {
                if (students.get(id).getResult().equals(Result.PASS)) {
                    students.get(id).setRank(Long.valueOf(id + 1));
                } 
                else 
                {
                    students.get(id).setRank(Long.valueOf(-1));
                }
            });

            List<com.sapient.app.student.report.jpa.domain.model.Student> updatedStudents = studentRepository.save(students);
            studentRepository.flush();
            updatedStudents.forEach(student -> reportBuilder.writeToReport(student));
        }

        List<com.sapient.app.student.report.jpa.domain.model.Student> unProcessedStudents = studentRepository.findAllNotProcessed();

        if (unProcessedStudents.isEmpty()) { return; }
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        unProcessedStudents.stream().forEach(student -> executorService.submit(new StudentReportWorker(student, studentRepository)));
        
	};
}
