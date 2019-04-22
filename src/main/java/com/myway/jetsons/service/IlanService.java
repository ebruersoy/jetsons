package com.myway.jetsons.service;

import com.myway.jetsons.dto.IlanRequest;
import com.myway.jetsons.dto.Mappers;
import com.myway.jetsons.exception.EntityNotFoundException;
import com.myway.jetsons.exception.InvalidRequestException;
import com.myway.jetsons.exception.PreconditionFailedException;
import com.myway.jetsons.model.Ilan;
import com.myway.jetsons.repository.DanismanRepository;
import com.myway.jetsons.repository.IlanRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author Ebru Göksal
 */
@Component
public class IlanService {

    IlanRepository ilanRepository;
    DanismanRepository danismanRepository;
    Mappers mappers;
    EmlakciService emlakciService;

    public IlanService(IlanRepository ilanRepository, DanismanRepository danismanRepository, Mappers mappers, EmlakciService emlakciService) {
        this.ilanRepository = ilanRepository;
        this.danismanRepository = danismanRepository;
        this.mappers = mappers;
        this.emlakciService = emlakciService;
    }

    public Ilan save(IlanRequest ilanRequest){
        checkForFields(ilanRequest);
        Ilan ilanFromDto = mappers.createIlanFromDto(ilanRequest);
        ilanFromDto.setCreateDate(LocalDateTime.now());
        return save(ilanFromDto);
    }

    public Ilan update(Long id, IlanRequest ilanRequest) {
        if(id == null || id == 0){
            throw new InvalidRequestException("path variable id", "null or zero", "not null and bigger than zero");
        }
        idCheck(id);
        checkForFields(ilanRequest);
        Ilan ilanFromDto = mappers.createIlanFromDto(ilanRequest);
        ilanFromDto.setUpdateDate(LocalDateTime.now());
        ilanFromDto.setId(id);
        return save(ilanFromDto);
    }

    public void delete(Long id) {
        idCheck(id);
        ilanRepository.deleteById(id);
    }

    public List<Ilan> findAll() {
        return ilanRepository.findAllByOrderByVitrinHakki();
    }

    private Ilan save(Ilan ilan){
        return ilanRepository.save(ilan);
    }

    private void checkForFields(IlanRequest ilanRequest) {
        if(ilanRequest.getDetail() == null){
            throw new InvalidRequestException("Detail","null", "not null");
        }
        if(ilanRequest.getDanismanId() == null){
            throw new InvalidRequestException("DanismanId","null", "not null");
        }
        if(ilanRequest.getDanismanId() == 0){
            throw new InvalidRequestException("DanismanId","0", "bigger than 0");
        }
    }

    private void idCheck(Long id) {
        if(id == null){
            throw new InvalidRequestException("id","null", "not null");
        }
        if(id == 0){
            throw new InvalidRequestException("id","0", "bigger than 0");
        }
        Optional<Ilan> optionalIlan = ilanRepository.findById(id);
        if(!optionalIlan.isPresent()){
            throw new EntityNotFoundException("Ilan");
        }
    }

    public Ilan updateVitrinHakki(Long id, boolean vitrinHakki) {
        idCheck(id);
        Optional<Ilan> optionalIlan = ilanRepository.findById(id);
        if(optionalIlan.isPresent()) {
            Ilan ilan = optionalIlan.get();
            if (ilan.isVitrinHakki() == vitrinHakki) {
                throw new InvalidRequestException("vitrin hakkı", "same", "different");
            }
            if (vitrinHakki) {
                if (ilan.getOfis().getEmlakci().getVitrinHakki() == 0) {
                    throw new PreconditionFailedException("Emlakci Vitrin Hakki 0");
                }
                List<Ilan> ilanList = ilanRepository.findByOfisId(ilan.getOfis().getId());
                if (ilanList.size() >= 3) {
                    throw new PreconditionFailedException("Ofise ait vitrindeki ilan sayısı 3'ten fazla olamaz");
                }
                ilan.setVitrinHakki(true);
                ilan = ilanRepository.save(ilan);
                emlakciService.descreaseVitrinHakki(ilan.getOfis().getEmlakci().getId());
            } else {
                ilan.setVitrinHakki(false);
                ilan = ilanRepository.save(ilan);
                emlakciService.increaseVitrinHakki(ilan.getOfis().getEmlakci().getId());
            }
            return ilan;
        }else{
            return null;
        }
    }

    public Ilan findById(Long id) {
        return ilanRepository.findById(id).orElse(null);
    }
}
