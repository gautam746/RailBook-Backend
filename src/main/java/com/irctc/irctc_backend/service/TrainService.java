package com.irctc.irctc_backend.service;

import com.irctc.irctc_backend.entity.Train;
import com.irctc.irctc_backend.repository.TrainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    public Train addTrain(Train train){
        return trainRepository.save(train);
    }

    public List<Train> getAllTrains(){
        return trainRepository.findAll();
    }

    public List<Train> searchTrain(String source,String destination){
        return trainRepository.findBySourceIgnoreCaseAndDestinationIgnoreCase(source,destination);
    }
}