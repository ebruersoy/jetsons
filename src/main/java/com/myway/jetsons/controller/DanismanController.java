package com.myway.jetsons.controller;

import com.myway.jetsons.dto.DanismanRequest;
import com.myway.jetsons.model.Danisman;
import com.myway.jetsons.service.DanismanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ebru Göksal
 */
@Controller
@RequestMapping(path = "danisman/v1")
public class DanismanController {

    DanismanService danismanService;

    public DanismanController(DanismanService danismanService) {
        this.danismanService = danismanService;
    }

    @PostMapping
    public @ResponseBody
    Danisman save(@RequestBody DanismanRequest danismanRequest){
        return danismanService.save(danismanRequest);
    }

    @PutMapping(path = "{id}")
    public @ResponseBody
    Danisman update(@PathVariable("id")Long id, @RequestBody DanismanRequest danismanRequest) {
        return danismanService.update(id, danismanRequest);
    }

    @DeleteMapping(path ="{id}")
    public @ResponseBody
    ResponseEntity remove(@PathVariable("id") Long id){
        danismanService.delete(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Deleted Successfully");
    }

    @GetMapping
    public @ResponseBody
    List<Danisman> findAll(){
        return danismanService.findAll();
    }

    @GetMapping(path ="{id}")
    public @ResponseBody
    Danisman findById(@PathVariable("id") Long id){
        return danismanService.findById(id);
    }

}
