package com.fzc.fzcstocka.service.Impl;

import com.fzc.fzcstocka.model.ResultAnalyzer;
import com.fzc.fzcstocka.repository.ResultAnalyzerRepository;
import com.fzc.fzcstocka.service.ResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2022/2/24 9:47
 */
@Service
@Slf4j
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultAnalyzerRepository resultDao;


    @Override
    public List<ResultAnalyzer> getCfList(String code) {
        return null;
    }

    @Override
    public List<ResultAnalyzer> getMfList(String code) {
        return null;
    }

    @Override
    public List<ResultAnalyzer> getPrList(String code) {
        return null;
    }

    @Override
    public List<ResultAnalyzer> getAcList(String code) {
        return null;
    }

    @Override
    public List<ResultAnalyzer> getIsList(String code) {
        return null;
    }

    @Override
    public List<ResultAnalyzer> getAllList(String code) {
        return null;
    }
}
