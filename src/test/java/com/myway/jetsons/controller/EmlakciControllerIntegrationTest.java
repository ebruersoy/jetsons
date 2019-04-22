package com.myway.jetsons.controller;

import com.myway.jetsons.IntegrationTest;
import com.myway.jetsons.dto.EmlakciRequest;
import com.myway.jetsons.model.Emlakci;
import com.myway.jetsons.repository.EmlakciRepository;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static com.myway.jetsons.TestDataHelper.createEmlakciRequest;
import static org.junit.Assert.*;

/**
 * @author Ebru Göksal
 */
@Category(IntegrationTest.class)
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmlakciControllerIntegrationTest {

    @Autowired
    EmlakciController emlakciController;

    @Autowired
    EmlakciRepository emlakciRepository;



    @Test
    public void should_save_emlakci(){
        // Given
        EmlakciRequest request = createEmlakciRequest();
        // When
        Emlakci saved = emlakciController.save(request);
        // Then
        assertNotNull(saved);
        assertNotNull(emlakciRepository.findById(saved.getId()));
    }

    @Test
    public void should_update_emlakci(){
        // Given
        EmlakciRequest request = createEmlakciRequest();
        Emlakci saved = emlakciController.save(request);
        // When
        request.setName("UpdatedName");
        Emlakci updated = emlakciController.update(saved.getId(), request);
        // Then
        assertNotNull(updated);
        assertEquals(updated.getName(),"UpdatedName");
    }

    @Test
    public void should_update_emlakci_vitrin_hakki(){
        // Given
        EmlakciRequest request = createEmlakciRequest();
        Emlakci saved = emlakciController.save(request);
        // When
        ResponseEntity responseEntity = emlakciController.updateVitrinHakki(saved.getId(), 10);
        // Then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
        Emlakci updated = emlakciController.findById(saved.getId());
        assertEquals(updated.getVitrinHakki(),10);
    }

    @Test
    public void should_remove_emlakci(){
        // Given
        EmlakciRequest request = createEmlakciRequest();
        Emlakci saved = emlakciController.save(request);
        // When
        ResponseEntity responseEntity = emlakciController.remove(saved.getId());
        // Then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.ACCEPTED);
        Emlakci updated = emlakciController.findById(saved.getId());
        assertNull(updated);
    }

    @Test
    public void should_find_all_emlakci(){
        // Given
        EmlakciRequest request = createEmlakciRequest();
        // When
        emlakciController.save(request);
        request.setName("new 2");
        emlakciController.save(request);
        // Then
        List<Emlakci> all = emlakciController.findAll();
        assertFalse(all.isEmpty());
    }
}
