package com.jobdev.msdioparking.service;

import com.jobdev.msdioparking.domain.entity.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {
    private static final Map<String, Parking> parkingMap = new HashMap<>();

    static {
        var id1 = getUUID();
        var id2 = getUUID();
        var id3 = getUUID();

        var p1 = new Parking(id1, "DMS-1111", "SC", "CELTA", "PRETO", null, null, null);
        var p2 = new Parking(id2, "TTL-1111", "SP", "GOL", "AZUL", null, null, null);
        var p3 = new Parking(id3, "TOQ-1111", "GO", "PRISMA", "BRANCO", null, null, null);

        parkingMap.put(id1, p1);
        parkingMap.put(id2, p2);
        parkingMap.put(id3, p3);
    }

    public List<Parking> findAll() {
        return new ArrayList<>(parkingMap.values());
    }

    public Parking findById(String id) {
        return parkingMap.get(id);
    }

    public Parking create(Parking parking) {
        String id = getUUID();
        parking.setId(id);
        parking.setEntryDate(LocalDateTime.now());
        parkingMap.put(id, parking);
        return parking;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
