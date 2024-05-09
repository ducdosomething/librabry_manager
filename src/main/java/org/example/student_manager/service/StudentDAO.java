package org.example.student_manager.service;

import org.example.student_manager.model.Classroom;
import org.example.student_manager.model.IStudentDAO;
import org.example.student_manager.model.Student;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IStudentDAO {
    private static final String SELECT_ALL_STUDENTS = "select students.*, classrooms.name as classroom_name from students join classrooms on students.classroom_id = classrooms.id order by students.id;";
    private static final String INSERT_STUDENT_SQL = "insert into students (id, name, email, dob, address, phoneNumber, classroom_id) values (?, ?, ?, ?, ?, ?, ?);";
    private static final String UPDATE_STUDENT_SQL = "update students set id = ?, name = ?, email = ?, dob = ?, address = ?, phoneNumber = ?, classroom_id = ? where id = ?;";
    private static final String SELECT_CLASSROOM_BY_ID = "select * from classrooms where id = ?";
    private static final String DELETE_STUDENT_SQL = "delete from students where id = ?;";
    private static final String SELECT_STUDENT_BY_ID = "select students.*, classrooms.id as classroom_id, classrooms.name as classroom_name from students join classrooms on students.classroom_id = classrooms.id where students.id = ?;";

    ConnectionDAO cs = new ConnectionDAO();
    @Override
    public List<Student> showAllStudents() {
        List<Student> students = new ArrayList<>();

        try {
            Connection connection = cs.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(SELECT_ALL_STUDENTS);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                LocalDate dob = LocalDate.parse(rs.getString("dob"));
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");

                int classId = rs.getInt("classroom_id");
                String className = rs.getString("classroom_name");
                Classroom classroom = new Classroom(classId, className);

                Student student = new Student(id, name, email, dob, address, phoneNumber, classroom);
                students.add(student);
            }
        } catch (SQLException sqlException) {
            cs.printSQLException(sqlException);
        }

        return students;
    }

    @Override
    public Student searchStudentById(int id) {
        Student student = null;
        try (Connection connection = cs.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                LocalDate dob = LocalDate.parse(rs.getString("dob"));
                String address = rs.getString("address");
                String phoneNumber = rs.getString("phoneNumber");
                int classId = rs.getInt("classroom_id");
                String className = rs.getString("classroom_name");
                Classroom classroom = new Classroom(classId, className);

                student = new Student(id, name, email, dob, address, phoneNumber, classroom);
            }
        } catch (SQLException e) {
            cs.printSQLException(e);
        }
        return student;
    }

    @Override
    public void addNewStudent(Student student) throws SQLException {
        System.out.println(INSERT_STUDENT_SQL);

        try (Connection connection = cs.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENT_SQL)) {
            preparedStatement.setInt(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setDate(4, Date.valueOf(student.getDob()));
            preparedStatement.setString(5, student.getAddress());
            preparedStatement.setString(6, student.getPhoneNumber());
            preparedStatement.setInt(7, student.getClassroom().getClassId());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            cs.printSQLException(e);
        }
    }

    @Override
    public boolean updateStudent(Student student) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = cs.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_SQL);) {
            statement.setInt(1, student.getId());
            statement.setString(2, student.getName());
            statement.setString(3, student.getEmail());
            statement.setDate(4, Date.valueOf(student.getDob()));
            statement.setString(5, student.getAddress());
            statement.setString(6,student.getPhoneNumber());
            statement.setInt(7, student.getClassroom().getClassId());
            statement.setInt(8,student.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteStudent(int id) throws SQLException {
        boolean rowDelete;
        try (Connection connection = cs.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT_SQL);) {
            statement.setInt(1, id);
            rowDelete = statement.executeUpdate() >0;
        }
        return rowDelete;
    }

    @Override
    public Classroom searchByClassId(int id) {
        Classroom classroom = null;
        try (Connection connection = cs.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CLASSROOM_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int classId = Integer.parseInt(rs.getString("id"));
                String className = rs.getString("name");

                classroom = new Classroom(classId, className);
            }
        } catch (SQLException e) {
            cs.printSQLException(e);
        }
        return classroom;
    }

    @Override
    public List<Classroom> showAllClass() {
        List<Classroom> classrooms = new ArrayList<>();

        try {
            Connection connection = cs.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from classrooms");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");

                Classroom classroom = new Classroom(id,name);
                classrooms.add(classroom);
            }
        } catch (SQLException sqlException) {
            cs.printSQLException(sqlException);
        }

        return classrooms;
    }
}
