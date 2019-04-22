package com.myway.jetsons.service;

import com.myway.jetsons.UnitTest;
import com.myway.jetsons.dto.Mappers;
import com.myway.jetsons.dto.OfisRequest;
import com.myway.jetsons.dto.OfisUpdateRequest;
import com.myway.jetsons.exception.InvalidRequestException;
import com.myway.jetsons.model.Ofis;
import com.myway.jetsons.repository.EmlakciRepository;
import com.myway.jetsons.repository.OfisRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.Optional;

import static com.myway.jetsons.TestDataHelper.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;

/**
 * @author Ebru Göksal
 */
@Category(UnitTest.class)
public class OfisServiceUnitTest {
    OfisRequest request;
    OfisUpdateRequest updateRequest;
    Ofis ofis;
    private OfisService ofisService;
    private OfisRepository ofisRepository;

    @Before
    public void setUp() {
        ofis = createOfis();
        request = createOfisRequest(1l);
        updateRequest = createUpdateOfisRequest();
        Mappers mappers = mock(Mappers.class);
        ofisRepository = mock(OfisRepository.class);
        EmlakciRepository emlakciRepository = mock(EmlakciRepository.class);
        ofisService = new OfisService(ofisRepository,mappers,emlakciRepository);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_save_with_null_name(){
        request.setName(null);
        ofisService.save(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_save_with_null_emlakci_id(){
        request.setEmlakciId(null);
        ofisService.save(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_with_null_id(){
        ofisService.update(null,null);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_with_zero_id(){
        ofisService.update(0l,null);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_with_null_name(){
        updateRequest.setName(null);
        Mockito.when(ofisRepository.findById(anyLong())).thenReturn(Optional.of(ofis));
        ofisService.update(1l,updateRequest);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_delete_with_null_id(){
        ofisService.delete(null);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_delete_with_zero_id(){
        ofisService.delete(0l);
    }

}
