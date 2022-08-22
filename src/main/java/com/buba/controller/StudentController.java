package com.buba.controller;

import com.buba.pojo.Students;
import com.buba.service.StudentService;
import com.buba.service.StudentServiceImpl;
import com.buba.tool.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/17 09:20
 */
@Controller
// @RequestMapping的value属性设置访问controller的url
@RequestMapping("/student")
public class StudentController {

    /**
     * 查看学生列表
     *
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String listStudent(Model model) {
        StudentService studentService = new StudentServiceImpl();
        List<Students> studentsList = studentService.listStudent();
//        request.setAttribute("studentsList", studentsList);
        model.addAttribute("studentsList", studentsList);
        // /jsp/studentlist.jsp
        return "studentlist";
    }

    /**
     * 根据id查询学生信息1
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/view")
    public String getStudentById(Model model, String id, Students students) {
        StudentService studentService = new StudentServiceImpl();
        Students student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "viewstudent";
    }

    /**
     * 添加学生信息
     *
     * @return
     */
    @RequestMapping(value = "/insert")
    public String insertStudent(Students students) {
        StudentService studentService = new StudentServiceImpl();
        studentService.insertStudent(students);

        // 添加完成，将请求重定向到查询学生列表
        return "redirect:/student/list";
    }

    /**
     * 修改页面初始化
     *
     * @param id
     * @return
     */
    @RequestMapping("/viewForUpdate")
    public String getStudentForUpdate(Model model, String id) {
        StudentService studentService = new StudentServiceImpl();
        Students student = studentService.getStudentById(id);

        model.addAttribute("student", student);

        return "updatestudent";
    }

    /**
     * 根据id修改学生信息
     *
     * @param students
     * @return
     */
    @RequestMapping("/updateStudentById")
    public String updateStudentById(Students students) {
        StudentService studentService = new StudentServiceImpl();
        studentService.updateStudentById(students);

        return "redirect:/student/list";
    }

    /**
     * 根据id删除学生信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/remove")
    public String removeStudentById(String id) {
        StudentService studentService = new StudentServiceImpl();
        studentService.removeStudentById(id);
        return "redirect:/student/list";
    }
}
