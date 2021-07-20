package com.fzc.fzcfutu.tool;

import com.futu.openapi.*;
import com.futu.openapi.pb.QotCommon;
import com.futu.openapi.pb.QotGetCapitalDistribution;

public class CapitalDistribution implements FTSPI_Qot, FTSPI_Conn {
    FTAPI_Conn_Qot qot = new FTAPI_Conn_Qot();
    private String code;
    private String market;
    int num;

    public CapitalDistribution() {
        qot.setClientInfo("javaclient", 1);
        qot.setConnSpi(this);
        qot.setQotSpi(this);
    }

    public CapitalDistribution(String code,String market) {
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
        QotGetCapitalDistribution.C2S c2s = QotGetCapitalDistribution.C2S.newBuilder()
                .setSecurity(sec)
                .build();
        QotGetCapitalDistribution.Request req = QotGetCapitalDistribution.Request.newBuilder().setC2S(c2s).build();
        int seqNo = qot.getCapitalDistribution(req);
        System.out.printf("Send QotGetCapitalDistribution: %d\n", seqNo);
    }

    @Override
    public void onDisconnect(FTAPI_Conn client, long errCode) {
        System.out.printf("Qot onDisConnect: %d\n", errCode);
    }

    @Override
    public void onReply_GetCapitalDistribution(FTAPI_Conn client, int nSerialNo, QotGetCapitalDistribution.Response rsp) {
        System.out.printf("Reply: QotGetCapitalDistribution: %d  %s\n", nSerialNo, rsp.toString());
    }

    public static void main(String[] args) {
        FTAPI.init();
        CapitalDistribution qot = new CapitalDistribution();
        qot.start();

        while (true) {
            try {
                Thread.sleep(1000 * 600);
            } catch (InterruptedException exc) {

            }
        }
    }
}


