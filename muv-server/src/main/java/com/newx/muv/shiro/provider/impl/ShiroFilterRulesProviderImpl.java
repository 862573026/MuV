package com.newx.muv.shiro.provider.impl;

import com.newx.muv.dao.ResourceMapper;
import com.newx.muv.shiro.provider.ShiroFilterRulesProvider;
import com.newx.muv.shiro.rule.RolePermRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/* *
 * @Author newx
 * @Description 
 * @Date 16:46 2018/3/7
 */
@Service("ShiroFilterRulesProvider")
public class ShiroFilterRulesProviderImpl implements ShiroFilterRulesProvider {

    @Autowired
    private ResourceMapper mResourceMapper;

    @Override
    public List<RolePermRule> loadRolePermRules() {

        return mResourceMapper.selectRoleRules();
    }

}
