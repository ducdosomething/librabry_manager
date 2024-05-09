package org.example.student_manager.controller;

import org.example.student_manager.model.Classroom;
import org.example.student_manager.model.Student;
import org.example.student_manager.service.StudentDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "StudentController", urlPatterns = "/students")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 50, // 50MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class StudentController extends HttpServlet {
    private StudentDAO studentDAO;
    public void init() {
        studentDAO = new StudentDAO();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    insertStudent(req, resp);
                    break;
                case "edit":
                    updateStudent(req, resp);
                    break;
                case "delete":
                    deleteStudent(req,resp);
                    break;
                default:
                    listStudent(req,resp);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showInsertForm(req, resp);
                    break;
                case "edit":
                    showEditForm(req, resp);
                    break;
                case "delete":
                    showFormDelete(req, resp);
                    break;
                default:
                    listStudent(req, resp);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listStudent(HttpServletRequest req, HttpServletResponse resp)
        throws SQLException, IOException, ServletException {
        List<Student> listStudent = studentDAO.showAllStudents();
        req.setAttribute("listStudent", listStudent);
        RequestDispatcher dispatcher= req.getRequestDispatcher("/view/list.jsp");
        dispatcher.forward(req, resp);
    }

    private void insertStudent(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
                int id= Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                String email = req.getParameter("email");
                LocalDate dob = LocalDate.parse(req.getParameter("dob"));
                String address = req.getParameter("address");
                String phoneNumber = req.getParameter("phoneNumber");
                int classroom = Integer.parseInt(req.getParameter("classroom"));

                Classroom classroom1 = studentDAO.searchByClassId(classroom);
                Student student = new Student(id, name, email, dob, address, phoneNumber, classroom1);

                try {
                    studentDAO.addNewStudent(student);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

                List<Student> students = studentDAO.showAllStudents();
                req.setAttribute("listStudent", students);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/view/list.jsp");
                dispatcher.forward(req, resp);
            }

    private void showInsertForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/create.jsp");
        List<Classroom> classrooms = studentDAO.showAllClass();
        req.setAttribute("classrooms", classrooms);

        dispatcher.forward(req, resp);
    }

    private void updateStudent(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        String address = req.getParameter("address");
        String phoneNumber = req.getParameter("phoneNumber");

        int id_class = Integer.parseInt(req.getParameter("classroom"));

        Classroom classroom = studentDAO.searchByClassId(id_class);
        Student editStudent = new Student(id, name, email, dob, address, phoneNumber, classroom);
        studentDAO.updateStudent(editStudent);
        List<Student> listStudent = studentDAO.showAllStudents();

        req.setAttribute("listStudent", listStudent);

        RequestDispatcher dispatcher = req.getRequestDispatcher("view/list.jsp");
        dispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Student existingStudent = studentDAO.   searchStudentById(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/edit.jsp");
        req.setAttribute("students", existingStudent);

        List<Classroom> classrooms = studentDAO.showAllClass();
        req.setAttribute("classrooms", classrooms);
        dispatcher.forward(req, resp);

    }

    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, SQLException {
        int id = Integer.parseInt(req.getParameter("id"));
        studentDAO.deleteStudent(id);
        List<Student> students = studentDAO.showAllStudents();
        req.setAttribute("listStudent", students);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/list.jsp");
        dispatcher.forward(req, resp);
    }

    private void showFormDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentDAO.searchStudentById(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/delete.jsp");
        req.setAttribute("listdelete", student);
        dispatcher.forward(req, resp);
    }
}
