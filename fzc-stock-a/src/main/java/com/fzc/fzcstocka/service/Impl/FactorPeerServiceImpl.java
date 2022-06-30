package com.fzc.fzcstocka.service.Impl;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.fzc.fzcstocka.model.PeerInfo;
import com.fzc.fzcstocka.service.FactorApiService;
import com.fzc.fzcstocka.service.FactorPeerService;
import com.fzc.fzcstocka.service.RedisService;
import com.fzc.fzcstocka.service.StockAInfoService;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * @author flamenco.xxx
 * @date 2022/3/15 15:20
 */
@Service
@Slf4j
public class FactorPeerServiceImpl implements FactorPeerService {

    @Autowired
    private StockAInfoService stockAInfoService;

    @Autowired
    private FactorApiService factorApiService;

    private static final String HTTP_127_0_0_1_8383 = "http://127.0.0.1:8383/";

    /**
     * 资本收益率
     */
    private static final String GET_ROC_PEER = "get_ROC_peer/";

    private static final String ROC_CN = "资本收益率";

    /**
     * 净资产收益率
     */
    private static final String GET_RONA_PEER = "get_RONA_peer/";

    private static final String RONA_CN = "净资产收益率";

    /**
     * 总资产收益率
     */
    private static final String GET_ROTA_PEER = "get_ROTA_peer/";

    private static final String ROTA_CN = "总资产收益率";

    /**
     * 毛利率
     */
    private static final String GET_GM_PEER = "get_GM_peer/";

    private static final String GM_CN = "毛利率";

    /**
     * 营业利润率
     */
    private static final String GET_OM_PEER = "get_OM_peer/";

    private static final String OM_CN = "营业利润率";

    /**
     * 净利润率
     */
    private static final String GET_NPM_PEER = "get_NPM_peer/";

    private static final String NPM_CN = "净利润率";

    /**
     * 获取全部参数,不再单独获取提高性能
     */
    private static final String GET_ALL_PEER = "get_ALL_peer/";


    @Autowired
    private RedisService redisService;

    public static final String STOCK_AINFO = "stockAInfo";
    @Value("${redis.database}")
    private String REDIS_DATABASE;

    private static Map<String, String> fieldMap = Maps.newHashMap();

    private static final Set<String> fieldSet = Sets.newHashSet(ROC_CN,RONA_CN,ROTA_CN,GM_CN,OM_CN,NPM_CN);

    @PostConstruct
    public void init(){
        fieldMap.put(ROC_CN,"roc");
        fieldMap.put(RONA_CN,"rona");
        fieldMap.put(ROTA_CN,"rota");
        fieldMap.put(GM_CN,"gm");
        fieldMap.put(OM_CN,"om");
        fieldMap.put(NPM_CN,"npm");

    }

    @Override
    public Map<String, PeerInfo> getPeerInfoMap(List<String> list) {
        String joinStr = Joiner.on(",").join(list);
        String url1 = HTTP_127_0_0_1_8383 + GET_ROC_PEER + joinStr;
        String url2 = HTTP_127_0_0_1_8383 + GET_RONA_PEER + joinStr;
        String url3 = HTTP_127_0_0_1_8383 + GET_ROTA_PEER + joinStr;
        String url4 = HTTP_127_0_0_1_8383 + GET_GM_PEER + joinStr;
        String url5 = HTTP_127_0_0_1_8383 + GET_OM_PEER + joinStr;
        String url6 = HTTP_127_0_0_1_8383 + GET_NPM_PEER + joinStr;
        String urlAll = HTTP_127_0_0_1_8383 + GET_ALL_PEER + joinStr;
        RestTemplate restTemplate = new RestTemplate();
        JSONObject json = restTemplate.getForObject(urlAll,JSONObject.class);
        if(ObjectUtil.isEmpty(json)){
            log.info("PEER重要指标返回JSON为空");
            return Maps.newHashMap();
        }
        JSONObject data = json.getJSONObject("data");
        Map<String, PeerInfo> map = Maps.newHashMap();
        list.forEach(k->{
            map.put(k,new PeerInfo(k));
        });
        fieldSet.forEach(k->{
            JSONObject resJson = data.getJSONObject(k);
            if(ObjectUtil.isNotEmpty(resJson)){
//                先不处理为NULL的情况
                JSONArray resArray = resJson.getJSONArray("result");
                resArray.forEach(j->{
                    JSONObject p = (JSONObject) j;
                    String code = p.getStr("stock_identity");
                    String result = p.getStr("res");
                    PeerInfo peerInfo = map.get(code);
                    String fieldName = fieldMap.get(k);
                    Field name = null;
                    try {
                        name = peerInfo.getClass().getDeclaredField(fieldName);
                        name.setAccessible(true);
                    } catch (NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                    try {
                        assert name != null;
                        name.set(peerInfo,result);
                        name.setAccessible(false);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
            }
        });
        Console.log(json.toString());
        return map;
    }

    @Override
    @Async
    public Future<Map<String, PeerInfo>> AsyncGetPeer(List<String> list) {
        Map<String, PeerInfo> map = this.getPeerInfoMap(list);
        return new AsyncResult<>(map);
    }
}
