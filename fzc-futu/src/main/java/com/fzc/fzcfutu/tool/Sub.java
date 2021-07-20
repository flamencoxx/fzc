package com.fzc.fzcfutu.tool;

import com.futu.openapi.*;
import com.futu.openapi.pb.*;

/**
 * @author 11615
 */
public class Sub implements FTSPI_Qot, FTSPI_Conn {
    FTAPI_Conn_Qot qot = new FTAPI_Conn_Qot();
    private String code;
    private String market;
    int num;

    public Sub() {
        qot.setClientInfo("javaclient", 1);
        qot.setConnSpi(this);
        qot.setQotSpi(this);
    }

    public Sub(String code, String market) {
        this.code = code;
        this.market = market;
        qot.setClientInfo("javaclient", 1);
        qot.setConnSpi(this);
        qot.setQotSpi(this);

        String sz = "SZ";
        String sh = "SH";
        String hk = "HK";
        num = 0;
        if (market.equals(sz)){
            num = 22;
        }
        else if (market.equals(sh)){
            num = 21;
        }
        else if (market.equals(hk)){
            num = 2;
        }
    }


    public void start() {
        qot.initConnect("127.0.0.1", (short)11111, false);
    }

    @Override
    public void onInitConnect(FTAPI_Conn client, long errCode, String desc)
    {
        System.out.printf("Qot onInitConnect: ret=%b desc=%s connID=%d\n", errCode, desc, client.getConnectID());
        if (errCode != 0) {
            return;
        }

        QotCommon.Security sec = QotCommon.Security.newBuilder()
                .setMarket(num)
                .setCode(code)
                .build();
        QotSub.C2S c2s = QotSub.C2S.newBuilder()
                .addSecurityList(sec)
                .addSubTypeList(QotCommon.SubType.SubType_Basic_VALUE)
//                .addSubTypeList(QotCommon.SubType.SubType_KL_Day_VALUE)
                .setIsSubOrUnSub(true)
                .setIsRegOrUnRegPush(true)
                .build();
        QotSub.Request req = QotSub.Request.newBuilder().setC2S(c2s).build();
        int seqNo = qot.sub(req);
        System.out.printf("Send QotSub: %d\n", seqNo);

        QotGetSubInfo.C2S c2s1 = QotGetSubInfo.C2S.newBuilder()
                .build();
        QotGetSubInfo.Request req1 = QotGetSubInfo.Request.newBuilder().setC2S(c2s1).build();
        int seqNo1 = qot.getSubInfo(req1);
        System.out.printf("Send QotGetSubInfo: %d\n", seqNo1);
    }

    @Override
    public void onDisconnect(FTAPI_Conn client, long errCode) {
        System.out.printf("Qot onDisConnect: %d\n", errCode);
    }

    @Override
    public void onReply_Sub(FTAPI_Conn client, int nSerialNo, QotSub.Response rsp) {
        System.out.printf("Reply: QotSub: %d  %s\n", nSerialNo, rsp.toString());
    }

    @Override
    public void onReply_GetSubInfo(FTAPI_Conn client, int nSerialNo, QotGetSubInfo.Response rsp) {
        System.out.printf("Reply: QotGetSubInfo: %d  %s\n", nSerialNo, rsp.toString());
    }

    @Override
    public void onPush_UpdateBasicQuote(FTAPI_Conn client, QotUpdateBasicQot.Response rsp) {
        System.out.printf("Push: UpdateBasicQuote: %s\n", rsp.toString());
    }

//    @Override
//    public void onPush_UpdateKL(FTAPI_Conn client, QotUpdateKL.Response rsp) {
//        System.out.printf("Push: UpdateKL: %s\n", rsp.toString());
//    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        FTAPI.init();
        Sub qot = new Sub("002603","SZ");
        qot.start();


        while (true) {
            try {
                Thread.sleep(1000);
                long endTime = System.currentTimeMillis();
                System.out.println(endTime - startTime-1000);
                Thread.sleep(1000 * 600);
            } catch (InterruptedException exc) {

            }
        }
    }
}


