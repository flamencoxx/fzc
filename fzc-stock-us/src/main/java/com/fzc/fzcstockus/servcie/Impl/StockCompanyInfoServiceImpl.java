package com.fzc.fzcstockus.servcie.Impl;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fzc.fzcstockus.DO.StockUsInfoDo;
import com.fzc.fzcstockus.mapper.StockUsInfoMapper;
import com.fzc.fzcstockus.model.StockUsInfo;
import com.fzc.fzcstockus.repository.StockUsDORepository;
import com.fzc.fzcstockus.repository.StockUsInfoDoRepository;
import com.fzc.fzcstockus.servcie.StockCompanyInfoService;
import com.fzc.fzcstockus.tool.RestTemplateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author Flamenco.xxx
 * @date 2021/7/20 19:49
 */
@Service
public class StockCompanyInfoServiceImpl extends ServiceImpl<StockUsInfoMapper, StockUsInfo> implements StockCompanyInfoService {

    @Autowired
    private StockUsDORepository stockUsDORepository;

    @Autowired
    private StockUsInfoDoRepository stockUsInfoDoRepository;


    @Override
    public StockUsInfoDo findStockCompanyInfo(String symbol) {


            StockUsInfoDo stock = stockUsInfoDoRepository.findStockUsInfoDoBySymbol(symbol);
            String url = "https://www.alphavantage.co/query?function=OVERVIEW&symbol="+ symbol +"&apikey=O0RLB5ZEL3N1GC1U";
            RestTemplate restTemplate = RestTemplateUtils.getInstance();
            JSONObject json = restTemplate.getForObject(url, JSONObject.class);
            assert json != null;
            if(json.isEmpty()){
                return null;
            }
            String Name = json.get("Name").toString();
            String Description = json.get("Description").toString();
            String Sector = json.get("Sector").toString();
            String Industry = json.get("Industry").toString();
            String FullTimeEmployees = json.get("FullTimeEmployees").toString();
            String MarketCapitalization = json.get("MarketCapitalization").toString();
            String EBITDA = json.get("EBITDA").toString();
            String PERatio = json.get("PERatio").toString();
            String PEGRatio = json.get("PEGRatio").toString();
            String BookValue = json.get("BookValue").toString();
            String DividendPerShare = json.get("DividendPerShare").toString();
            String DividendYield = json.get("DividendYield").toString();
            String EPS = json.get("EPS").toString();
            String ProfitMargin = json.get("ProfitMargin").toString();
            String OperatingMarginTTM = json.get("OperatingMarginTTM").toString();
            String ReturnOnAssetsTTM = json.get("ReturnOnAssetsTTM").toString();
            String ReturnOnEquityTTM = json.get("ReturnOnEquityTTM").toString();
            String RevenueTTM = json.get("RevenueTTM").toString();
            String GrossProfitTTM = json.get("GrossProfitTTM").toString();
            String AnalystTargetPrice = json.get("AnalystTargetPrice").toString();
            String TrailingPE = json.get("TrailingPE").toString();
            String ForwardPE = json.get("ForwardPE").toString();
            String PriceToSalesRatioTTM = json.get("PriceToSalesRatioTTM").toString();
            String PriceToBookRatio = json.get("PriceToBookRatio").toString();
            String EVToRevenue = json.get("EVToRevenue").toString();
            String EVToEBITDA = json.get("EVToEBITDA").toString();
            String Beta = json.get("Beta").toString();


            StockUsInfoDo.CompanyOverview companyOverview = new StockUsInfoDo.CompanyOverview();
            companyOverview.setName(Name);
            companyOverview.setDescription(Description);
            companyOverview.setSector(Sector);
            companyOverview.setIndustry(Industry);
            companyOverview.setFullTimeEmployees(FullTimeEmployees);
            companyOverview.setMarketCapitalization(MarketCapitalization);
            companyOverview.setEBITDA(EBITDA);
            companyOverview.setPERatio(PERatio);
            companyOverview.setPEGRatio(PEGRatio);
            companyOverview.setBookValue(BookValue);
            companyOverview.setDividendPerShare(DividendPerShare);
            companyOverview.setDividendYield(DividendYield);
            companyOverview.setEPS(EPS);
            companyOverview.setProfitMargin(ProfitMargin);
            companyOverview.setOperatingMarginTTM(OperatingMarginTTM);
            companyOverview.setReturnOnAssetsTTM(ReturnOnAssetsTTM);
            companyOverview.setReturnOnEquityTTM(ReturnOnEquityTTM);
            companyOverview.setRevenueTTM(RevenueTTM);
            companyOverview.setGrossProfitTTM(GrossProfitTTM);
            companyOverview.setAnalystTargetPrice(AnalystTargetPrice);
            companyOverview.setTrailingPE(TrailingPE);
            companyOverview.setForwardPE(ForwardPE);
            companyOverview.setPriceToBookRatio(PriceToBookRatio);
            companyOverview.setPriceToSalesRatioTTM(PriceToSalesRatioTTM);
            companyOverview.setEVToEBITDA(EVToEBITDA);
            companyOverview.setEVToRevenue(EVToRevenue);
            companyOverview.setBeta(Beta);

            System.out.println(companyOverview.getName());
            stock.setCompanyOverview(companyOverview);
            stockUsInfoDoRepository.save(stock);

            return stock;
    }
}
