package kaem0n.u5w1d5;

import kaem0n.u5w1d5.dao.BuildingService;
import kaem0n.u5w1d5.dao.ReservationService;
import kaem0n.u5w1d5.dao.StationService;
import kaem0n.u5w1d5.dao.UserService;
import kaem0n.u5w1d5.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Random;

@Component
public class Runner implements CommandLineRunner {
    @Autowired
    private BuildingService bs;
    @Autowired
    private ReservationService rs;
    @Autowired
    private StationService ss;
    @Autowired
    private UserService us;

    @Override
    public void run(String... args) throws Exception {
        System.out.println();

        // DATABASE FILL
        bs.save(new Building("EPICODE", "Rome"));
        bs.save(new Building("EPICODE", "Milan"));
        bs.save(new Building("EPICODE", "Berlin"));

        us.save(new User("iTzZMaaRiooZz", "Mario Rossi", "mario@rossi.it"));
        us.save(new User("bestDeveloperEU", "Giovanni Neri", "giovanni@neri.it"));
        us.save(new User("backendTrauma", "Marco Bianchi", "marco@bianchi.it"));

        for (int i = 1; i < 4; i++) {
            ss.save(new Station(StationType.PRIVATE, "Private 1", new Random().nextInt(4, 8), bs.findById(i)));
            ss.save(new Station(StationType.PRIVATE, "Private 2", new Random().nextInt(4, 8), bs.findById(i)));
            ss.save(new Station(StationType.PRIVATE, "Private 3", new Random().nextInt(4, 8), bs.findById(i)));
            ss.save(new Station(StationType.OPENSPACE, "Open Space 1", new Random().nextInt(10, 30), bs.findById(i)));
            ss.save(new Station(StationType.OPENSPACE, "Open Space 2", new Random().nextInt(10, 30), bs.findById(i)));
            ss.save(new Station(StationType.OPENSPACE, "Open Space 3", new Random().nextInt(10, 30), bs.findById(i)));
            ss.save(new Station(StationType.MEETING_ROOM, "Meeting Room 1", new Random().nextInt(10, 20), bs.findById(i)));
            ss.save(new Station(StationType.MEETING_ROOM, "Meeting Room 2", new Random().nextInt(10, 20), bs.findById(i)));
            ss.save(new Station(StationType.MEETING_ROOM, "Meeting Room 3", new Random().nextInt(10, 20), bs.findById(i)));
        }

        // TEST RESERVATIONS WITH EXPECTED OUTPUTS
        rs.save(new Reservation(LocalDate.parse("2020-01-01"), us.findById(1), ss.findById(1))); // CAN'T RESERVE IN THE PAST
        rs.save(new Reservation(LocalDate.now(), us.findById(1), ss.findById(1))); // CAN'T RESERVE TODAY
        rs.save(new Reservation(LocalDate.now().plusDays(1), us.findById(1), ss.findById(1))); // SAVED/ALREADY TAKEN
        rs.save(new Reservation(LocalDate.now().plusDays(1), us.findById(2), ss.findById(1))); // STATION NOT AVAILABLE
        rs.save(new Reservation(LocalDate.now().plusDays(1), us.findById(1), ss.findById(3))); // USER CAN'T RESERVE
        rs.save(new Reservation(LocalDate.now().plusDays(3), us.findById(1), ss.findById(1))); // SAVED/ALREADY TAKEN
        rs.save(new Reservation(LocalDate.now().plusDays(3), us.findById(1), ss.findById(2))); // USER CAN'T RESERVE
        rs.save(new Reservation(LocalDate.now().plusDays(2), us.findById(1), ss.findById(2))); // SAVED/ALREADY TAKEN
        rs.save(new Reservation(LocalDate.now().plusDays(2), us.findById(3), ss.findById(2))); // STATION NOT AVAILABLE
        rs.save(new Reservation(LocalDate.now().plusDays(2), us.findById(3), ss.findById(3))); // SAVED/ALREADY TAKEN

        System.out.println();
    }
}
