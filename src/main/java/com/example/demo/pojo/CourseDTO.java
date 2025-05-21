package com.example.demo.pojo;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CourseDTO {

    Long id;
    /** 课程名称 */
    private String courseName;
    /** 适用专业ID */
    private Long majorId;
    /** 适用年级 */
    private Integer grade;
    /** 课程性质(1-必修,2-选修) */
    private Integer courseType;
    /** 学分 */
    private BigDecimal credit;
    /** 是否公开(0-否,1-是) */
    private Integer isPublic;
    /** 课程状态(1-已提交,2-审核通过,3-审核不通过,4-公开,5-隐藏) */
    private Integer status;

    public CourseDTO(String courseName, Long majorId, Integer grade, Integer courseType, BigDecimal credit, Integer isPublic, Integer status){
        if(isPublic == 0) return;
        this.courseName = courseName;
        this.majorId = majorId;
        this.grade = grade;
        this.courseType = courseType;
        this.credit = credit;
        this.isPublic = isPublic;
        this.status = status;
    }

    public CourseDTO(Long id, String courseName, Long majorId, Integer grade, Integer courseType, BigDecimal credit, Integer isPublic, Integer status){
        if(isPublic == 0) return;
        this.id = id;
        this.courseName = courseName;
        this.majorId = majorId;
        this.grade = grade;
        this.courseType = courseType;
        this.credit = credit;
        this.isPublic = isPublic;
        this.status = status;
    }
}
