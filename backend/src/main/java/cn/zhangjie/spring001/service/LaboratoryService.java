package cn.zhangjie.spring001.service;

import cn.zhangjie.spring001.entity.Laboratory;

import java.util.List;

public interface LaboratoryService {
    List<Laboratory> selectLaborytory(String name);

    void insertLaboratory(Laboratory laboratory);

    void updateLaboratory(Laboratory laboratory);

    void deleteLaboratory(Integer id);
}
