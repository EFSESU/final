package com.example.demo.controller;

import com.example.demo.entity.Deliver;
import com.example.demo.service.DeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliver")
@CrossOrigin
public class DeliverController {
    private final DeliverService deliverService;

    @Autowired
    public DeliverController(DeliverService deliverService) {
        this.deliverService = deliverService;
    }

    @GetMapping
    public List<Deliver> getAll() {
        return deliverService.getAllDelivers();
    }

    @GetMapping("/{workId}")
    public Deliver getById(@PathVariable String workId) {
        return deliverService.getDeliverById(workId);
    }

    @PostMapping
    public Deliver create(@RequestBody Deliver deliver) {
        return deliverService.saveDeliver(deliver);
    }

    @PutMapping("/{workID}")  // 明确路径变量
    public Deliver update(@PathVariable String workID, @RequestBody Deliver deliver) {
        deliver.setWorkId(workID);
        return deliverService.saveDeliver(deliver);
    }

    @DeleteMapping("/{workID}")
    public void delete(@PathVariable String workID) {
        deliverService.deleteDeliver(workID);
    }
}
