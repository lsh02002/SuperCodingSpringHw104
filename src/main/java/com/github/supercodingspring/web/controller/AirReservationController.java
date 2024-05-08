package com.github.supercodingspring.web.controller;

import com.github.supercodingspring.repository.airlineTicket.AirlineTicket;
import com.github.supercodingspring.service.AirReservationService;
import com.github.supercodingspring.web.dto.airline.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/v1/api/air-reservation")
@RequiredArgsConstructor
@Slf4j
public class AirReservationController {

    private final AirReservationService airReservationService;

    @ApiOperation("선호하는 ticket 탐색")
    @GetMapping("/tickets")
    public TicketResponse findAirlineTickets(
            @ApiParam(name = "user-Id", value = "유저 ID", example = "1") @RequestParam("user-Id") Integer userId,
            @ApiParam(name = "airline-ticket-type", value = "항공권 타입", example = "왕복|편도") @RequestParam("airline-ticket-type") String ticketType )
    {
            List<Ticket> tickets = airReservationService.findUserFavoritePlaceTickets(userId, ticketType);
            return new TicketResponse(tickets);
    }
    @ApiOperation("User와 Ticket Id로 예약 진행")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/reservations")
    public ReservationResult makeReservation(@RequestBody ReservationRequest reservationRequest){
            return airReservationService.makeReservation(reservationRequest);
    }

    @ApiOperation("userId의 예약한 항공편과 수수료 총합")
    @GetMapping("/users-sum-price")
    public Double findUserFlightSumPrice(
            @ApiParam(name = "user-Id", value = "유저 ID", example = "1") @RequestParam("user-id") Integer userId
    )
    {
        Double sum = airReservationService.findUserFlightSumPrice(userId);
        return sum;
    }

    //아래부터 과제 내용입니다.

    @ApiOperation("항공권 타입(편도/왕복) 조회와 페이지네이션")
    @GetMapping("/flight-pageable")
    public Page<AirLineTicketDto> findFlightPageable(@ApiParam(name = "type", value = "항권권 타입", example = "편도") @RequestParam("type") String type, Pageable pageable){
        return airReservationService.findByTicketType(type, pageable);
    }

    @ApiOperation("유저가 예약한 항공편들의 목적지 조회")
    @GetMapping("username-arrival-location")
    public List<String> findUsernameArrivalLocation(@ApiParam(name = "type", value = "항권권 타입", example = "편도") @RequestParam("username") String username){
        Set<String> arrivalLocations = airReservationService.findArrivalLocationByUsername(username);
        return List.copyOf(arrivalLocations);
    }
}
