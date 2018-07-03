package com.newx.muv.service;

import com.newx.muv.entity.bo.Apk;
import org.springframework.dao.DataAccessException;

import java.util.List;

/**
 * Created by xuzhijian on 2018/6/26 0026.
 */
public interface ApkService {

    Apk selectApkByPK(Integer id) throws DataAccessException;

    boolean deleteById(Integer id) throws DataAccessException;

    boolean insert(Apk record) throws DataAccessException;

    boolean update(Apk record) throws DataAccessException;

    List<Apk> getApkList() throws DataAccessException;

    Apk queryLatestApk(String packageName) throws DataAccessException;
}
