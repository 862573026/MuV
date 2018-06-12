package com.newx.muv.service.impl;

import com.newx.muv.dao.ResourceMapper;
import com.newx.muv.entity.bo.Resource;
import com.newx.muv.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/* *
 * @Author newx
 * @Description 
 * @Date 10:59 2018/3/26
 */
@Service("ResourceService")
public class ResourceServiceImpl implements ResourceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceServiceImpl.class);

    @Autowired
    private ResourceMapper mResourceMapper;

    @Override
    public List<Resource> getAuthorityMenusByUid(String appId) throws DataAccessException {
        return mResourceMapper.selectAuthorityMenusByUid(appId);
    }

    @Override
    public List<Resource> getMenus() throws DataAccessException {
        return mResourceMapper.selectMenus();
    }

    @Override
    public Boolean addMenu(Resource menu) throws DataAccessException {
        int num = mResourceMapper.insertSelective(menu);
        return num == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean modifyMenu(Resource menu) throws DataAccessException {
        int num = mResourceMapper.updateByPrimaryKeySelective(menu);
        return num == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public Boolean deleteMenuByMenuId(Integer menuId) throws DataAccessException {
        int num = mResourceMapper.deleteByPrimaryKey(menuId);
        return num == 1 ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    public List<Resource> getApiTeamList() throws DataAccessException {
        return mResourceMapper.selectApiTeamList();
    }

    @Override
    public List<Resource> getApiList() throws DataAccessException {
        return mResourceMapper.selectApiList();
    }

    @Override
    public List<Resource> getApiListByTeamId(Integer teamId) throws DataAccessException {
        return mResourceMapper.selectApiListByTeamId(teamId);
    }

    @Override
    public List<Resource> getAuthorityApisByRoleId(Integer roleId) throws DataAccessException {
        return mResourceMapper.selectApisByRoleId(roleId);
    }

    @Override
    public List<Resource> getAuthorityMenusByRoleId(Integer roleId) throws DataAccessException {
        return mResourceMapper.selectMenusByRoleId(roleId);
    }

    @Override
    public List<Resource> getNotAuthorityApisByRoleId(Integer roleId) throws DataAccessException {
        return mResourceMapper.selectNotAuthorityApisByRoleId(roleId);
    }

    @Override
    public List<Resource> getNotAuthorityMenusByRoleId(Integer roleId) throws DataAccessException {
        return mResourceMapper.selectNotAuthorityMenusByRoleId(roleId);
    }

}
