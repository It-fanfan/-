package com.fish.controller;

import com.fish.dao.primary.model.ArcadeGames;
import com.fish.protocols.GetParameter;
import com.fish.protocols.GetResult;
import com.fish.protocols.PostResult;
import com.fish.service.GamesService;
import com.fish.utils.BaseConfig;
import com.fish.utils.ReadJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/manage")
public class GameTableController
{

    @Autowired
    GamesService gamesService;
    @Autowired
    BaseConfig baseConfig;

    //查询游戏信息
    @ResponseBody
    @GetMapping(value = "/play")
    public GetResult getGames(GetParameter parameter)
    {
        return gamesService.findAll(parameter);
    }

    //新增游戏信息
    @ResponseBody
    @PostMapping(value = "/play/new")
    public PostResult insertGames(@RequestBody ArcadeGames productInfo)
    {
        PostResult result = new PostResult();
        int count = gamesService.insertGameInfo(productInfo);
        if (count == 1)
        {
            String res = ReadJsonUtil.flushTable("games", baseConfig.getFlushCache());
            result.setCode(200);
            result.setMsg("操作成功" + res);
            return result;
        } else
        {
            result.setCode(404);
            result.setMsg("操作失败，请联系管理员");
            return result;
        }
    }

    //修改游戏表信息
    @ResponseBody
    @PostMapping(value = "/play/edit")
    public PostResult modifyGames(@RequestBody ArcadeGames productInfo)
    {
        PostResult result = new PostResult();

        int count = gamesService.updateGameBySelective(productInfo);
        if (count != 0)
        {
            String res = ReadJsonUtil.flushTable("games", baseConfig.getFlushCache());
            result.setCode(200);
            result.setMsg("操作成功" + res);
            return result;
        } else
        {
            result.setCode(404);
            result.setMsg("操作失败，请联系管理员");
            return result;
        }

    }

}
