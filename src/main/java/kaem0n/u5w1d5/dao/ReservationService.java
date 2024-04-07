package kaem0n.u5w1d5.dao;

import kaem0n.u5w1d5.entities.Reservation;
import kaem0n.u5w1d5.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    private ReservationDAO rd;

    public void save(Reservation reservation) {
        List<Reservation> stationCheck = reservation.getStation().getReservations().stream()
                .filter(el -> el.getReservationDay() != reservation.getReservationDay()).toList();
        List<Reservation> userCheck = reservation.getUser().getReservations().stream()
                .filter(el -> el.getReservationDay() != reservation.getReservationDay()).toList();
        if (stationCheck.isEmpty() && userCheck.isEmpty()) {
            rd.save(reservation);
            System.out.println("Station ID " + reservation.getStation().getId() + " on building " +
                    reservation.getStation().getBuilding().getName() + " (" +
                    reservation.getStation().getBuilding().getCity() + ") reserved for user " +
                    reservation.getUser().getUsername() + " on date " + reservation.getReservationDay() + ".");
        } else if (!stationCheck.isEmpty() && userCheck.isEmpty()) {
            System.out.println("Station ID " + reservation.getStation().getId() + " is unavailable on date " + reservation.getReservationDay() + ".");
        } else if (stationCheck.isEmpty()) {
            System.out.println("User " + reservation.getUser().getUsername() + " has another reservation for this day.");
        } else {
            System.out.println("Reservation already taken.");
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
