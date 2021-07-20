package com.fzc.fzcfutu.tool;

import com.futu.openapi.*;
import com.futu.openapi.pb.QotCommon;
import com.futu.openapi.pb.QotGetMarketState;
import com.futu.openapi.pb.QotGetSecuritySnapshot;

import java.util.List;

public class Market implements FTSPI_Qot, FTSPI_Conn {
    QotGetMarketState.S2C response;
    QotGetSecuritySnapshot.S2C responseSnap;
    FTAPI_Conn_Qot qot = new FTAPI_Conn_Qot();
    private String code;
    private String market;
    int num;
    int marketState;
    List<QotGetMarketState.MarketInfo> marketInfoList;
    List<QotGetSecuritySnapshot.Snapshot> snapshotList;

    public Market() {
        qot.setClientInfo("javaclient", 1);
        qot.setConnSpi(this);
        qot.setQotSpi(this);
    }
    public Market(String code,String market) {
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
        if (errCode != 0)
            return;

        QotCommon.Security sec = QotCommon.Security.newBuilder()
                .setMarket(num)
                .setCode(code)
                .build();
        QotGetMarketState.C2S c2s = QotGetMarketState.C2S.newBuilder()
                .addSecurityList(sec)
                .build();
        QotGetMarketState.Request req = QotGetMarketState.Request.newBuilder().setC2S(c2s).build();
        int seqNo = qot.getMarketState(req);
        System.out.printf("Send QotGetMarketState: %d\n", seqNo);


        QotGetSecuritySnapshot.C2S c2s1 = QotGetSecuritySnapshot.C2S.newBuilder()
                .addSecurityList(sec)
                .build();
        QotGetSecuritySnapshot.Request req1 = QotGetSecuritySnapshot.Request.newBuilder().setC2S(c2s1).build();
        int seqNo1 = qot.getSecuritySnapshot(req1);
        System.out.printf("Send QotGetSecuritySnapshot: %d\n", seqNo1);

    }

    @Override
    public void onDisconnect(FTAPI_Conn client, long errCode) {
        System.out.printf("Qot onDisConnect: %d\n", errCode);
    }

    @Override
    public void onReply_GetMarketState(FTAPI_Conn client, int nSerialNo, QotGetMarketState.Response rsp) {
//        System.out.printf("Reply: QotGetMarketState: %d  %s\n", nSerialNo, rsp.toString());
        response = rsp.getS2C();
        marketInfoList = response.getMarketInfoListList();
        for(QotGetMarketState.MarketInfo s:marketInfoList){
            System.out.println(s.getName());
            System.out.println(s.getMarketState());
        }

    }

    @Override
    public void onReply_GetSecuritySnapshot(FTAPI_Conn client, int nSerialNo, QotGetSecuritySnapshot.Response rsp) {
//        System.out.printf("Reply: QotGetSecuritySnapshot: %d  %s\n", nSerialNo, rsp.toString());
        responseSnap = rsp.getS2C();
        snapshotList = responseSnap.getSnapshotListList();
        for (QotGetSecuritySnapshot.Snapshot s:snapshotList){
            System.out.println(s.getBasic());
            System.out.println(s.getEquityExData());
//            System.out.println(s.getWarrantExData());
//            System.out.println(s.getPlateExData());
//            System.out.println(s.getTrustExData());
        }
    }




    public static void main(String[] args) {
        FTAPI.init();
        Market qot = new Market("002603","SZ");
        qot.start();

        while (true) {
            try {
                Thread.sleep(1000);
//                System.out.println(qot.marketState);
                Thread.sleep(1000 * 600);
            } catch (InterruptedException exc) {

            }
        }
    }
}

