package com.myway.jetsons.dto;

import com.myway.jetsons.exception.InvalidRequestException;
import com.myway.jetsons.model.Danisman;
import com.myway.jetsons.model.Emlakci;
import com.myway.jetsons.model.Ilan;
import com.myway.jetsons.model.Ofis;
import com.myway.jetsons.repository.DanismanRepository;
import com.myway.jetsons.repository.EmlakciRepository;
import com.myway.jetsons.repository.OfisRepository;
import org.springframework.stereotype.Component;

/**
 * @author Ebru Göksal
 */
@Component
public class Mappers {

    private DanismanRepository danismanRepository;
    private OfisRepository ofisRepository;
    private EmlakciRepository emlakciRepository;

    public Mappers(DanismanRepository danismanRepository, OfisRepository ofisRepository, EmlakciRepository emlakciRepository) {
        this.danismanRepository = danismanRepository;
        this.ofisRepository = ofisRepository;
        this.emlakciRepository = emlakciRepository;
    }

    public Ilan createIlanFromDto(IlanRequest ilanRequest) {
        Ilan ilan = new Ilan();
        ilan.setDetail(ilanRequest.getDetail());
        Danisman danisman = danismanRepository.findById(ilanRequest.getDanismanId())
            .orElseThrow(() -> new InvalidRequestException("Danisman", "not exists", "must exists"));
        ilan.setDanisman(danisman);
        ilan.setOfis(danisman.getOfis());
        return ilan;
    }

    public Danisman createDanismanFromDto(DanismanRequest danismanRequest) {
        Danisman danisman = new Danisman();
        danisman.setName(danismanRequest.getName());
        Ofis ofis = ofisRepository.findById(danismanRequest.getOfisId())
            .orElseThrow(() -> new InvalidRequestException("Ofis", "not exists", "must exists"));
        danisman.setOfis(ofis);
        return danisman;
    }

    public Ofis createOfisFromDto(OfisRequest ofisRequest) {
        Ofis ofis = new Ofis();
        ofis.setName(ofisRequest.getName());
        Emlakci emlakci = emlakciRepository.findById(ofisRequest.getEmlakciId())
            .orElseThrow(() -> new InvalidRequestException("Emlakci", "not exists", "must exists"));
        ofis.setEmlakci(emlakci);
        return ofis;
    }

    public Emlakci createEmlakciFromDto(EmlakciRequest emlakciRequest) {
        Emlakci emlakci = new Emlakci();
        emlakci.setName(emlakciRequest.getName());
        return emlakci;
    }
}
