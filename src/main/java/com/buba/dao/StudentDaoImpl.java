package com.buba.dao;

import com.buba.pojo.Students;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2022/8/10 15:02
 */
public class StudentDaoImpl implements StudentDao{

    /**
     * 根据id查询学生信息
     * @param connection
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public Students getStudentById(Connection connection, String id) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        Students students = new Students();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from students where id = ?");

            Object[] params = {id};
            System.out.println("sql ----> " + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
            while(rs.next()){
                students.setId(rs.getInt("id"));
                students.setStudentCode(rs.getString("studentCode"));
                students.setStudentName(rs.getString("studentName"));
                students.setAge(rs.getLong("age"));
                students.setSex(rs.getString("sex"));
                students.setPhone(rs.getString("phone"));
            }
            // 释放资源
            BaseDao.closeResource(null, pstm, rs);
        }
        return students;
    }

    /**
     * 根据id修改学生信息
     * @param connection
     * @param students
     * @return
     * @throws Exception
     */
    @Override
    public int updateStudentById(Connection connection, Students students) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int num = 0;
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("update students set ");
            sql.append("studentCode = ?, ");
            sql.append("studentName = ?, ");
            sql.append("age = ?, ");
            sql.append("sex = ?, ");
            sql.append("phone = ? ");
            sql.append("where id = ? ");

            Object[] params = {
                    students.getStudentCode(),
                    students.getStudentName(),
                    students.getAge(),
                    students.getSex(),
                    students.getPhone(),
                    students.getId()};
            System.out.println("sql ----> " + sql.toString());
            num = BaseDao.execute(connection, pstm, sql.toString(), params);
            // 释放资源
            BaseDao.closeResource(null, pstm, rs);
        }
        return num;
    }

    /**
     * 根据id删除学生信息
     * @param connection
     * @param id
     * @return
     * @throws Exception
     */
    @Override
    public int removeStudentById(Connection connection, String id) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int num = 0;
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("delete from students where id = ?");

            Object[] params = {id};
            System.out.println("sql ----> " + sql.toString());
            num = BaseDao.execute(connection, pstm, sql.toString(), params);
            // 释放资源
            BaseDao.closeResource(null, pstm, rs);
        }
        return num;
    }

    /**
     * 查询学生列表
     * @param connection
     * @return
     */
    @Override
    public List<Students> listStudent(Connection connection) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        List<Students> studentsList = new ArrayList<Students>();
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("select * from students");

            Object[] params = {};
            System.out.println("sql ----> " + sql.toString());
            rs = BaseDao.execute(connection, pstm, rs, sql.toString(), params);
            while(rs.next()){
                Students students = new Students();
                students.setId(rs.getInt("id"));
                students.setStudentCode(rs.getString("studentCode"));
                students.setStudentName(rs.getString("studentName"));
                students.setAge(rs.getLong("age"));
                students.setSex(rs.getString("sex"));
                students.setPhone(rs.getString("phone"));

                studentsList.add(students);
            }
            // 释放资源
            BaseDao.closeResource(null, pstm, rs);
        }
        return studentsList;
    }

    /**
     * 添加学生信息
     * @param connection
     * @param students
     * @return
     * @throws Exception
     */
    @Override
    public int insertStudent(Connection connection, Students students) throws Exception {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        int num = 0;
        if(connection != null){
            StringBuffer sql = new StringBuffer();
            sql.append("insert into students( ");
            sql.append("studentCode, ");
            sql.append("studentName, ");
            sql.append("age, ");
            sql.append("sex, ");
            sql.append("phone) ");
            sql.append("values(?,?,?,?,?) ");

            Object[] params = {
                    students.getStudentCode(),
                    students.getStudentName(),
                    students.getAge(),
                    students.getSex(),
                    students.getPhone()};
            System.out.println("sql ----> " + sql.toString());
            num = BaseDao.execute(connection, pstm, sql.toString(), params);
            // 释放资源
            BaseDao.closeResource(null, pstm, rs);
        }
        return num;
    }
}
