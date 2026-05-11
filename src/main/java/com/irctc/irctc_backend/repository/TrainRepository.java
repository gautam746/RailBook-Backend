package com.irctc.irctc_backend.repository;

import com.irctc.irctc_backend.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Long> {

    List<Train> findBySourceIgnoreCaseAndDestinationIgnoreCase(String source,String destination);
}