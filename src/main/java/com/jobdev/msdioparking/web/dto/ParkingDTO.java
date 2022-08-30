package com.jobdev.msdioparking.web.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParkingDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;


    private String license;
    private String state;
    private String model;
    private String color;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime entryDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime exitDate;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double bill;

}
