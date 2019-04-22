package com.myway.jetsons.service;

import com.myway.jetsons.UnitTest;
import com.myway.jetsons.dto.IlanRequest;
import com.myway.jetsons.dto.Mappers;
import com.myway.jetsons.exception.InvalidRequestException;
import com.myway.jetsons.exception.PreconditionFailedException;
import com.myway.jetsons.model.Ilan;
import com.myway.jetsons.repository.DanismanRepository;
import com.myway.jetsons.repository.IlanRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.myway.jetsons.TestDataHelper.createIlan;
import static com.myway.jetsons.TestDataHelper.createIlanRequest;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;

/**
 * @author Ebru Göksal
 */
@Category(UnitTest.class)
public class IlanServiceUnitTest {
    IlanRequest request;
    Ilan ilan;
    private IlanService ilanService;
    private IlanRepository ilanRepository;

    @Before
    public void setUp() {
        ilan = createIlan();
        request = createIlanRequest(1l);
        Mappers mappers = mock(Mappers.class);
        DanismanRepository danismanRepository = mock(DanismanRepository.class);
        ilanRepository = mock(IlanRepository.class);
        ilanService = new IlanService(ilanRepository, danismanRepository,mappers,null);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_save_with_null_detail(){
        request.setDetail(null);
        ilanService.save(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_save_with_null_danisman_id(){
        request.setDanismanId(null);
        ilanService.save(request);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_with_null_id(){
        ilanService.update(null,null);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_with_zero_id(){
        ilanService.update(0l,null);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_with_null_detil(){
        request.setDetail(null);
        Mockito.when(ilanRepository.findById(anyLong())).thenReturn(Optional.of(ilan));
        ilanService.update(1l,request);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_with_null_danisman_id(){
        request.setDanismanId(null);
        Mockito.when(ilanRepository.findById(anyLong())).thenReturn(Optional.of(ilan));
        ilanService.update(1l,request);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_delete_with_null_id(){
        ilanService.delete(null);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_delete_with_zero_id(){
        ilanService.delete(0l);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_vitrin_hakki_with_null_id(){
        ilanService.updateVitrinHakki(null,true);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_vitrin_hakki_with_zero_id(){
        ilanService.updateVitrinHakki(0l,true);
    }

    @Test(expected = PreconditionFailedException.class)
    public void should_not_update_vitrin_hakki_with_zero_emlakci_vitrin_hakki(){
        ilan.setId(1l);
        Mockito.when(ilanRepository.findById(anyLong())).thenReturn(Optional.of(ilan));
        ilanService.updateVitrinHakki(1l,true);
    }

    @Test(expected = PreconditionFailedException.class)
    public void should_not_update_vitrin_hakki_with_three_ofis_ilan(){
        ilan.setId(1l);
        ilan.getOfis().getEmlakci().setVitrinHakki(4);
        ilan.getOfis().setId(1l);
        List<Ilan> ilanList = new ArrayList<>();
        Mockito.when(ilanRepository.findById(anyLong())).thenReturn(Optional.of(ilan));

        Mockito.when(ilanRepository.findByOfisId(anyLong())).then(invocation -> {
            ilan.setVitrinHakki(true);
            ilanList.add(ilan);
            ilanList.add(ilan);
            ilanList.add(ilan);
            return ilanList;
        });
        ilanService.updateVitrinHakki(1l,true);
    }

    @Test(expected = InvalidRequestException.class)
    public void should_not_update_vitrin_hakki_with_same(){
        ilan.setId(1l);
        ilan.setVitrinHakki(true);
        ilan.getOfis().getEmlakci().setVitrinHakki(4);
        ilan.getOfis().setId(1l);
        List<Ilan> ilanList = new ArrayList<>();
        Mockito.when(ilanRepository.findById(anyLong())).thenReturn(Optional.of(ilan));
        Mockito.when(ilanRepository.findByOfisId(anyLong())).then(invocation -> {
            ilan.setVitrinHakki(true);
            ilanList.add(ilan);
            ilanList.add(ilan);
            return ilanList;
        });
        ilanService.updateVitrinHakki(1l,true);
    }

}
