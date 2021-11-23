package com.fzc.fzcsearches.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fzc.fzcsearches.model.StockUsImport;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/23 15:32
 */
public interface StockUsOtherService  extends IService<StockUsImport> {

    List<String> remNameComStr();

    List<String> remDescComStr();


    /**
     * 将出现多次的字符删除，提高准确性
     * @param str
     * @return
     */
    String convertStr(String str);

}
