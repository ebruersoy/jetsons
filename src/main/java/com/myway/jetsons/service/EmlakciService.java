package com.myway.jetsons.service;

import com.myway.jetsons.dto.EmlakciRequest;
import com.myway.jetsons.dto.Mappers;
import com.myway.jetsons.exception.EntityNotFoundException;
import com.myway.jetsons.exception.InvalidRequestException;
import com.myway.jetsons.model.Emlakci;
import com.myway.jetsons.repository.EmlakciRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Ebru Göksal
 */
@Component
public class EmlakciService {

    EmlakciRepository emlakciRepository;
    Mappers mappers;

    public EmlakciService(EmlakciRepository emlakciRepository, Mappers mappers) {
        this.emlakciRepository = emlakciRepository;
        this.mappers = mappers;
    }

    public Emlakci save(EmlakciRequest emlakciRequest){
        checkForFields(emlakciRequest);
        Emlakci emlakci = mappers.createEmlakciFromDto(emlakciRequest);
        return emlakciRepository.save(emlakci);
    }

    public Emlakci update(Long id, EmlakciRequest emlakciRequest) {
        idCheck(id);
        checkForFields(emlakciRequest);
        Emlakci emlakci = mappers.createEmlakciFromDto(emlakciRequest);
        emlakci.setId(id);
        return emlakciRepository.save(emlakci);
    }

    public void delete(Long id) {
        idCheck(id);
        emlakciRepository.deleteById(id);
    }

    public List<Emlakci> findAll() {
        return emlakciRepository.findAll();
    }

    private void checkForFields(EmlakciRequest emlakci) {
        if(emlakci.getName() == null){
            throw new InvalidRequestException("Name","null", "not null");
        }
    }

    private void idCheck(Long id) {
        if(id == null){
            throw new InvalidRequestException("id","null", "not null");
        }
        if(id == 0){
            throw new InvalidRequestException("id","0", "bigger than 0");
        }
        Optional<Emlakci> optionalIlan = emlakciRepository.findById(id);
        if(!optionalIlan.isPresent()){
            throw new EntityNotFoundException("Emlakci");
        }
    }

    public Emlakci updateVitrinHakki(Long id, Integer vitrinHakki) {
        if(id == null || id == 0){
            throw new InvalidRequestException("path variable id", "null or zero", "not null and bigger than zero");
        }
        idCheck(id);
        if(vitrinHakki > 15){
            throw new InvalidRequestException("vitrinHakki", "bigger than 15","can be max 15");
        }
        Emlakci emlakci = emlakciRepository.findById(id).get();
        emlakci.setVitrinHakki(vitrinHakki);
        return emlakciRepository.save(emlakci);
    }

    public void descreaseVitrinHakki(Long id) {
        Optional<Emlakci> optionalEmlakci = emlakciRepository.findById(id);
        if(optionalEmlakci.isPresent()) {
            Emlakci emlakci = optionalEmlakci.get();
            emlakci.setVitrinHakki(emlakci.getVitrinHakki() - 1);
            emlakciRepository.save(emlakci);
        }
    }

    public void increaseVitrinHakki(Long id) {
        Optional<Emlakci> optionalEmlakci = emlakciRepository.findById(id);
        if(optionalEmlakci.isPresent()) {
            Emlakci emlakci = optionalEmlakci.get();
            emlakci.setVitrinHakki(emlakci.getVitrinHakki() + 1);
            emlakciRepository.save(emlakci);
        }
    }

    public Emlakci findById(Long id) {
        return emlakciRepository.findById(id).orElse(null);
    }
}
