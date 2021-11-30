package com.fzc.fzcsearches.util;

import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * @author flamenco.xxx
 * @date 2021/11/8 15:21
 */
public class SearchUtil {

    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;
    @Value("${redis.key.sector}")
    private String REDIS_KEY_SECTOR;

    public static List<String> splitWords(String words) {
        List<String> list = Splitter.onPattern(" |-")
                .trimResults(CharMatcher.anyOf(",")
                        .or(CharMatcher.whitespace())
                        .or(CharMatcher.anyOf("."))
                        .or(CharMatcher.anyOf("'s"))
                        .or(CharMatcher.anyOf(";"))
                        .or(CharMatcher.anyOf("&"))
                        .or(CharMatcher.anyOf("-")))
                .splitToList(words);
        if (list.isEmpty()) {
            return new ArrayList<String>();
        }
        return list;
    }

    public static boolean isWords(String words) {

        return false;
    }

    public static List<String> splitWords2(String words) {
        List<String> list = Splitter.onPattern(" |-")
                .trimResults(CharMatcher.anyOf(",")
                        .or(CharMatcher.whitespace())
                        .or(CharMatcher.anyOf("."))
                        .or(CharMatcher.anyOf(";"))
                        .or(CharMatcher.anyOf("&"))
                        .or(CharMatcher.anyOf("-")))
                .splitToList(words);
        if (list.isEmpty()) {
            return new ArrayList<String>();
        }
        return list;
    }

    public static List<String> splitWords3(String words) {
        String result = words.replaceAll("\\p{Punct}", " ");
        List<String> list = Splitter.onPattern(" |-")
                .trimResults(CharMatcher.anyOf(",")
                        .or(CharMatcher.whitespace()))
                .omitEmptyStrings()
                .splitToList(result);
        if (list.isEmpty()) {
            return new ArrayList<String>();
        }
        return list;
    }

    public static int wordSum(String str) {
        int word = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i == (str.length() - 1) && (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z')) {
                word++;
            }
            if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') {
                continue;
            } else {
                word++;
            }
        }
        return word;
    }

    public static boolean checkIsSector() {
        return false;
    }

    //    Remove redundant characters
    public static String removeChar(String word) {
        List<String> words = SearchUtil.splitWords(word);
        return null;
    }
}



