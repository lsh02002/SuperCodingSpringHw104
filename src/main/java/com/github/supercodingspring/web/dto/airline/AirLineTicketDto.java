package com.github.supercodingspring.web.dto.airline;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class AirLineTicketDto {
    private Integer flightId;
    private LocalDateTime departAt;
    private LocalDateTime arrivalAt;
    private String departureLocation;
    private String arrivalLocation;
}
