package com.fzc.fzcstockus.model;

import java.util.List;

/**
 * @author Flamenco.xxx
 * @date 2021/6/24 20:07
 */
public class StockUsHistory {
    private static class Data{
        private Double close;
        private Double high;
        private Double low;
        private Double open;
        private String time;

        public Data(Double close, Double high, Double low, Double open,String time) {
            this.close = close;
            this.high = high;
            this.low = low;
            this.open = open;
            this.time = time;
        }

        public Data() {
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public Double getClose() {
            return close;
        }

        public void setClose(Double close) {
            this.close = close;
        }

        public Double getHigh() {
            return high;
        }

        public void setHigh(Double high) {
            this.high = high;
        }

        public Double getLow() {
            return low;
        }

        public void setLow(Double low) {
            this.low = low;
        }

        public Double getOpen() {
            return open;
        }

        public void setOpen(Double open) {
            this.open = open;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Data{");
            sb.append("close=").append(close);
            sb.append(", high=").append(high);
            sb.append(", low=").append(low);
            sb.append(", open=").append(open);
            sb.append(", time=").append(time);
            sb.append('}');
            return sb.toString();
        }
    }
    private List<Data> dataList;

    public StockUsHistory(List<Data> dataList) {
        this.dataList = dataList;
    }

    public StockUsHistory() {
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("StockUsHistory{");
        sb.append("dataList=").append(dataList);
        sb.append('}');
        return sb.toString();
    }
}
