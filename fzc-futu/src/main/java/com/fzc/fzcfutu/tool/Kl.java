package com.fzc.fzcfutu.tool;


import com.futu.openapi.*;
import com.futu.openapi.pb.*;

import java.util.List;

public class Kl implements FTSPI_Qot, FTSPI_Conn {
    QotGetKL.S2C response;
    public String result = null;
    FTAPI_Conn_Qot qot = new FTAPI_Conn_Qot();
    List<QotCommon.KLine> kList = null;

    private String code;
    private String market;
    private int num;
    private String time;



    public Kl() {
        qot.setClientInfo("javaclient", 1);  //设置客户端信息
        qot.setConnSpi(this);  //设置连接回调
        qot.setQotSpi(this);   //设置交易回调
    }
    public Kl(String code, String market) {
        this.code = code;
        this.market=market;
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

    public Kl(String code, String market, String time) {
        this.code = code;
        this.market=market;
        this.time = time;
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
        if (errCode != 0)
            return;

        QotCommon.Security sec = QotCommon.Security.newBuilder()
                .setMarket(num)
                .setCode(code)
                .build();
        QotSub.C2S c2s = QotSub.C2S.newBuilder()
                .addSecurityList(sec)
                .addSubTypeList(QotCommon.SubType.SubType_KL_Day_VALUE)
                .setIsSubOrUnSub(true)
                .build();
        QotSub.Request req = QotSub.Request.newBuilder().setC2S(c2s).build();
        int seqNo = qot.sub(req);
        System.out.printf("Send QotSub: %d\n", seqNo);
    }

    @Override
    public void onDisconnect(FTAPI_Conn client, long errCode) {
        System.out.printf("Qot onDisConnect: %d\n", errCode);
    }

    @Override
    public void onReply_Sub(FTAPI_Conn client, int nSerialNo, QotSub.Response rsp) {
        System.out.printf("Reply: QotSub: %d  %s\n", nSerialNo, rsp.toString());

        if (rsp.getRetType() != Common.RetType.RetType_Succeed_VALUE)
            return;

        QotCommon.Security sec = QotCommon.Security.newBuilder()
                .setMarket(num)
                .setCode(code)
                .build();
        QotGetKL.C2S c2s = QotGetKL.C2S.newBuilder()
                .setSecurity(sec)
                .setKlType(QotCommon.KLType.KLType_Day_VALUE)
                .setRehabType(QotCommon.RehabType.RehabType_Forward_VALUE)
                .setReqNum(50)
                .build();

        QotGetMarketState.C2S c2s1 = QotGetMarketState.C2S.newBuilder()
                .addSecurityList(sec)
                .build();
        QotGetKL.Request req = QotGetKL.Request.newBuilder().setC2S(c2s).build();
        int seqNo = qot.getKL(req);
        System.out.printf("Send QotGetKL: %d\n", seqNo);
        QotGetMarketState.Request req1 = QotGetMarketState.Request.newBuilder().setC2S(c2s1).build();
        int seqNo1 = qot.getMarketState(req1);
        System.out.printf("Send QotGetMarketState: %d\n", seqNo1);
    }

    @Override
    public void onReply_GetKL(FTAPI_Conn client, int nSerialNo, QotGetKL.Response rsp) {
        result = rsp.toString();
//        System.out.println(result)
//        for(QotCommon.KLine s:kList){
//            System.out.println(s.getClosePrice()   );
//        }
//        System.out.printf("Reply: QotGetKL: %d  %s\n" + "结束了", nSerialNo, rsp.toString());



        response = rsp.getS2C();
        kList = response.getKlListList();

    }


    @Override
    public void onReply_GetMarketState(FTAPI_Conn client, int nSerialNo, QotGetMarketState.Response rsp) {
//        System.out.printf("Reply: QotGetMarketState: %d  %s\n", nSerialNo, rsp.toString());
    }

    public static void main(String[] args) {
        FTAPI.init();
        Kl qot = new Kl("002603","SZ");
        qot.start();

        while (true) {
            try {
                Thread.sleep(1000 * 6);
            int i =0;

                Thread.sleep(1000 * 600);
            } catch (InterruptedException exc) {

            }
        }
    }
}



