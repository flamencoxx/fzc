package com.fzc.fzcstocka.service.Impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.fzc.fzcstocka.model.PeerInfo;
import com.fzc.fzcstocka.model.ResAndPeriod;
import com.fzc.fzcstocka.service.FactorApiService;
import com.fzc.fzcstocka.service.StockAInfoService;
import com.fzc.fzcstocka.util.RestTemplateUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @date 2022/2/28 11:16
 */
@Service
@Slf4j
public class FactorApiServiceImpl implements FactorApiService {

    private static final String HTTP_127_0_0_1_8383 = "http://127.0.0.1:8383/";

    private static final String HTTP_192_168_31_230_8383 = "http://192.168.31.230:8383/";

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

    private  static final String GET_INFO = "get_info/";

    /**
     * 获取全部参数,不再单独获取提高性能
     */
    private static final String GET_ALL_PEER = "get_ALL_peer/";

    public static final String GET_ROC = "get_ROC/";
    public static final String DATA = "data";
    public static final String GET_RONA = "get_RONA/";
    @Autowired
    private StockAInfoService stockAInfoService;


    private static Map<String, String> fieldMap = Maps.newHashMap();

    private  static Map<String,String> fieldListMap = Maps.newHashMap();

    private static final Set<String> fieldSet = Sets.newHashSet(ROC_CN,RONA_CN,ROTA_CN,GM_CN,OM_CN,NPM_CN);

    public static final String LD_CN = "流动比率";
    private static final Set<String> fieldListSet = Sets.newHashSet(ROC_CN,RONA_CN,ROTA_CN,GM_CN,OM_CN,NPM_CN, LD_CN);

    @PostConstruct
    public void init(){
        fieldMap.put(ROC_CN,"roc");
        fieldMap.put(RONA_CN,"rona");
        fieldMap.put(ROTA_CN,"rota");
        fieldMap.put(GM_CN,"gm");
        fieldMap.put(OM_CN,"om");
        fieldMap.put(NPM_CN,"npm");

        fieldListMap.put(ROC_CN,"rocList");
        fieldListMap.put(RONA_CN,"ronaList");
        fieldListMap.put(ROTA_CN,"rotaList");
        fieldListMap.put(GM_CN,"gmList");
        fieldListMap.put(OM_CN,"omList");
        fieldListMap.put(NPM_CN,"npmList");
        fieldListMap.put(LD_CN,"ldList");


    }


    @Override
    public String getRoc(String code) {
        String url = HTTP_127_0_0_1_8383 + GET_RONA + code;
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url, JSONObject.class);
        if(ObjectUtil.isNull(json)){
            return "";
        }
        String resStr = json.getStr("result");
        return resStr;
    }

    @Override
    public String getRona(String code) {
        String url = HTTP_127_0_0_1_8383 + GET_ROC + code;
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url, JSONObject.class);
        if(ObjectUtil.isNull(json)){
            return "";
        }
        String resStr = json.getStr("result");
        return resStr;
    }

    @Override
    public String getRota(String code) {
        return null;
    }

    @Override
    public String getGm(String code) {
        return null;
    }

    @Override
    public String getOm(String code) {
        return null;
    }

    @Override
    public String getNpm(String code) {
        return null;
    }

    @Override
    public PeerInfo getInfo(String code) {
        String url = HTTP_192_168_31_230_8383 + GET_INFO + code;
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url,JSONObject.class);
        if(ObjectUtil.isEmpty(json)){
            log.info("PEER重要指标返回JSON为空");
            return new PeerInfo();
        }
        JSONObject data = json.getJSONObject("data");
        JSONObject dataList = json.getJSONObject("data_list");
        PeerInfo peerInfo = new PeerInfo();
        fieldSet.forEach(k ->{
            JSONObject resJson = data.getJSONObject(k);
            if(ObjectUtil.isNotEmpty(resJson)){
                JSONArray resArray = resJson.getJSONArray("result");
                resArray.forEach(j ->{
                    JSONObject p = (JSONObject) j;
                    String c = p.getStr("stock_identity");
                    String result = p.getStr("res");
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

        fieldListSet.forEach(k ->{
            JSONArray resJson = dataList.getJSONArray(k);
            if(ObjectUtil.isNotEmpty(resJson)){
                List<ResAndPeriod> resAndPeriods = Lists.newArrayList();
                resJson.forEach(j ->{
                    JSONObject p = (JSONObject) j;
                    ResAndPeriod resAndPeriod = new ResAndPeriod();
                    resAndPeriod.setPeriod( p.getStr("period"));
                    resAndPeriod.setRes(String.valueOf(p.get("param")));
                    resAndPeriods.add(resAndPeriod);
                });
                Field name = null;
                String fieldNameList = fieldListMap.get(k);
                try {
                    name = peerInfo.getClass().getDeclaredField(fieldNameList);
                    name.setAccessible(true);
                    name.set(peerInfo,resAndPeriods);
                    name.setAccessible(false);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        });
        peerInfo.setCode(code);
        return peerInfo;
    }

    @Override
    @Async
    public Future<PeerInfo> AsyncGetInfo(String code) {
        PeerInfo info = this.getInfo(code);
        return new AsyncResult<>(info);
    }
}
