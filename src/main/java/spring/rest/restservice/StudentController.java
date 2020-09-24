package spring.rest.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private final StudentRepository studentRepository;

    public StudentController(StudentRepository repository){
        this.studentRepository = repository;
    }

    @GetMapping("/student")
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @GetMapping("/student/{id}")
    public Student getOneStudent(@PathVariable Long id) {
        return studentRepository.findById(id) .orElseThrow(() -> new StudentNotFoundException(id));
    }

    @PostMapping("/student")
    public Student addNewStudent(@RequestBody Student newStudent){
        return studentRepository.save(newStudent);
    }

    @PutMapping("/student/{id}")
    public Student replaceStudent(@RequestBody Student newStudent, @PathVariable Long id) {
        return studentRepository.findById(id).map( student -> {
            student.setId(newStudent.getId());
            student.setFirst_name(newStudent.getFirst_name());
            student.setLast_name(newStudent.getLast_name());
            student.setPhone(newStudent.getPhone());
            return studentRepository.save(student);
        })
                .orElseGet( () -> {
                    newStudent.setId(id);
                    return studentRepository.save(newStudent);
                });

    }

    @DeleteMapping("/student/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentRepository.deleteById(id);
    }

}
