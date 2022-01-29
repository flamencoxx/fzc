package com.fzc.fzccommon.exceptionTest;

/**
 * @author flamenco.xxx
 * @date 2021/12/30 10:00
 */
public interface BaseErrorInfoInterface {

    /**
     *  错误码
     * @return
     */
    String getResultCode();

    /**
     * 错误描述
     * @return
     */
    String getResultMsg();
}


