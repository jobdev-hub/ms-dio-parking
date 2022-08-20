package com.jobdev.msdioparking.logic.service;

import com.jobdev.msdioparking.domain.entity.Parking;
import com.jobdev.msdioparking.logic.exception.ParkingNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static com.jobdev.msdioparking.logic.rules.ParkingCheckOut.calculateBill;

@Service
public class ParkingService {
    private static final Map<String, Parking> parkingMap = new HashMap<>();

    static {
        var id1 = getUUID();
        var id2 = getUUID();
        var id3 = getUUID();

        //generate entryDate1 to reduce 40minutes from current time
        var entryDate1 = LocalDateTime.now().minusMinutes(40);

        //generate entryDate2 to reduce 3hours and 40minutes from current time
        var entryDate2 = LocalDateTime.now().minusHours(3).minusMinutes(40);

        //generate entryDate3 to reduce 5days from current time
        var entryDate3 = LocalDateTime.now().minusDays(5);

        var p1 = new Parking(id1, "DMS-1111", "SC", "CELTA", "PRETO", entryDate1, null, null);
        var p2 = new Parking(id2, "TTL-1111", "SP", "GOL", "AZUL", entryDate2, null, null);
        var p3 = new Parking(id3, "TOQ-1111", "GO", "PRISMA", "BRANCO", entryDate3, null, null);

        parkingMap.put(id1, p1);
        parkingMap.put(id2, p2);
        parkingMap.put(id3, p3);
    }

    public List<Parking> findAll() {
        return new ArrayList<>(parkingMap.values());
    }

    public Parking findById(String id) {
        var parking = parkingMap.get(id);

        if (parking == null) {
            throw new ParkingNotFoundException(id);
        }

        return parking;
    }

    public Parking checkIn(Parking parking) {
        String id = getUUID();
        parking.setId(id);
        parking.setEntryDate(LocalDateTime.now());
        parkingMap.put(id, parking);
        return parking;
    }

    public Parking checkOut(String id) {
        var parking = parkingMap.get(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill(calculateBill(parking));
        parkingMap.replace(id, parking);
        return parking;
    }

    public Parking update(String id, Parking requestBody) {
        var parking = findById(id);
        parking.setColor(requestBody.getColor());
        parking.setState(requestBody.getState());
        parking.setModel(requestBody.getModel());
        parking.setLicense(requestBody.getLicense());
        parkingMap.replace(id, parking);
        return parking;
    }

    public void deleteById(String id) {
        findById(id);
        parkingMap.remove(id);
    }


    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
