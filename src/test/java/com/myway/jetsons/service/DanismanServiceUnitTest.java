package com.myway.jetsons.service;

import com.myway.jetsons.UnitTest;
import com.myway.jetsons.dto.DanismanRequest;
import com.myway.jetsons.dto.Mappers;
import com.myway.jetsons.exception.InvalidRequestException;
import com.myway.jetsons.model.Danisman;
import com.myway.jetsons.repository.DanismanRepository;
import com.myway.jetsons.repository.OfisRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.Optional;

import static com.myway.jetsons.TestDataHelper.createDanisman;
import static com.myway.jetsons.TestDataHelper.createDanismanRequest;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;

/**
 * @author Ebru Göksal
 */
@Category(UnitTest.class)
public class DanismanServiceUnitTest {
    DanismanRequest request;
    Danisman danisman;
    private DanismanService danismanService;
    private DanismanRepository danismanRepository;

    @Before
    public void setUp() {
        danisman = createDanisman();
        request = createDanismanRequest(1l);
        Mappers mappers = mock(Mappers.class);
        danismanRepository = mock(DanismanRepository.class);
        OfisRepository ofisRepository = mock(OfisRepository.class);
        danismanService = new DanismanService(danismanRepository,mappers,ofisRepository);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_save_with_null_name(){
        request.setName(null);
        danismanService.save(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_save_with_null_ofis_id(){
        request.setOfisId(null);
        danismanService.save(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_with_null_id(){
        danismanService.update(null,null);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_with_zero_id(){
        danismanService.update(0l,null);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_with_null_name(){
        request.setName(null);
        Mockito.when(danismanRepository.findById(anyLong())).thenReturn(Optional.of(danisman));
        danismanService.update(1l,request);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_with_null_ofis_id(){
        request.setOfisId(null);
        Mockito.when(danismanRepository.findById(anyLong())).thenReturn(Optional.of(danisman));
        danismanService.update(1l,request);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_delete_with_null_id(){
        danismanService.delete(null);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_delete_with_zero_id(){
        danismanService.delete(0l);
    }

}
