package cn.zhangjuntao.spring001.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Laboratory {
    private Integer id;//实验室id
    private String name;//实验室名称
    private String organization;
    private String head;
    private String phone;
    private String totalValue;
    private String area;
    private Integer workStation;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
}
