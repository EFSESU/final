package com.example.demo.service;

import com.example.demo.entity.Deliver;
import com.example.demo.repository.DeliverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliverService {
    private final DeliverRepository deliverRepository;

    @Autowired
    public DeliverService(DeliverRepository deliverRepository) {
        this.deliverRepository = deliverRepository;
    }

    public List<Deliver> getAllDelivers() {
        return deliverRepository.findAll();
    }

    public Deliver getDeliverById(String workId) {
        return deliverRepository.findById(workId).orElse(null);
    }

    public Deliver saveDeliver(Deliver deliver) {
        return deliverRepository.save(deliver);
    }

    public void deleteDeliver(String workId) {
        deliverRepository.deleteById(workId);
    }
}
