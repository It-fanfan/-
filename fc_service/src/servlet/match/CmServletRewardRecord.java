package servlet.match;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import db.CmDbSqlResource;
import db.PeDbGame;
import db.PeDbObject;
import db.PeDbRoundReceive;
import service.match.Ranking;
import servlet.CmServletMain;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

<<<<<<< HEAD
/**
 * @author xuwei
 */
@WebServlet(urlPatterns = "/reward/record")
public class CmServletRewardRecord extends CmServletMain {
=======
@WebServlet(urlPatterns = "/reward/record")
public class CmServletRewardRecord extends CmServletMain
{
>>>>>>> 8e4fccbbfce1955a84f9ef20f6bf84773b680aed
    /**
     * 需要子类实现的处理逻辑方法
     *
     * @param sqlResource    数据库资源句柄
     * @param requestObject  请求的对象
     * @param requestPackage 请求的包体
     * @return 响应的包体
     */
<<<<<<< HEAD
    @Override
    protected JSONObject handle(CmDbSqlResource sqlResource, HttpServletRequest requestObject, JSONObject requestPackage) {
        JSONObject result = new JSONObject();
        String ddUid = requestPackage.getString("uid");
        if (null == ddUid || "0".equals(ddUid)) {
=======
    protected JSONObject handle(CmDbSqlResource sqlResource, HttpServletRequest requestObject, JSONObject requestPackage)
    {
        JSONObject result = new JSONObject();
        String ddUid = requestPackage.getString("uid");
        if (null == ddUid || "0".equals(ddUid))
        {
>>>>>>> 8e4fccbbfce1955a84f9ef20f6bf84773b680aed
            result.put("result", "fail");
            result.put("msg", "user unknown");
            return result;
        }
        Set<String> matches = new HashSet<>();
        //获取奖励领取记录
        JSONArray record = addRewardRecord(ddUid, matches);
        result.put("record", record);

<<<<<<< HEAD
        if (!matches.isEmpty()) {
=======
        if (!matches.isEmpty())
        {
>>>>>>> 8e4fccbbfce1955a84f9ef20f6bf84773b680aed
            JSONObject matchData = new JSONObject();
            matches.forEach(matchKey ->
            {
                JSONObject matchInfo = Ranking.getMatchInfo(matchKey);
<<<<<<< HEAD
                if (matchInfo != null) {
=======
                if (matchInfo != null)
                {
>>>>>>> 8e4fccbbfce1955a84f9ef20f6bf84773b680aed
                    matchData.put(matchKey, matchInfo.getString("name"));
                }
            });
            result.put("matches", matchData);
        }
        result.put("result", "success");
        return result;
    }

    /**
     * 获取奖励记录
     *
     * @param ddUid 用户信息
     * @return 奖励列表
     */
<<<<<<< HEAD
    private JSONArray addRewardRecord(String ddUid, Set<String> matches) {
        Vector<PeDbObject> list = PeDbRoundReceive.getDbRecord(ddUid);
        JSONArray array = new JSONArray();
        if (list != null) {
=======
    private JSONArray addRewardRecord(String ddUid, Set<String> matches)
    {
        Vector<PeDbObject> list = PeDbRoundReceive.getDbRecord(ddUid);
        JSONArray array = new JSONArray();
        if (list != null)
        {
>>>>>>> 8e4fccbbfce1955a84f9ef20f6bf84773b680aed
            SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
            list.forEach(temp ->
            {
                PeDbRoundReceive data = (PeDbRoundReceive) temp;
                JSONObject element = new JSONObject();
                PeDbGame games = PeDbGame.getGameFast(data.ddGCode);
                element.put("ranking", data.ddRanking);
                element.put("mark", data.ddMark);
                element.put("index", data.ddMIndex);
                element.put("code", data.ddMCode);
                element.put("isGroup", data.ddGroup);
                element.put("total", data.ddTotal);
                element.put("type", data.ddType);
                String matchKey = Ranking.getField(data.ddMCode, data.ddGroup, data.ddMIndex);
                element.put("matchKey", matchKey);
                element.put("times", format.format(data.ddMStart));
                element.put("start", data.ddMStart.getTime() / 1000);
                element.put("end", data.ddMEnd.getTime() / 1000);
<<<<<<< HEAD
                if (games != null) {
                    element.put("name", games.ddName);
                }
=======
                if (games != null)
                    element.put("name", games.ddName);
>>>>>>> 8e4fccbbfce1955a84f9ef20f6bf84773b680aed
                matches.add(matchKey);
                array.add(element);
            });
        }
        return array;
    }
}
