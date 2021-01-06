package com.aking.learn.service.impl;

import com.aking.learn.domain.Student;
import com.aking.learn.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-03
 */
@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    /**
     * 获取学生信息
     *
     * @return
     */
    @Override
    public Student queryStudentInfo() {
        return new Student().setName("aking").setBirth(new Date());
    }

    /**
     * 添加学生
     *
     * @param student
     * @return
     */
    @Override
    public Boolean addStudent(Student student) {
        log.info("student = " + student);
        if (student.getName() == null || student.getName() == "") {
            log.error("addStudent student is null");
        }
        return true;
    }
}
