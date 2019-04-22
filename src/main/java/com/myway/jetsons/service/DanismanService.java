package com.myway.jetsons.service;

import com.myway.jetsons.dto.DanismanRequest;
import com.myway.jetsons.dto.Mappers;
import com.myway.jetsons.exception.EntityNotFoundException;
import com.myway.jetsons.exception.InvalidRequestException;
import com.myway.jetsons.model.Danisman;
import com.myway.jetsons.model.Ofis;
import com.myway.jetsons.repository.DanismanRepository;
import com.myway.jetsons.repository.OfisRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * @author Ebru Göksal
 */
@Component
public class DanismanService {
    DanismanRepository danismanRepository;
    Mappers mappers;
    OfisRepository ofisRepository;

    public DanismanService(DanismanRepository danismanRepository, Mappers mappers, OfisRepository ofisRepository) {
        this.danismanRepository = danismanRepository;
        this.mappers = mappers;
        this.ofisRepository = ofisRepository;
    }

    public Danisman save(DanismanRequest danismanRequest){
        checkForFields(danismanRequest);
        Danisman danisman = mappers.createDanismanFromDto(danismanRequest);
        return danismanRepository.save(danisman);
    }

    public Danisman update(Long id, DanismanRequest danismanRequest){
        if(id == null || id == 0){
            throw new InvalidRequestException("path variable id", "null or zero", "not null and bigger than zero");
        }
        idCheck(id);
        checkForFields(danismanRequest);
        Danisman danisman = mappers.createDanismanFromDto(danismanRequest);
        danisman.setId(id);
        return danismanRepository.save(danisman);
    }

    private void idCheck(Long id) {
        if(id == null){
            throw new InvalidRequestException("id", "null", "not null");
        }
        if(id == 0){
            throw new InvalidRequestException("id", "0", "bigger than 0");
        }
        Optional<Danisman> existingDanisman = danismanRepository.findById(id);
        if(!existingDanisman.isPresent()){
            throw new EntityNotFoundException("Danisman");
        }
    }

    public void delete(Long id){
        idCheck(id);
        danismanRepository.deleteById(id);
    }

    public List<Danisman> findAll(){
        return danismanRepository.findAll();
    }

    private void checkForFields(DanismanRequest danismanRequest) {
        if(danismanRequest.getName() == null){
            throw new InvalidRequestException("name", "null", "not null");
        }
        if(danismanRequest.getOfisId() == null){
            throw new InvalidRequestException("ofisId", "null", "not null");
        }
        Optional<Ofis> ofis = ofisRepository.findById(danismanRequest.getOfisId());
        if(!ofis.isPresent()){
            throw new EntityNotFoundException("ofis");
        }
    }

    public Danisman findById(Long id) {
        return danismanRepository.findById(id).orElse(null);
    }
}
