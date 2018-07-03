package com.newx.muv.dao;

import com.newx.muv.entity.bo.Apk;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by xuzhijian on 2018/6/26 0026.
 */
public interface ApkMapper {

    int deleteByPrimaryKey(Integer id) throws DataAccessException;

    int insert(Apk record) throws DataAccessException;

    Apk selectApkByPK(Integer id) throws DataAccessException;

    int updateByPrimaryKey(Apk record) throws DataAccessException;

    List<Apk> selectApkList() throws DataAccessException;

    Apk queryLatestApk(String packageName) throws DataAccessException;
}
