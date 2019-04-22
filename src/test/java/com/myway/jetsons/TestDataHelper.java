package com.myway.jetsons;

import com.myway.jetsons.dto.*;
import com.myway.jetsons.model.Danisman;
import com.myway.jetsons.model.Emlakci;
import com.myway.jetsons.model.Ilan;
import com.myway.jetsons.model.Ofis;

/**
 * @author Ebru Göksal
 **/
public class TestDataHelper {

    public static final String DETAY_TEST = "DetayTest";
    public static final String TEST_NAME = "TestName";

    private TestDataHelper() {
    }

    public static EmlakciRequest createEmlakciRequest() {
        EmlakciRequest request = new EmlakciRequest();
        request.setName(TEST_NAME);
        return request;
    }

    public static Emlakci createEmlakci() {
        Emlakci request = new Emlakci();
        request.setName(TEST_NAME);
        request.setVitrinHakki(0);
        return request;
    }

    public static OfisRequest createOfisRequest(Long id) {
        OfisRequest ofisRequest = new OfisRequest();
        ofisRequest.setEmlakciId(id);
        ofisRequest.setName("TestOfis");
        return ofisRequest;
    }


    public static OfisUpdateRequest createUpdateOfisRequest() {
        OfisUpdateRequest ofisRequest = new OfisUpdateRequest();
        ofisRequest.setName("TestOfisUpdate");
        return ofisRequest;
    }

    public static Ofis createOfis() {
        Ofis request = new Ofis();
        request.setName(TEST_NAME);
        request.setEmlakci(createEmlakci());
        return request;
    }

    public static DanismanRequest createDanismanRequest(Long id) {
        DanismanRequest danismanRequest = new DanismanRequest();
        danismanRequest.setName("DanismanTest");
        danismanRequest.setOfisId(id);
        return danismanRequest;
    }

    public static Danisman createDanisman() {
        Danisman danisman = new Danisman();
        danisman.setName("DanismanTest");
        danisman.setOfis(createOfis());
        return danisman;
    }

    public static IlanRequest createIlanRequest(Long id) {
        IlanRequest ilanRequest = new IlanRequest();
        ilanRequest.setDetail(DETAY_TEST);
        ilanRequest.setDanismanId(id);
        return ilanRequest;
    }

    public static Ilan createIlan() {
        Ilan ilan = new Ilan();
        ilan.setDetail(DETAY_TEST);
        ilan.setDanisman(createDanisman());
        ilan.setOfis(createOfis());
        ilan.setVitrinHakki(false);
        ilan.setDetail(DETAY_TEST);
        return ilan;
    }

}
