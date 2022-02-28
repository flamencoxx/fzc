package com.fzc.fzcstocka.service.Impl;

import com.fzc.fzcstocka.repository.ResultAnalyzerRepository;
import com.fzc.fzcstocka.service.ResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author flamenco.xxx
 * @date 2022/2/24 9:47
 */
@Service
@Slf4j
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultAnalyzerRepository resultDao;


}
