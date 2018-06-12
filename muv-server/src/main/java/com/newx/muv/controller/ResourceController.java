package com.newx.muv.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newx.muv.common.RespCode;
import com.newx.muv.entity.bo.Resource;
import com.newx.muv.entity.vo.MenuTreeNode;
import com.newx.muv.entity.vo.Message;
import com.newx.muv.service.ResourceService;
import com.newx.muv.util.TreeUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/* *
 * @Author newx
 * @Description 资源URL管理
 * @Date 21:36 2018/3/17
 */
@RestController
@RequestMapping("/resource")
public class ResourceController extends BasicAction {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    @ApiOperation(value = "获取用户被授权菜单",notes = "通过uid获取对应用户被授权的菜单列表,获取完整菜单树形结构")
    @GetMapping("authorityMenu")
    public Message getAuthorityMenu(HttpServletRequest request) {
//        String uid = request.getParameter("uid");
        String uid = request.getHeader("appId");
        List<MenuTreeNode> treeNodes = new ArrayList<>();
        List<Resource> resources = resourceService.getAuthorityMenusByUid(uid);

        for (Resource resource : resources) {
            MenuTreeNode node = new MenuTreeNode();
            BeanUtils.copyProperties(resource,node);
            treeNodes.add(node);
        }
        List<MenuTreeNode> menuTreeNodes = TreeUtil.buildTreeBy2Loop(treeNodes,-1);
        return new Message().ok(RespCode.OK,"return menu list success").addData("menuTree",menuTreeNodes);
    }

    @ApiOperation(value = "获取全部菜单列", httpMethod = "GET")
    @GetMapping("menus")
    public Message getMenus() {

        List<MenuTreeNode> treeNodes = new ArrayList<>();
        List<Resource> resources = resourceService.getMenus();

        for (Resource resource: resources) {
            MenuTreeNode node = new MenuTreeNode();
            BeanUtils.copyProperties(resource,node);
            treeNodes.add(node);
        }
        List<MenuTreeNode> menuTreeNodes = TreeUtil.buildTreeBy2Loop(treeNodes,-1);
        return new Message().ok(RespCode.OK,"return menus success").addData("menuTree",menuTreeNodes);
    }

    @ApiOperation(value = "增加菜单",httpMethod = "POST")
    @PostMapping("menu")
    public Message addMenu(@RequestBody Resource menu ) {

        LOGGER.info(menu.toString());
        Boolean flag = resourceService.addMenu(menu);
        if (flag) {
            return new Message().ok(RespCode.OK,"add menu success");
        } else {
            return new Message().error(RespCode.ERROR,"add menu fail");
        }
    }

    @ApiOperation(value = "修改菜单",httpMethod = "PUT")
    @PutMapping("menu")
    public Message updateMenu(@RequestBody Resource menu) {
        LOGGER.info(menu.toString());

        Boolean flag = resourceService.modifyMenu(menu);
        if (flag) {
            return new Message().ok(RespCode.OK,"update menu success");
        } else {
            return new Message().error(RespCode.ERROR, "update menu fail");
        }
    }

    @ApiOperation(value = "删除菜单", notes = "根据菜单ID删除菜单", httpMethod = "DELETE")
    @DeleteMapping("menu/{menuId}")
    public Message deleteMenuByMenuId(@PathVariable Integer menuId) {

        LOGGER.info(menuId.toString());

        Boolean flag = resourceService.deleteMenuByMenuId(menuId);
        if (flag) {
            return new Message().ok(RespCode.OK, "delete menu success");
        } else {
            return new Message().error(RespCode.ERROR, "delete menu fail");
        }
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取API list", notes = "需要分页,根据teamId判断,-1->获取api分类,0->获取全部api,id->获取对应分类id下的api",httpMethod = "GET")
    @GetMapping("api/{teamId}/{currentPage}/{pageSize}")
    public Message getApiList(@PathVariable Integer teamId, @PathVariable Integer currentPage, @PathVariable Integer pageSize) {

        List<Resource> resources = null;
        if (teamId == -1) {
            // -1 为获取api分类
            resources = resourceService.getApiTeamList();
            return new Message().ok(RespCode.OK,"return apis success").addData("data",resources);
        }
        PageHelper.startPage(currentPage, pageSize);
        if (teamId == 0) {
            // 0 为获取全部api
            resources = resourceService.getApiList();
        } else {
            // 其他查询teamId 对应分类下的apis
            resources = resourceService.getApiListByTeamId(teamId);
        }
        PageInfo pageInfo = new PageInfo(resources);
        return new Message().ok(RespCode.OK,"return apis success").addData("data",pageInfo);
    }

    @ApiOperation(value = "增加API",httpMethod = "POST")
    @PostMapping("api")
    public Message addApi(@RequestBody Resource api ) {

        LOGGER.info(api.toString());
        Boolean flag = resourceService.addMenu(api);
        if (flag) {
            return new Message().ok(RespCode.OK,"add api success");
        } else {
            return new Message().error(RespCode.ERROR,"add api fail");
        }
    }

    @ApiOperation(value = "修改API",httpMethod = "PUT")
    @PutMapping("api")
    public Message updateApi(@RequestBody Resource api) {
        LOGGER.info(api.toString());

        Boolean flag = resourceService.modifyMenu(api);
        if (flag) {
            return new Message().ok(RespCode.OK,"update api success");
        } else {
            return new Message().error(RespCode.ERROR, "update api fail");
        }
    }

    @ApiOperation(value = "删除API", notes = "根据API_ID删除API", httpMethod = "DELETE")
    @DeleteMapping("api/{apiId}")
    public Message deleteApiByApiId(@PathVariable Integer apiId) {

        LOGGER.info(apiId.toString());

        Boolean flag = resourceService.deleteMenuByMenuId(apiId);
        if (flag) {
            return new Message().ok(RespCode.OK, "delete api success");
        } else {
            return new Message().error(RespCode.ERROR, "delete api fail");
        }
    }

}
