package com.newx.muv.dao;

import com.newx.muv.entity.bo.File;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
    /**
     * 通过主键获取一行数据
     * @return
     */
    File getById(Long id);

    /**
     * 插入一行数据
     * @param file
     * @return
     */
    int save(File file);

    /**
     * 更新一行数据
     * @param file
     * @return
     */
    int update(File file);

    /**
     * 删除一行数据
     * @param id
     * @return
     */
    int deleteById(Long id);

    int deleteByPath(String path);

    /**
     * 根据一个或多个属性获取File
     * @param file
     * @return
     */
    File getByFile(File file);
}
