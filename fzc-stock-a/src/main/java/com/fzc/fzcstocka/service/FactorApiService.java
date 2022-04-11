package com.fzc.fzcstocka.service;

import com.fzc.fzcstocka.model.PeerInfo;

import java.util.concurrent.Future;

/**
 * @author flamenco.xxx
 * @date 2022/2/28 11:16
 */
public interface FactorApiService {

    /**
     * 资本收益率
     * @param code
     * @return
     */
    String getRoc(String code);

    String getRona(String code);

    String getRota(String code);

    String getGm(String code);

    String getOm(String code);

    String getNpm(String code);

    PeerInfo getInfo(String code);

    Future<PeerInfo> AsyncGetInfo(String code);
}
