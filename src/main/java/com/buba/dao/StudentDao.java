package com.buba.dao;

import com.buba.pojo.Students;

import java.sql.Connection;
import java.util.List;

public interface StudentDao {

    /**
     * 根据id查询学生信息
     * @param connection
     * @param id
     * @return
     * @throws Exception
     */
    Students getStudentById(Connection connection, String id) throws Exception;

    /**
     * 根据id修改学生信息
     * @param connection
     * @param students
     * @return
     * @throws Exception
     */
    int updateStudentById(Connection connection, Students students) throws Exception;

    /**
     * 根据id删除学生信息
     * @param connection
     * @param id
     * @return
     * @throws Exception
     */
    int removeStudentById(Connection connection, String id) throws Exception;

    /**
     * 查询学生列表
     * @param connection
     * @return
     */
    List<Students> listStudent(Connection connection) throws Exception;

    /**
     * 添加学生信息
     * @param connection
     * @param students
     * @return
     * @throws Exception
     */
    int insertStudent(Connection connection, Students students) throws Exception;
}
