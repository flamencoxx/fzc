package com.fzc.fzcstockus.servcie.Impl;

import com.fzc.fzcstockus.api.ESSearchClient;
import com.fzc.fzcstockus.servcie.ESSearchService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/22 11:39
 */
@Service
public class ESSearchServiceImpl implements ESSearchService {

    @Resource
    private ESSearchClient esSearchClient;

    @Override
    public List<String> searchByKeyword(String keyword) {
        return esSearchClient.searchByKeyword(keyword);
    }
}
