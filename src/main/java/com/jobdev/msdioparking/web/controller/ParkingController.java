package com.jobdev.msdioparking.web.controller;

import com.jobdev.msdioparking.domain.dto.ParkingDTO;
import com.jobdev.msdioparking.domain.entity.Parking;
import com.jobdev.msdioparking.service.ParkingService;
import com.jobdev.msdioparking.web.mapper.ParkingMapper;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<ParkingDTO> findAll() {
        List<Parking> parkingList = parkingService.findAll();
        return parkingMapper.toParkingDTOList(parkingList);
    }
}
