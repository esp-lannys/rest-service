package spring.rest.restservice;

public class StudentNotFoundException extends RuntimeException{
    StudentNotFoundException(Long id) {
        super("ID not found : " + id);
    }
}
