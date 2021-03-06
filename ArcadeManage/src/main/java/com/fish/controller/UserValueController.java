package com.fish.controller;

import com.fish.dao.second.model.UserAllInfo;
import com.fish.protocols.GetParameter;
import com.fish.protocols.GetResult;
import com.fish.protocols.PostResult;
import com.fish.service.UserValueService;
import com.fish.utils.BaseConfig;
import com.fish.utils.ReadJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/manage")
public class UserValueController {

    @Autowired
    UserValueService userValueService;
    @Autowired
    BaseConfig baseConfig;
    //查询展示所有产品信息
    @ResponseBody
    @GetMapping(value = "/uservalue")
    public GetResult getUserValue(GetParameter parameter) {
        return userValueService.findAll(parameter);
    }

    //新增产品信息
    @ResponseBody
    @PostMapping(value = "/uservalue/new")
    public PostResult insertUserValue(@RequestBody UserAllInfo productInfo) {
        PostResult result = new PostResult();
        int count = userValueService.insert(productInfo);
        if (count == 1) {
            String res = ReadJsonUtil.flushTable("user_value", baseConfig.getFlushCache());
            result.setCode(200);
            result.setMsg("操作成功"+res);

            return result;
        } else {
            result.setCode(404);
            result.setMsg("操作失败，请联系管理员");
            return result;
        }
    }

    //修改产品信息
    @ResponseBody
    @PostMapping(value = "/uservalue/edit")
    public PostResult modifyUserValue(@RequestBody UserAllInfo productInfo) {
        PostResult result = new PostResult();
        int count = userValueService.updateByPrimaryKeySelective(productInfo);
        if (count != 0) {
            String res = ReadJsonUtil.flushTable("user_value", baseConfig.getFlushCache());
            result.setCode(200);
            result.setMsg("操作成功"+res);
            return result;
        } else {
            result.setCode(404);
            result.setMsg("操作失败，请联系管理员");
            return result;
        }

    }

}
