package com.myway.jetsons.controller;

import com.myway.jetsons.IntegrationTest;
import com.myway.jetsons.dto.IlanRequest;
import com.myway.jetsons.model.Danisman;
import com.myway.jetsons.model.Emlakci;
import com.myway.jetsons.model.Ilan;
import com.myway.jetsons.model.Ofis;
import com.myway.jetsons.repository.DanismanRepository;
import com.myway.jetsons.repository.EmlakciRepository;
import com.myway.jetsons.repository.IlanRepository;
import com.myway.jetsons.repository.OfisRepository;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.myway.jetsons.TestDataHelper.*;
import static org.junit.Assert.*;

/**
 * @author Ebru Göksal
 */
@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class IlanControllerIntegrationTest {
    Emlakci emlakci;
    Ofis ofis;
    Danisman danisman;

    @Autowired
    IlanController ilanController;

    @Autowired
    IlanRepository ilanRepository;

    @Autowired
    EmlakciRepository emlakciRepository;

    @Autowired
    OfisRepository ofisRepository;

    @Autowired
    DanismanRepository danismanRepository;

    public void should_save_danisman(){
        // Given
        emlakci = emlakciRepository.save(createEmlakci());
        ofis = createOfis();
        ofis.setEmlakci(emlakci);
        Ofis savedOfis = ofisRepository.save(ofis);
        danisman = createDanisman();
        danisman.setOfis(savedOfis);
        danisman = danismanRepository.save(danisman);
        IlanRequest request = createIlanRequest(danisman.getId());
        // When
        Ilan saved = ilanController.save(request);
        // Then
        assertNotNull(saved);
        assertNotNull(ilanRepository.findById(saved.getId()));
    }

    @Test
    public void should_update_ilan(){
        // Given
        emlakci = emlakciRepository.save(createEmlakci());
        ofis = createOfis();
        ofis.setEmlakci(emlakci);
        Ofis savedOfis = ofisRepository.save(ofis);
        danisman = createDanisman();
        danisman.setOfis(savedOfis);
        danisman = danismanRepository.save(danisman);
        IlanRequest request = createIlanRequest(danisman.getId());
        Ilan saved = ilanController.save(request);
        // When
        request.setDetail("UpdatedDetail");
        Ilan updated = ilanController.update(saved.getId(), request);
        //Then
        assertNotNull(updated);
        assertEquals(updated.getDetail(),"UpdatedDetail");
    }

    @Test
    public void should_remove_ilan(){
        // Given
        emlakci = emlakciRepository.save(createEmlakci());
        ofis = createOfis();
        ofis.setEmlakci(emlakci);
        Ofis savedOfis = ofisRepository.save(ofis);
        danisman = createDanisman();
        danisman.setOfis(savedOfis);
        danisman = danismanRepository.save(danisman);
        IlanRequest request = createIlanRequest(danisman.getId());
        Ilan saved = ilanController.save(request);
        // When
        ResponseEntity responseEntity = ilanController.remove(saved.getId());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.ACCEPTED);
        Ilan updated = ilanController.findById(saved.getId());
        assertNull(updated);
    }

    @Test
    public void should_update_vitrin_hakki(){
        // Given
        Emlakci createdemlakci = createEmlakci();
        createdemlakci.setVitrinHakki(10);
        this.emlakci = emlakciRepository.save(createdemlakci);
        ofis = createOfis();
        ofis.setEmlakci(this.emlakci);
        Ofis savedOfis = ofisRepository.save(ofis);
        danisman = createDanisman();
        danisman.setOfis(savedOfis);
        danisman = danismanRepository.save(danisman);
        IlanRequest request = createIlanRequest(danisman.getId());
        Ilan saved = ilanController.save(request);
        // When
        boolean oldvitrinHakki = saved.isVitrinHakki();
        ilanController.updateVitrinHakki(saved.getId(),!oldvitrinHakki);
        // Then
        Ilan updated = ilanController.findById(saved.getId());
        assertNotEquals(updated.isVitrinHakki(),saved.isVitrinHakki());
    }
}
