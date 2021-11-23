package com.fzc.fzcstockus.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/22 11:10
 */
@Component
@FeignClient(value = "fzc-search-es",url = "http://127.0.0.1:8087")
@RequestMapping("/ESSearch")
public interface ESSearchClient {

    @GetMapping(value = "/search")
    List<String> searchByKeyword(@RequestParam(value ="keyword") String keyword);

}
