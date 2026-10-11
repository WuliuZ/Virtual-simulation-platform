package cn.zhangjie.spring001.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimulationEquipment {
    private Integer id;
    private String name;
    private String number;
    private Integer type;
    private String soft_system;
    private String supplier;
    private Integer status;
    private String purpose;
    private Integer laboratory_id;
    private String laboratory_name;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
}


