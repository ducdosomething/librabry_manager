package org.example.student_manager.model;

import java.sql.SQLException;
import java.util.List;

public interface IStudentDAO {
    public List<Student> showAllStudents();
    public Student searchStudentById(int id);
    public void addNewStudent(Student student) throws SQLException;
    public boolean updateStudent(Student student) throws SQLException;
    public boolean deleteStudent(int id) throws SQLException;
    public Classroom searchByClassId(int id);
    List<Classroom> showAllClass();
}
