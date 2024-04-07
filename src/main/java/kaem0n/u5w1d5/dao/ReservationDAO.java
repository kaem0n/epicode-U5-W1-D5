package kaem0n.u5w1d5.dao;

import kaem0n.u5w1d5.entities.Reservation;
import kaem0n.u5w1d5.entities.Station;
import kaem0n.u5w1d5.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationDAO extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r WHERE r.user = :user AND r.station = :station AND r.reservationDay = :date")
    List<Reservation> reservationAlreadyMadeCheck(User user, Station station, LocalDate date);

    @Query("SELECT r FROM Reservation r WHERE r.user = :user AND r.reservationDay = :date")
    List<Reservation> userAlreadyReservedOnDateCheck(User user, LocalDate date);

    @Query("SELECT r FROM Reservation r WHERE r.station = :station AND r.reservationDay = :date")
    List<Reservation> stationAlreadyReservedOnDateCheck(Station station, LocalDate date);
}
