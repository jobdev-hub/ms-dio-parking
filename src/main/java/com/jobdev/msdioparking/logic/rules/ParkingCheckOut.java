package com.jobdev.msdioparking.logic.rules;

import com.jobdev.msdioparking.domain.entity.Parking;
import com.jobdev.msdioparking.domain.setting.ParkingValueSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class ParkingCheckOut {
    public static final int ONE_HOUR = 60;
    public static final int TWENTY_FOUR_HOUR = 24 * ONE_HOUR;

    private static ParkingValueSetting parkingValueSetting;

    @Autowired
    public ParkingCheckOut(ParkingValueSetting parkingValueSetting) {
        ParkingCheckOut.parkingValueSetting = parkingValueSetting;
    }

    private static Double calculateBill(LocalDateTime entryDate, LocalDateTime exitDate) {
        long minutes = entryDate.until(exitDate, ChronoUnit.MINUTES);
        Double bill = 0.0;
        if (minutes <= ONE_HOUR) {
            return parkingValueSetting.getOneHourValue();
        }
        if (minutes <= TWENTY_FOUR_HOUR) {
            bill = parkingValueSetting.getOneHourValue();
            int hours = (int) (minutes / ONE_HOUR);
            System.out.println(hours);
            for (int i = 0; i < hours; i++) {
                bill += parkingValueSetting.getAdditionalPerHourValue();
            }
            return bill;
        }
        int days = (int) (minutes / TWENTY_FOUR_HOUR);
        for (int i = 0; i < days; i++) {
            bill += parkingValueSetting.getDayValue();
        }
        return bill;
    }

    public static Double calculateBill(Parking parking) {
        return calculateBill(parking.getEntryDate(), parking.getExitDate());
    }
}
