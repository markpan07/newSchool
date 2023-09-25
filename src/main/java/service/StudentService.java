package service;

import exception.StudentNotFoundException;
import model.Student;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class StudentService {
    private HashMap<Long, Student> students = new HashMap<Long, Student>();
    private long count = 0;

    public Student create(Student student) {
        student.setId(++count);
        students.put(student.getId(), student);
        return student;
    }

    public Student update(long id, Student student){
        if(students.containsKey(id)) {
            Student oldStudent = students.get(id);
            oldStudent.setAge(student.getAge());
            oldStudent.setName(student.getName());
            students.replace(id, oldStudent);
            return oldStudent;
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    public Student delete(long id) {
        if(students.containsKey(id)) {
            return students.remove(id);
        } else {
            throw new StudentNotFoundException(id);
        }
    }

    public Student get(long id) {
        if(students.containsKey(id)) {
            return students.get(id);
        } else {
            throw new StudentNotFoundException(id);
        }
    }

}
