package kaem0n.u5w1d5.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "stations")
public class Station {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "station_id")
    private long id;
    @Enumerated(EnumType.STRING)
    private StationType type;
    private String description;
    @Column(name = "max_capacity")
    private int maxCapacity;
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;
    @OneToMany(mappedBy = "station", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    public Station(StationType type, String description, int maxCapacity, Building building) {
        this.type = type;
        this.description = description;
        this.maxCapacity = maxCapacity;
        this.building = building;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", type=" + type +
                ", description='" + description + '\'' +
                ", maxCapacity=" + maxCapacity +
                ", building=" + building +
                '}';
    }
}
