package com.fzc.fzccommon.exceptionTest;

import cn.hutool.core.lang.Console;
import org.junit.Test;

/**
 * @author flamenco.xxx
 * @date 2021/12/30 10:16
 */
public class ExceptionTest {

    @Test
    public void test(){
        ResultResponse res = ResultResponse.error(ExceptionEnum.BODY_NOT_MATCH);
        Console.log(res.getCode() + ":" + res.getMessage() + ":" + res.getResult());
    }
}
