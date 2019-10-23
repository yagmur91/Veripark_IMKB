package com.veripark.adapters;

import com.veripark.Encryption.Encryption;
import com.veripark.services.Model.HandShakeObject;
import com.veripark.services.HandShakePost;
import com.veripark.services.Model.StockListResponseModel;
import com.veripark.services.StockServices;

public class ServiceAdapter {
    public HandShakeObject handShakeResponse;

    HandShakePost handshake = new HandShakePost();
    StockServices ss  = new StockServices();
    private Encryption enc = new Encryption();

    public ServiceAdapter()
    {}
    public HandShakeObject handshake() {
        handShakeResponse =  handshake.post();
        return handShakeResponse;
    }

    public StockListResponseModel StocksList(String filter){
        //if(handShakeResponse == null || handShakeResponse.getLifeTime())
        handshake();
        String ecntiptedPeriod = enc.encript(handShakeResponse.getAesIV(), handShakeResponse.getAesKey(), filter);
        return ss.getStockList(handShakeResponse.getAuthorization(), ecntiptedPeriod);

    }

}
