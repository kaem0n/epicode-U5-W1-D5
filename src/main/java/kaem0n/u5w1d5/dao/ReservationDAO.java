package kaem0n.u5w1d5.dao;

import kaem0n.u5w1d5.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationDAO extends JpaRepository<Reservation, Long> {
}
