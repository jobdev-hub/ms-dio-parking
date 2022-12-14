package com.jobdev.msdioparking.web.controller;

import com.jobdev.msdioparking.web.dto.ParkingDTO;
import com.jobdev.msdioparking.logic.service.ParkingService;
import com.jobdev.msdioparking.web.mapper.ParkingMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@SecurityRequirement(name = "basicAuth")
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
    public ResponseEntity<ParkingDTO> findById(@PathVariable UUID id) {
        var parking = parkingService.findById(id);
        var responseBody = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping("checkin")
    public ResponseEntity<ParkingDTO> checkIn(@RequestBody ParkingDTO parkingDTO) {
        var requestBody = parkingMapper.toParking(parkingDTO);
        var responseBody = parkingMapper.toParkingDTO(parkingService.checkIn(requestBody));
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PutMapping("checkout/{id}")
    public ResponseEntity<ParkingDTO> checkOut(@PathVariable UUID id) {
        var responseBody = parkingMapper.toParkingDTO(parkingService.checkOut(id));
        return ResponseEntity.ok(responseBody);
    }

    @PutMapping("{id}")
    public ResponseEntity<ParkingDTO> update(@PathVariable UUID id, @RequestBody ParkingDTO parkingDTO) {
        var requestBody = parkingMapper.toParking(parkingDTO);
        var responseBody = parkingMapper.toParkingDTO(parkingService.update(id, requestBody));
        return ResponseEntity.ok(responseBody);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        parkingService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
