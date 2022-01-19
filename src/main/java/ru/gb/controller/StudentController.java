package ru.gb.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.dto.Student;
import ru.gb.service.StudentService;

@Controller
public class StudentController {

  private StudentService studentService;

  public StudentController(StudentService studentService) {
    this.studentService = studentService;
  }

  // MVC
  // http://localhost:8080/app/all GET
  @GetMapping("/all")
  public String getAllStudents(Model model) {
    model.addAttribute("studends", studentService.getAll());
    return "student_list";
  }

  // http://localhost:8080/app/info/3 GET
  @GetMapping("/info/{id}")
  public String getStudentInfo(@PathVariable Integer id, Model model) {
    model.addAttribute("student", studentService.findById(id));
    return "student_info";
  }

  // http://localhost:8080/app/add GET
  @GetMapping("/add")
  public String getStudentAddFrom() {
    return "student_form";
  }

  // http://localhost:8080/app/add POST
  @PostMapping("/add")
  public String saveStudent(@RequestParam Integer id,
                            @RequestParam String name,
                            @RequestParam Float score) {
    studentService.save(new Student(id, name, score));
    return "redirect:all";
  }
}
