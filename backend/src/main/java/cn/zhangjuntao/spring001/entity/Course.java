package cn.zhangjuntao.spring001.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private Integer id;
    private String name;
    private String number;
    private Integer experiment;
    private String intro;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private Integer pte_id;
    private String pte_name;
}
