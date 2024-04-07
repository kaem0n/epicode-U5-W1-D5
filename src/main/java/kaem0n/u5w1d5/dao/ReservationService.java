package kaem0n.u5w1d5.dao;

import kaem0n.u5w1d5.entities.Reservation;
import kaem0n.u5w1d5.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
public class ReservationService {
    @Autowired
    private ReservationDAO rd;

    public void save(Reservation reservation) {
        List<Reservation> conflictCheck1 = rd.reservationAlreadyMadeCheck(reservation.getUser(), reservation.getStation(), reservation.getReservationDay());
        List<Reservation> conflictCheck2 = rd.userAlreadyReservedOnDateCheck(reservation.getUser(), reservation.getReservationDay());
        List<Reservation> conflictCheck3 = rd.stationAlreadyReservedOnDateCheck(reservation.getStation(), reservation.getReservationDay());
        if (reservation.getReservationDay().isBefore(LocalDate.now())) System.out.println("Invalid date. You can't reserve on past days.");
        else if (Objects.equals(reservation.getReservationDay(), LocalDate.now())) System.out.println("Invalid date. You can't reserve on today's date.");
        else {
            if (!conflictCheck1.isEmpty()) System.out.println("Reservation already taken.");
            else if (!conflictCheck2.isEmpty()) System.out.println("User " + reservation.getUser().getUsername() + " has another reservation for this day.");
            else if (!conflictCheck3.isEmpty()) System.out.println("Station ID " + reservation.getStation().getId() + " is unavailable on date " + reservation.getReservationDay() + ".");
            else {
                rd.save(reservation);
                System.out.println("Station ID " + reservation.getStation().getId() + " on building " +
                        reservation.getStation().getBuilding().getName() + " (" +
                        reservation.getStation().getBuilding().getCity() + ") reserved for user " +
                        reservation.getUser().getUsername() + " on date " + reservation.getReservationDay() + ".");
            }
        }
    }

    public Reservation findById(long id) {
        return rd.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(long id) {
        Reservation found = this.findById(id);
        rd.delete(found);
        System.out.println("Reservation ID " + found.getId() + " deleted from db.");
    }
}
