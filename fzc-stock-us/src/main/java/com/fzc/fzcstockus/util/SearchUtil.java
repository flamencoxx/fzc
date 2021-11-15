package com.fzc.fzcstockus.util;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/8 15:21
 */
public class SearchUtil {

    public static List<String> splitWords(String words) {
        List<String> list = Splitter.on(" ")
                .trimResults(CharMatcher.anyOf(",")
                        .or(CharMatcher.whitespace())
                        .or(CharMatcher.anyOf("."))
                        .or(CharMatcher.anyOf("'s"))
                        .or(CharMatcher.anyOf(";")))
                .splitToList(words);
        if (list.isEmpty()) {
            return new ArrayList<String>();
        }
        return list;
    }
}
