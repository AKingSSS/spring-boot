package com.aking.learn.controller;

import com.aking.learn.domain.Student;
import com.aking.learn.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *
 * </p>
 *
 * @author yk
 * @date 2021-01-03
 */
@RestController
@RequestMapping("/student")
@Slf4j
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 获取学生信息
     * @return
     */
    @GetMapping("/queryStudentInfo")
    public Student queryStudentInfo() {
        return studentService.queryStudentInfo();
    }

    /**
     * 新增学生信息
     * @return
     */
    @PostMapping("/addStudent")
    public Boolean addStudent(@RequestBody Student student) {

        return studentService.addStudent(student);
    }


}
