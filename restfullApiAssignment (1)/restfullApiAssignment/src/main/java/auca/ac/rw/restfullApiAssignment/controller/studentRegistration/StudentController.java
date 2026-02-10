package auca.ac.rw.restfullApiAssignment.controller.studentRegistration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.restfullApiAssignment.model.studentRegistration.Student;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private List<Student> students = new ArrayList<>();

    public StudentController() {
        students.add(new Student(1L, "celine", "kami", "celine@example.com", "Computer Science", 3.8));
        students.add(new Student(2L, "cycy", "diana", "cycy@example.com", "Mathematics", 3.2));
        students.add(new Student(3L, "Charlie", "Brown", "charlie@example.com", "Physics", 3.5));
        students.add(new Student(4L, "kelly", "Irakoze", "kelly@example.com", "Computer Science", 3.9));
        students.add(new Student(5L, "soso", "Keza", "soso@example.com", "Chemistry", 2.9));
    }

    // GET all students
    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    
    // GET student by ID 
    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        return students.stream()
                .filter(s -> s.getStudentId().equals(studentId))
                .findFirst()
                .map(s -> new ResponseEntity<>(s, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // GET students by major
    @GetMapping("/major/{major}")
    public List<Student> getStudentsByMajor(@PathVariable String major) {
        return students.stream()
                .filter(s -> s.getMajor().equalsIgnoreCase(major))
                .collect(Collectors.toList());
    }

    // GET students by minimum GPA
    @GetMapping("/filter")
    public List<Student> getStudentsByGpa(@RequestParam Double gpa) {
        return students.stream()
                .filter(s -> s.getGpa() >= gpa)
                .collect(Collectors.toList());
    }

    // POST new student
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        students.add(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    // PUT update student

    @PutMapping("/{studentId}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long studentId, @RequestBody Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(studentId)) {
                students.set(i, updatedStudent);
                return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

