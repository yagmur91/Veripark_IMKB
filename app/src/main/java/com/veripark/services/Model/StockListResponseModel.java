package com.veripark.services.Model;

import java.util.List;

public class StockListResponseModel {
    List<Stock> stocks;
    ResponseStatus status;

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}
