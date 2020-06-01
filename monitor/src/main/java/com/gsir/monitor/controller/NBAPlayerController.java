package com.gsir.monitor.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gsir.monitor.base.message.enums.ResponseCode;
import com.gsir.monitor.base.message.response.ResponseResult;
import com.gsir.monitor.base.utils.BeanUtils;
import com.gsir.monitor.base.utils.StringUtils;
import com.gsir.monitor.common.utils.Requests;
import com.gsir.monitor.entities.ElasticSearchEntity;
import com.gsir.monitor.entities.NBAPlayer;
import com.gsir.monitor.service.BaseElasticSearchService;
import com.gsir.monitor.service.NBAPlayerService;
import com.gsir.monitor.utils.ElasticSearchUtil;
import com.gsir.monitor.vo.IdxVo;
import com.gsir.monitor.vo.QueryVo;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/nbaplayer")
public class NBAPlayerController {
    @Autowired
    private NBAPlayerService nbaPlayerService;

    @Autowired
    private BaseElasticSearchService baseElasticSearchService;

    @RequestMapping("/list")
    public List<NBAPlayer> list() {
        return nbaPlayerService.getAll();
    }

    @GetMapping(value =  "/search")
    public ResponseResult search(@RequestBody QueryVo queryVo) {
        ResponseResult response = new ResponseResult();
        if(!StringUtils.isNotEmpty(queryVo.getIdxName())){
            response.setCode(ResponseCode.PARAM_ERROR_CODE.getCode());
            response.setMsg("索引为空，不允许提交");
            response.setStatus(false);
            log.warn("索引为空");
            return response;
        }

        try {
            Class<?> clazz = NBAPlayer.class;
            Map<String,Object> params = queryVo.getQuery().get("match");
            Set<String> keys = params.keySet();
            MatchQueryBuilder queryBuilders = null;
            for(String key : keys){
                queryBuilders = QueryBuilders.matchQuery(key, params.get(key));
            }
            if(null != queryBuilders){
                SearchSourceBuilder searchSourceBuilder = ElasticSearchUtil.initSearchSourceBuilder(queryBuilders);
                List<?> data = baseElasticSearchService.search(queryVo.getIdxName(), searchSourceBuilder, clazz);
                response.setData(data);
            }
        } catch (Exception e) {
            response.setCode(ResponseCode.ERROR.getCode());
            response.setMsg("服务忙，请稍后再试");
            response.setStatus(false);
            log.error("查询数据异常，metadataVo={},异常信息={}", queryVo.toString(),e.getMessage());
        }

        return response;
    }

    @RequestMapping("/importAllFromDB")
    public ResponseResult importAllFromDB() {
        ResponseResult response = new ResponseResult();
        List<NBAPlayer> players = nbaPlayerService.getAll();
        List<ElasticSearchEntity> playerEntities = new LinkedList<ElasticSearchEntity>();
        for (NBAPlayer player : players) {
            ElasticSearchEntity esEntity = new ElasticSearchEntity();
            esEntity.setId(player.getId().toString());
            esEntity.setData(BeanUtils.transBeanToMap(player));
            playerEntities.add(esEntity);
        }
        baseElasticSearchService.insertBatch("nba", playerEntities);
        response.setMsg("insert success");
        return response;
    }

    @RequestMapping("/importall")
    public String importALL() {

        JSONObject jsonObj = Requests.getJsonObj("https://china.nba.com/static/data/league/playerlist.json", null, null);
        JSONObject payloadObj = (JSONObject) jsonObj.get("payload");
        JSONArray jsonArray = payloadObj.getJSONArray("players");
        for (Object jsonObject : jsonArray) {
            JSONObject playerObj = (JSONObject) jsonObject;
            NBAPlayer player = playerObj.getObject("playerProfile", NBAPlayer.class);
            JSONObject teamProfile = (JSONObject) playerObj.get("teamProfile");
            /*反射三种方式
            1. Class<?> clazz1 = null;clazz1 = Class.forName("XXX");
            2. Class<?> clazz2 = null;clazz2 = new Person().getClass();
            3. Class<?> clazz3 = null;clazz3 = Person.class
            */
            Class<?> playerClass = player.getClass();
            for (String key : teamProfile.keySet()) {
                String methodName = "setTeam" + key.substring(0, 1).toUpperCase() + key.substring(1);
                try {
                    Method method = playerClass.getMethod(methodName, String.class);
                    method.invoke(player, teamProfile.get(key));
                } catch (Exception e1) {
                    try {
                        Method method = playerClass.getMethod(methodName, Boolean.class);
                        method.invoke(player, teamProfile.get(key));
                    } catch (Exception e2) {

                    }
                }

            }
            nbaPlayerService.save(player);
        }
        return "";
    }

    @PostMapping(value = "/createIndex")
    public ResponseResult createIndex(@RequestBody IdxVo idxVo) {
        ResponseResult response = new ResponseResult();
        try {
            if (!baseElasticSearchService.isExistsIndex(idxVo.getIdxName())) {
                String idxSql = JSON.toJSONString(idxVo.getIdxSql());
                log.warn(" idxName={}, idxSql={}",idxVo.getIdxName(), idxSql);
                baseElasticSearchService.createIndex(idxVo.getIdxName(), idxSql);
            } else {
                response.setStatus(false);
                response.setCode(ResponseCode.DUPLICATEKEY_ERROR_CODE.getCode());
                response.setMsg("索引已经存在，不允许创建");
            }
        } catch (Exception e) {
            response.setStatus(false);
            response.setCode(ResponseCode.ERROR.getCode());
            response.setMsg(ResponseCode.ERROR.getMsg());
        }

        return response;
    }

    /**
     * @Description 判断索引是否存在；存在-TRUE，否则-FALSE
     * @param index
     * @return xyz.wongs.weathertop.base.message.response.ResponseResult
     * @throws
     * @date 2019/11/19 18:48
     */
    @GetMapping(value = "/exist/{index}")
    public ResponseResult indexExist(@PathVariable(value = "index") String index){

        ResponseResult response = new ResponseResult();
        try {
            if(!baseElasticSearchService.isExistsIndex(index)){
                log.error("index={},不存在", index);
                response.setCode(ResponseCode.RESOURCE_NOT_EXIST.getCode());
                response.setMsg(ResponseCode.RESOURCE_NOT_EXIST.getMsg());
            } else {
                response.setMsg(" 索引已经存在, " + index);
            }
        } catch (Exception e) {
            response.setCode(ResponseCode.NETWORK_ERROR.getCode());
            response.setMsg(" 调用ElasticSearch 失败！");
            response.setStatus(false);
        }
        return response;
    }

    @GetMapping(value = "/del/{index}")
    public ResponseResult indexDel(@PathVariable(value = "index") String index){
        ResponseResult response = new ResponseResult();
        try {
            baseElasticSearchService.deleteIndex(index);
        } catch (Exception e) {
            response.setCode(ResponseCode.NETWORK_ERROR.getCode());
            response.setMsg(" 调用ElasticSearch 失败！");
            response.setStatus(false);
        }
        return response;
    }

}
