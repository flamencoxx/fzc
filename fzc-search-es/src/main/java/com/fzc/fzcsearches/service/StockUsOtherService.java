package com.fzc.fzcsearches.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcsearches.model.StockUsImport;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/23 15:32
 */
public interface StockUsOtherService  extends IService<StockUsImport> {

    /**
     * 将要删除的股票名字字段字符存入redis，并返回字符表
     * @return
     */
    List<String> remNameComStr();

    /**
     * 将要删除的描述字段字符存入redis，并返回字符表
     * @return
     */
    List<String> remDescComStr();


    /**
     * 将出现多次的字符删除，提高准确性
     * @param str
     * @return
     */
    String convertStr(String str);

}
