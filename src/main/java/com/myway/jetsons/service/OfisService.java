package com.myway.jetsons.service;

import com.myway.jetsons.dto.Mappers;
import com.myway.jetsons.dto.OfisRequest;
import com.myway.jetsons.dto.OfisUpdateRequest;
import com.myway.jetsons.exception.EntityNotFoundException;
import com.myway.jetsons.exception.InvalidRequestException;
import com.myway.jetsons.model.Emlakci;
import com.myway.jetsons.model.Ofis;
import com.myway.jetsons.repository.EmlakciRepository;
import com.myway.jetsons.repository.OfisRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Ebru Göksal
 */
@Component
public class OfisService {
    OfisRepository ofisRepository;
    Mappers mappers;
    EmlakciRepository emlakciRepository;

    public OfisService(OfisRepository ofisRepository, Mappers mappers, EmlakciRepository emlakciRepository) {
        this.ofisRepository = ofisRepository;
        this.mappers = mappers;
        this.emlakciRepository = emlakciRepository;
    }

    public Ofis save(OfisRequest ofisRequest){
        checkForFields(ofisRequest);
        Ofis ofis = mappers.createOfisFromDto(ofisRequest);
        return ofisRepository.save(ofis);
    }

    public Ofis update(Long id, OfisUpdateRequest ofisRequest){
        Ofis ofis = getOfficeByIdIfExists(id);
        if(ofisRequest.getName() == null){
            throw new InvalidRequestException("name", "null", "not null");
        }
        ofis.setName(ofisRequest.getName());
        return ofisRepository.save(ofis);
    }

    private Ofis getOfficeByIdIfExists(Long id) {
        if(id == null){
            throw new InvalidRequestException("id", "null", "not null");
        }
        if(id == 0){
            throw new InvalidRequestException("id", "0", "bigger than 0");
        }
        Optional<Ofis> existingOfis = ofisRepository.findById(id);
        if(!existingOfis.isPresent()){
            throw new EntityNotFoundException("Ofis");
        }
        return existingOfis.get();
    }

    public void delete(Long id){
        getOfficeByIdIfExists(id);
        ofisRepository.deleteById(id);
    }

    public List<Ofis> findAll(){
        return ofisRepository.findAll();
    }

    private void checkForFields(OfisRequest ofisRequest) {
        if(ofisRequest.getName() == null){
            throw new InvalidRequestException("name", "null", "not null");
        }
        if(ofisRequest.getEmlakciId() == null){
            throw new InvalidRequestException("emlakciId", "null", "not null");
        }
        Optional<Emlakci> emlakci = emlakciRepository.findById(ofisRequest.getEmlakciId());
        if(!emlakci.isPresent()){
            throw new EntityNotFoundException("Emlakci");
        }
    }

    public Ofis findById(Long id) {
        return ofisRepository.findById(id).orElse(null);
    }
}
