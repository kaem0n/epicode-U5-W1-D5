package kaem0n.u5w1d5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private long id;
    @Column(name = "reservation_day")
    private LocalDate reservationDay;
    private LocalDate expiration;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    public Reservation(LocalDate reservationDay, User user, Station station) {
        this.reservationDay = reservationDay;
        this.expiration = reservationDay.plusDays(1);
        this.user = user;
        this.station = station;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", reservationDay=" + reservationDay +
                ", expiration=" + expiration +
                ", user=" + user.getUsername() +
                ", station=" + station.getDescription() + " " + station.getBuilding().getName() + " (" + station.getBuilding().getCity() + ") " +
                '}';
    }
}
