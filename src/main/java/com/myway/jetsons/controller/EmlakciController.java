package com.myway.jetsons.controller;

import com.myway.jetsons.dto.EmlakciRequest;
import com.myway.jetsons.model.Emlakci;
import com.myway.jetsons.service.EmlakciService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ebru Göksal
 */
@Controller
@RequestMapping(path = "emlakci/v1")
public class EmlakciController {

    EmlakciService emlakciService;

    public EmlakciController(EmlakciService emlakciService) {
        this.emlakciService = emlakciService;
    }

    @PostMapping
    public @ResponseBody
    Emlakci save(@RequestBody EmlakciRequest emlakciRequest){
        return emlakciService.save(emlakciRequest);
    }

    @PutMapping(path = "{id}")
    public @ResponseBody
    Emlakci update(@PathVariable("id")Long id, @RequestBody EmlakciRequest emlakciRequest) {
        return emlakciService.update(id, emlakciRequest);
    }

    @DeleteMapping(path ="{id}")
    public @ResponseBody
    ResponseEntity remove(@PathVariable("id") Long id){
        emlakciService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted Successfully");
    }

    @GetMapping
    public @ResponseBody
    List<Emlakci> findAll(){
        return emlakciService.findAll();
    }

    @GetMapping(path ="{id}")
    public @ResponseBody
    Emlakci findById(@PathVariable("id") Long id){
        return emlakciService.findById(id);
    }

    @PatchMapping(path ="{id}/vitrinHakki")
    public @ResponseBody ResponseEntity updateVitrinHakki(@PathVariable("id") Long id, @RequestBody Integer vitrinHakki) {
        Emlakci emlakci = emlakciService.updateVitrinHakki(id, vitrinHakki);
        return ResponseEntity.status(HttpStatus.OK).body(emlakci);
    }
}
