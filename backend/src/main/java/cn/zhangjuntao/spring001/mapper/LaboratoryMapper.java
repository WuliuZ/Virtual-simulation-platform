package cn.zhangjuntao.spring001.mapper;

import cn.zhangjuntao.spring001.entity.Laboratory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface LaboratoryMapper {

    List<Laboratory> selectLaboratory(String name);

    void insertLaboratory(Laboratory laboratory);

    void updateLaboratory(Laboratory laboratory);
//@Param("laboratory")
    void deleteLaboratory(Integer id);
}
