package com.irctc.irctc_backend.controller;

import com.irctc.irctc_backend.entity.Train;
import com.irctc.irctc_backend.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping("/add")
    public Train addTrain(@RequestBody Train train){
        return trainService.addTrain(train);
    }

    @GetMapping("/all")
    public List<Train> getAllTrains(){
        return trainService.getAllTrains();
    }

    @GetMapping("/search")
    public List<Train> searchTrain(@RequestParam String source,
                                   @RequestParam String destination){
        return trainService.searchTrain(source,destination);
    }
}