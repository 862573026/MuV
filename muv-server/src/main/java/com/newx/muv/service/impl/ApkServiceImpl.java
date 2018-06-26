package com.newx.muv.service.impl;

import com.newx.muv.dao.ApkMapper;
import com.newx.muv.entity.bo.Apk;
import com.newx.muv.service.ApkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xuzhijian on 2018/6/26 0026.
 */
@Service("ApkService")
public class ApkServiceImpl implements ApkService {

    @Autowired
    private ApkMapper mApkMapper;

    @Override
    public boolean deleteById(Integer id) throws DataAccessException {
        int result = mApkMapper.deleteByPrimaryKey(id);
        return result == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public boolean insert(Apk record) throws DataAccessException {
        int result = mApkMapper.insert(record);
        return result == 1 ? Boolean.TRUE : Boolean.FALSE;
    }


    @Override
    public boolean update(Apk record) throws DataAccessException {
        int result = mApkMapper.updateByPrimaryKey(record);
        return result == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public List<Apk> getApkList() throws DataAccessException {
        return mApkMapper.selectApkList();
    }
}
