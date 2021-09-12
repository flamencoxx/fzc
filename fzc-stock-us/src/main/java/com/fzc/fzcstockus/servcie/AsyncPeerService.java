package com.fzc.fzcstockus.servcie;

import com.fzc.fzcstockus.DO.StockUsInfoDo;

import java.util.concurrent.Future;

/**
 * @author Flamenco.xxx
 * @date 2021/9/11 17:49
 */
public interface AsyncPeerService {

    public Future<StockUsInfoDo> asyncPeer(String symbol);
}
