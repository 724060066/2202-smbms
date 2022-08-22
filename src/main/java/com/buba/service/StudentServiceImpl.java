package com.buba.service;

import com.buba.dao.BaseDao;
import com.buba.dao.StudentDao;
import com.buba.dao.StudentDaoImpl;
import com.buba.pojo.Students;

import java.sql.Connection;
import java.util.List;

/**
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/10 14:42
 */
public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao = new StudentDaoImpl();

    /**
     * 根据id查询学生信息
     * @param id
     * @return
     */
    @Override
    public Students getStudentById(String id) {
        // TODO Auto-generated method stub
        Connection connection = null;
        Students students = null;
        try {
            // 取得数据库链接
            connection = BaseDao.getConnection();
            students = studentDao.getStudentById(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭数据库链接
            BaseDao.closeResource(connection, null, null);
        }
        return students;
    }

    /**
     * 根据id删除学生信息
     * @param id
     */
    @Override
    public void removeStudentById(String id) {
        // TODO Auto-generated method stub
        Connection connection = null;
        try {
            // 取得数据库链接
            connection = BaseDao.getConnection();
            studentDao.removeStudentById(connection, id);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭数据库链接
            BaseDao.closeResource(connection, null, null);
        }
    }

    /**
     * 根据id修改学生信息
     * @param students
     */
    @Override
    public void updateStudentById(Students students) {
        // TODO Auto-generated method stub
        Connection connection = null;
        try {
            // 取得数据库链接
            connection = BaseDao.getConnection();
            studentDao.updateStudentById(connection, students);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭数据库链接
            BaseDao.closeResource(connection, null, null);
        }
    }

    /**
     * 查询学生列表
     * @return
     */
    @Override
    public List<Students> listStudent() {
        // TODO Auto-generated method stub
        Connection connection = null;
        List<Students> studentList = null;
        try {
            // 取得数据库链接
            connection = BaseDao.getConnection();
            studentList = studentDao.listStudent(connection);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭数据库链接
            BaseDao.closeResource(connection, null, null);
        }
        return studentList;
    }

    /**
     * 添加学生信息
     *
     * @param students
     */
    public void insertStudent(Students students) {
        // TODO Auto-generated method stub
        Connection connection = null;
        try {
            // 取得数据库链接
            connection = BaseDao.getConnection();
            studentDao.insertStudent(connection, students);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 关闭数据库链接
            BaseDao.closeResource(connection, null, null);
        }
    }
}
