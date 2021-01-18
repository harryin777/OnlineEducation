package com.yzz.service_acl.controller;

import com.alibaba.fastjson.JSONObject;
import com.yzz.service_acl.entity.Permission;
import com.yzz.service_acl.service.IndexService;
import com.yzz.service_acl.service.PermissionService;
import com.yzz.commonutils.vo.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Api(tags = "admin_acl服务")
@RestController
@RequestMapping("/admin/acl/index")
//@CrossOrigin
public class IndexController {

    @Resource
    private IndexService indexService;

    /**
     * 根据token获取用户信息
     */
    @ApiOperation("根据token获取用户信息")
    @GetMapping("info")
    public ResultData info(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Map<String, Object> userInfo = indexService.getUserInfo(username);
        return ResultData.success().data(userInfo);
    }

    /**
     * 获取菜单
     * @return
     */
    @ApiOperation("获取菜单")
    @GetMapping("menu")
    public ResultData getMenu(){
        //获取当前登录用户用户名
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<JSONObject> permissionList = indexService.getMenu(username);
        return ResultData.success().data("permissionList", permissionList);
    }
    
    @ApiOperation("登出")
    @PostMapping("logout")
    public ResultData logout(){
        return ResultData.success();
    }

}
