package com.buba.service;

import com.buba.pojo.Students;

import java.util.List;

/**
 * 学生管理service接口
 */
public interface StudentService {

    /**
     * 根据id查询学生信息
     * @param id
     * @return
     */
    Students getStudentById(String id);

    /**
     * 根据id删除学生信息
     * @param id
     */
    void removeStudentById(String id);

    /**
     * 根据id修改学生信息
     * @param students
     */
    void updateStudentById(Students students);

    /**
     * 查询学生列表
     * @return
     */
    List<Students> listStudent();

    /**
     * 添加学生信息
     * @param students
     */
    void insertStudent(Students students);
}
