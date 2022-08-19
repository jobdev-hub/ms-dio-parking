package com.jobdev.msdioparking.service;

import com.jobdev.msdioparking.domain.entity.Parking;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ParkingService {
    private static Map<String, Parking> parkingMap = new HashMap();

    static {
        var id = getUUID();
        Parking parking = new Parking(id, "DMS-1111", "SC", "CELTA", "PRETO", null, null, null);
        parkingMap.put(id, parking);
    }

    public List<Parking> findAll() {
        return new ArrayList<>(parkingMap.values());
    }

    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
