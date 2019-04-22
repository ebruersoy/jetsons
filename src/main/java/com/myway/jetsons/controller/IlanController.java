package com.myway.jetsons.controller;

import com.myway.jetsons.dto.IlanRequest;
import com.myway.jetsons.model.Ilan;
import com.myway.jetsons.service.IlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ebru Göksal
 */
@Controller
@RequestMapping(path = "ilan/v1")
public class IlanController {

    IlanService ilanService;

    public IlanController(IlanService ilanService) {
        this.ilanService = ilanService;
    }

    @PostMapping
    public @ResponseBody
    Ilan save(@RequestBody IlanRequest ilanRequest){
        return ilanService.save(ilanRequest);
    }

    @PutMapping(path = "{id}")
    public @ResponseBody
    Ilan update(@PathVariable("id")Long id, @RequestBody IlanRequest ilanRequest) {
        return ilanService.update(id, ilanRequest);
    }

    @DeleteMapping(path ="{id}")
    public @ResponseBody
    ResponseEntity remove(@PathVariable("id") Long id){
        ilanService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted Successfully");
    }

    @GetMapping
    public @ResponseBody
    List<Ilan> findAll(){
        return ilanService.findAll();
    }

    @GetMapping(path ="{id}")
    public @ResponseBody
    Ilan findById(@PathVariable("id") Long id){
        return ilanService.findById(id);
    }


    @PatchMapping(path ="{id}/vitrinHakki")
    public @ResponseBody ResponseEntity updateVitrinHakki(@PathVariable("id") Long id, @RequestBody boolean vitrinHakki) {
        Ilan ilan = ilanService.updateVitrinHakki(id, vitrinHakki);
        return ResponseEntity.status(HttpStatus.OK).body(ilan);
    }
}
