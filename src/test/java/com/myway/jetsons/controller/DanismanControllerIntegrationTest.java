package com.myway.jetsons.controller;

import com.myway.jetsons.IntegrationTest;
import com.myway.jetsons.dto.DanismanRequest;
import com.myway.jetsons.model.Danisman;
import com.myway.jetsons.model.Emlakci;
import com.myway.jetsons.model.Ofis;
import com.myway.jetsons.repository.DanismanRepository;
import com.myway.jetsons.repository.EmlakciRepository;
import com.myway.jetsons.repository.OfisRepository;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.myway.jetsons.TestDataHelper.*;
import static org.junit.Assert.*;

/**
 * @author Ebru Göksal
 */
@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DanismanControllerIntegrationTest {
    Emlakci emlakci;
    Ofis ofis;

    @Autowired
    DanismanController danismanController;

    @Autowired
    DanismanRepository danismanRepository;

    @Autowired
    EmlakciRepository emlakciRepository;

    @Autowired
    OfisRepository ofisRepository;

    public void should_save_danisman(){
        // Given
        emlakci = emlakciRepository.save(createEmlakci());
        ofis = createOfis();
        ofis.setEmlakci(emlakci);
        Ofis savedOfis = ofisRepository.save(ofis);
        DanismanRequest request = createDanismanRequest(savedOfis.getId());
        // When
        Danisman saved = danismanController.save(request);
        // Then
        assertNotNull(saved);
        assertNotNull(danismanRepository.findById(saved.getId()));
    }

    @Test
    public void should_update_danisman(){
        // Given
        emlakci = emlakciRepository.save(createEmlakci());
        ofis = createOfis();
        ofis.setEmlakci(emlakci);
        Ofis savedOfis = ofisRepository.save(ofis);
        DanismanRequest request = createDanismanRequest(savedOfis.getId());
        Danisman saved = danismanController.save(request);
        // When
        request.setName("UpdatedName");
        Danisman updated = danismanController.update(saved.getId(), request);
        //Then
        assertNotNull(updated);
        assertEquals(updated.getName(),"UpdatedName");
    }

    @Test
    public void should_remove_danisman(){
        // Given
        emlakci = emlakciRepository.save(createEmlakci());
        ofis = createOfis();
        ofis.setEmlakci(emlakci);
        Ofis savedOfis = ofisRepository.save(ofis);
        DanismanRequest request = createDanismanRequest(savedOfis.getId());
        Danisman saved = danismanController.save(request);
        // When
        ResponseEntity responseEntity = danismanController.remove(saved.getId());
        assertEquals(responseEntity.getStatusCode(), HttpStatus.ACCEPTED);
        Danisman updated = danismanController.findById(saved.getId());
        assertNull(updated);
    }

    @Test
    public void should_find_all_danisman(){
        emlakci = emlakciRepository.save(createEmlakci());
        ofis = createOfis();
        ofis.setEmlakci(emlakci);
        Ofis savedOfis = ofisRepository.save(ofis);
        DanismanRequest request = createDanismanRequest(savedOfis.getId());
        // When
        danismanController.save(request);
        danismanController.save(request);
        // Then
        List<Danisman> all = danismanController.findAll();
        assertFalse(all.isEmpty());
    }
}
