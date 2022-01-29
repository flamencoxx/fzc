package com.fzc.fzcstockus.profile2;

import cn.hutool.json.JSONObject;
import com.fzc.fzcstockus.model.StockUsImport;
import com.fzc.fzcstockus.reportedModel.FinancialsReported;
import com.fzc.fzcstockus.reportedModel.StockUsReport;
import com.fzc.fzcstockus.reportedModel.TestHaa;
import com.fzc.fzcstockus.servcie.FinancialsReportService;
import com.fzc.fzcstockus.servcie.StockUsImportService;
import com.fzc.fzcstockus.tool.RestTemplateUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author flamenco.xxx
 * @date 2021/12/2 11:07
 */
@SpringBootTest
public class serviceTesting {

    public static final String HELLO = "hello";
    @Autowired
    private FinancialsReportService financialsReportService;

    @Autowired
    private StockUsImportService stockUsImportService;

    @Test
    public void test(){
        List<StockUsImport> list = stockUsImportService.list();
        list.forEach(k->{
            StockUsReport stockUsReport = new StockUsReport();
            stockUsReport.setSymbol(k.getSymbol());
            financialsReportService.save(stockUsReport);
        });
    }

    @Test
    public void update() throws Exception {
        financialsReportService.updateReportAll();
    }

    @Test
    public void getFinancial(){
        String code = "ibm";
        FinancialsReported f = financialsReportService.getReportFast(code);
        System.out.println(f.toString());
    }

    @Test
    public void getFinancialKryo(){
        String code = "aapl";
        FinancialsReported f = financialsReportService.getReportKryo(code);
        System.out.println(f.toString());
    }

    @Test
    public void testGson(){
        String code = "ibm";
        String url = "https://finnhub.io/api/v1/stock/financials-reported?symbol="+ code +"&token=c32mkoaad3ieculvpcsg";
        RestTemplate restTemplate = RestTemplateUtils.getInstance();
        JSONObject json = restTemplate.getForObject(url, JSONObject.class);
        TestHaa testHaa = new TestHaa();
        testHaa = json.toBean(TestHaa.class);
        System.out.println(testHaa.toString());
        System.out.println("");
    }

    @Test
    public void coda(){
    }

    @Test
    public void http(){
        URL url = null;
        try {
            url = new URL("https://finnhub.io/api");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            HttpsURLConnection httpURLConnection = (HttpsURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void ExecutorTest(){
        Executor executor = Executors.newSingleThreadExecutor();
    }

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 60L;

    private volatile int k;
//    ThreadFactory threadFactory = ThreadFactoryBuilder
//            .setNameFormat("threadNameProfix" + "-%d")
//            .setDaemon(true).build();
    @Test
    public void testThreadPool(){

        ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    private static int count = 0;
    private volatile static int nums = 0;
    public int getCount(){
        synchronized (this){
            count++;
            return count;
        }
    }

    public void getNums2(){
        int i = 0;
            while(i < 100){
                nums++;
                i++;
            }
    }

    public void getNums(){
        int i = 0;
        synchronized(this){
            while(i < 100){
                nums++;
                i++;
            }
        }
    }

    @Test
    public void multiThread(){
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        threadPool.execute(new Runnable() {
            @Override
            public void run() {
                getCount();
                System.out.println(count);
            }
        });
    }

    @Test
    public void mulitThreadTesting(){
        for(int i = 0;i < 20; i++){
            new Thread(() -> {
                getNums2();
                System.out.println(nums);
            }).start();
        }
    }
}
