package cn.zhangjuntao.spring001.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Training {
    private Integer id;
    private String name;
    private String major;
    private Integer classHours;
    private String experimentType;
    private String courseSpecific;
    private Integer certificationMapping;
    private Integer competitionMapping;
    private String relatedTrainingResources;
    private String intro;
    private Integer laboratory_id;
    private String laboratory_name;
    private LocalDateTime create_time;
    private LocalDateTime update_time;

}
