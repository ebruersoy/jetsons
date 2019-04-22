package com.myway.jetsons.service;

import com.myway.jetsons.UnitTest;
import com.myway.jetsons.dto.EmlakciRequest;
import com.myway.jetsons.dto.Mappers;
import com.myway.jetsons.exception.InvalidRequestException;
import com.myway.jetsons.model.Emlakci;
import com.myway.jetsons.repository.EmlakciRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.Optional;

import static com.myway.jetsons.TestDataHelper.createEmlakci;
import static com.myway.jetsons.TestDataHelper.createEmlakciRequest;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;

/**
 * @author Ebru Göksal
 */
@Category(UnitTest.class)
public class EmlakciServiceUnitTest {
    EmlakciRequest request;
    Emlakci emlakci;
    private EmlakciService emlakciService;
    private EmlakciRepository emlakciRepository;

    @Before
    public void setUp() {
        emlakci = createEmlakci();
        request = createEmlakciRequest();
        Mappers mappers = mock(Mappers.class);
        emlakciRepository = mock(EmlakciRepository.class);
        emlakciService = new EmlakciService(emlakciRepository,mappers);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_save_with_null_name(){
        request.setName(null);
        emlakciService.save(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_with_null_id(){
        emlakciService.update(null,null);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_with_zero_id(){
        emlakciService.update(0l,null);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_with_null_name(){
        request.setName(null);
        Mockito.when(emlakciRepository.findById(anyLong())).thenReturn(Optional.of(emlakci));
        emlakciService.update(1l,request);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_delete_with_null_id(){
        emlakciService.delete(null);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_delete_with_zero_id(){
        emlakciService.delete(0l);
    }


    @Test(expected = InvalidRequestException.class)
    public void should_not_update_vitrin_hakki_with_null_id(){
        emlakciService.updateVitrinHakki(null,0);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_vitrin_hakki_with_zero_id(){
        emlakciService.updateVitrinHakki(0l,0);
    }

}
