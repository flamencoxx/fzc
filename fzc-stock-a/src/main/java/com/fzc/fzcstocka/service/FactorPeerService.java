package com.fzc.fzcstocka.service;

import com.fzc.fzcstocka.model.PeerInfo;

import java.util.List;
import java.util.Map;

/**
 * @author flamenco.xxx
 * @date 2022/3/15 15:19
 */
public interface FactorPeerService {

    /**
     * 获取股票数组的关键指标,需要使用多线程异步等操作尽量减少响应时间
     * @return
     */
    Map<String, PeerInfo> getPeerInfoMap(List<String> list);
}
