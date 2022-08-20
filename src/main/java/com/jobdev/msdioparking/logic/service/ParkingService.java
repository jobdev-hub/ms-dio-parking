package com.jobdev.msdioparking.logic.service;

import com.jobdev.msdioparking.domain.entity.Parking;
import com.jobdev.msdioparking.domain.repository.ParkingRepository;
import com.jobdev.msdioparking.logic.exception.ParkingNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static com.jobdev.msdioparking.logic.rules.ParkingCheckOut.calculateBill;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }

    public Parking findById(UUID id) {
        return parkingRepository.findById(id).orElseThrow(() -> new ParkingNotFoundException(id));
    }

    public Parking checkIn(Parking parking) {
        parking.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parking);
        return parking;
    }

    public Parking checkOut(UUID id) {
        var parking = findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(calculateBill(parking));
        parkingRepository.save(parking);
        return parking;
    }

    public Parking update(UUID id, Parking requestBody) {
        var parking = findById(id);
        mountUpdate(requestBody, parking);
        parkingRepository.save(parking);
        return parking;
    }

    public void deleteById(UUID id) {
        findById(id);
        parkingRepository.deleteById(id);
    }

    private static void mountUpdate(Parking requestBody, Parking parking) {
        if (requestBody.getColor() != null && !requestBody.getColor().isEmpty()) {
            parking.setColor(requestBody.getColor());
        }
        if (requestBody.getState() != null && !requestBody.getState().isEmpty()) {
            parking.setState(requestBody.getState());
        }
        if (requestBody.getModel() != null && !requestBody.getModel().isEmpty()) {
            parking.setModel(requestBody.getModel());
        }
        if (requestBody.getLicense() != null && !requestBody.getLicense().isEmpty()) {
            parking.setLicense(requestBody.getLicense());
        }
    }

}
