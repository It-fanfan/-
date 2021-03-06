package com.fish.controller;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import com.fish.dao.primary.model.GameDayInfo;
import com.fish.protocols.GetParameter;
import com.fish.protocols.GetResult;
import com.fish.protocols.PostResult;
import com.fish.service.GameDayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/manage")
public class FormatConfigController {

    @Autowired
    GameDayInfoService gameDayInfoService;

    //查询展示所有赛制配置
    @ResponseBody
    @GetMapping(value = "/gameday")
    public GetResult getGameDayIfon(GetParameter parameter) {
        return gameDayInfoService.findAll(parameter);
    }

    //新增赛制配置信息
    @ResponseBody
    @PostMapping(value = "/gameday/new")
    public PostResult insertGameDayIfon(@RequestBody GameDayInfo productInfo) {
        PostResult result = new PostResult();

        int count = gameDayInfoService.insert(productInfo);
        //int count =1;
        if (count == 1) {
//            JSONObject paramMap = new JSONObject();
//            paramMap.put("name","rounds");
//            String res= HttpUtil.post("https://sgame.qinyougames.com/persieService/flush/logic", paramMap.toJSONString());
//            System.out.println("我是res返回值 : "+res);
            result.setCode(200);
            result.setMsg("操作成功");
            return result;
        } else {
            result.setCode(404);
            result.setMsg("操作失败，请联系管理员");
            return result;
        }
    }

    //修改赛制配置
    @ResponseBody
    @PostMapping(value = "/gameday/edit")
    public PostResult modifyGameDayIfon(@RequestBody GameDayInfo productInfo) {
        PostResult result = new PostResult();
       // int count =1;
       int count = gameDayInfoService.updateByPrimaryKeySelective(productInfo);
        if (count != 0) {
//            JSONObject paramMap = new JSONObject();
//            paramMap.put("name","rounds");
//            String res= HttpUtil.post("https://sgame.qinyougames.com/persieService/flush/logic", paramMap.toJSONString());
//            System.out.println("我是res返回值 : "+res);
            result.setCode(200);
            result.setMsg("操作成功");
            return result;
        } else {
            result.setCode(404);
            result.setMsg("操作失败，请联系管理员");
            return result;
        }

    }

}
