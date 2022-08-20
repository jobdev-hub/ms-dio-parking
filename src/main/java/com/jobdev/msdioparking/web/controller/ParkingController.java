package com.jobdev.msdioparking.web.controller;

import com.jobdev.msdioparking.domain.dto.ParkingDTO;
import com.jobdev.msdioparking.service.ParkingService;
import com.jobdev.msdioparking.web.mapper.ParkingMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {
    private final ParkingService parkingService;
    private final ParkingMapper parkingMapper;

    public ParkingController(ParkingService parkingService, ParkingMapper parkingMapper) {
        this.parkingService = parkingService;
        this.parkingMapper = parkingMapper;
    }

    @GetMapping
    @Operation(summary = "Get all parkings", description = "return list of parkings")
    public ResponseEntity<List<ParkingDTO>> findAll() {
        var parkingList = parkingService.findAll();
        var responseBody = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id) {
        var parking = parkingService.findById(id);
        var responseBody = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingDTO parkingDTO) {
        var requestBody = parkingMapper.toParking(parkingDTO);
        var responseBody = parkingMapper.toParkingDTO(parkingService.create(requestBody));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}
