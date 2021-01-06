package com.aking.learn.service;

import com.aking.learn.domain.Student;

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
