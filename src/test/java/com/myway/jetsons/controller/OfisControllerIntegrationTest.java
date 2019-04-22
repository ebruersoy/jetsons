package com.myway.jetsons.controller;

import com.myway.jetsons.IntegrationTest;
import com.myway.jetsons.dto.OfisRequest;
import com.myway.jetsons.dto.OfisUpdateRequest;
import com.myway.jetsons.model.Emlakci;
import com.myway.jetsons.model.Ofis;
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
public class OfisControllerIntegrationTest {
    Emlakci emlakci;

    @Autowired
    OfisController ofisController;

    @Autowired
    OfisRepository ofisRepository;

    @Autowired
    EmlakciRepository emlakciRepository;


    public void should_save_ofis(){
        // Given
        emlakci = emlakciRepository.save(createEmlakci());
        OfisRequest request = createOfisRequest(emlakci.getId());
        // When
        Ofis saved = ofisController.save(request);
        // Then
        assertNotNull(saved);
        assertNotNull(ofisRepository.findById(saved.getId()));
    }

    @Test
    public void should_update_ofis(){
        // Given
        emlakci = emlakciRepository.save(createEmlakci());
        OfisRequest request = createOfisRequest(emlakci.getId());
        OfisUpdateRequest updateRequest = createUpdateOfisRequest();
        Ofis saved = ofisController.save(request);
        // When
        Ofis updated = ofisController.update(saved.getId(), updateRequest);
        // Then
        assertNotNull(updated);
        assertEquals(updated.getName(),updateRequest.getName());
    }

    @Test
    public void should_remove_ofis(){
        // Given
        emlakci = emlakciRepository.save(createEmlakci());
        OfisRequest request = createOfisRequest(emlakci.getId());
        Ofis saved = ofisController.save(request);
        // When
        ResponseEntity responseEntity = ofisController.remove(saved.getId());
        // Then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.ACCEPTED);
        Ofis updated = ofisController.findById(saved.getId());
        assertNull(updated);
    }

    @Test
    public void should_find_all_ofis(){
        // Given
        emlakci = emlakciRepository.save(createEmlakci());
        OfisRequest request = createOfisRequest(emlakci.getId());
        // When
        ofisController.save(request);
        request.setName("new 2");
        ofisController.save(request);
        // Then
        List<Ofis> all = ofisController.findAll();
        assertFalse(all.isEmpty());
    }
}
