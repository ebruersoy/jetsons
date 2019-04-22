package com.myway.jetsons.controller;

import com.myway.jetsons.dto.OfisRequest;
import com.myway.jetsons.dto.OfisUpdateRequest;
import com.myway.jetsons.model.Ofis;
import com.myway.jetsons.service.OfisService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ebru Göksal
 */
@Controller
@RequestMapping(path = "ofis/v1")
public class OfisController {

    OfisService ofisService;

    public OfisController(OfisService ofisService) {
        this.ofisService = ofisService;
    }

    @PostMapping
    public @ResponseBody
    Ofis save(@RequestBody OfisRequest ofisRequest){
        return ofisService.save(ofisRequest);
    }

    @PutMapping(path = "{id}")
    public @ResponseBody
    Ofis update(@PathVariable("id")Long id, @RequestBody OfisUpdateRequest ofisRequest) {
        return ofisService.update(id, ofisRequest);
    }

    @DeleteMapping(path ="{id}")
    public @ResponseBody
    ResponseEntity remove(@PathVariable("id") Long id){
        ofisService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted Successfully");
    }

    @GetMapping
    public @ResponseBody
    List<Ofis> findAll(){
        return ofisService.findAll();
    }

    @GetMapping(path ="{id}")
    public @ResponseBody
    Ofis findById(@PathVariable("id") Long id){
        return ofisService.findById(id);
    }

}
