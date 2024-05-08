package com.github.supercodingspring.repository.reservations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface ReservationJpaRepository extends JpaRepository<Reservation, Integer> {


    @Query("SELECT new com.github.supercodingspring.repository.reservations.FlightPriceAndCharge(f.flightPrice, f.charge) " +
           "FROM Reservation r " +
           "JOIN r.passenger p " +
           "JOIN r.airlineTicket a " +
           "JOIN a.flightList f " +
           "WHERE p.user.userId = :userId ")
    List<FlightPriceAndCharge> findFlightPriceAndCharge(Integer userId);

    //아래 내용부터 과제 내용인데
    //솔직히 감이 오지 않아서 참고를 했습니다.

    @Query("SELECT DISTINCT f.arrivalLocation FROM Reservation r " +
            "JOIN r.passenger p " +
            "JOIN r.airlineTicket a " +
            "JOIN a.flightList f " +
            "WHERE p.user.userName = :username ")
    Set<String> findArrivalLocationByUsername(String username);
}
