package com.aking.learn.service;

import com.aking.learn.pojo.Student;
import com.sun.org.apache.xpath.internal.operations.Bool;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-03
 */
public interface StudentService {
    /**
     * 获取学生信息
     * @return
     */
    Student queryStudentInfo();

    /**
     * 添加学生
     * @param student
     * @return
     */
    Boolean addStudent(Student student);
}
