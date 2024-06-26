package kaem0n.u5w1d5.dao;

import kaem0n.u5w1d5.entities.Station;
import kaem0n.u5w1d5.entities.StationType;
import kaem0n.u5w1d5.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StationService {
    @Autowired
    private StationDAO sd;

    public void save(Station station) {
        long sameTypeStations = sd.countByTypeAndBuilding(station.getType(), station.getBuilding());
        if (!sd.existsByDescriptionAndBuilding(station.getDescription(), station.getBuilding()) && sameTypeStations < 3) {
            sd.save(station);
            System.out.println("New " + station.getType().toString().toLowerCase() + " station in " + station.getBuilding().getName() +
                    " (" + station.getBuilding().getCity() + ") building registered on db.");
        }
    }

    public Station findById(long id) {
        return sd.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(long id) {
        Station found = this.findById(id);
        sd.delete(found);
        System.out.println("Station ID " + found.getId() + " deleted from db.");
    }

    public List<Station> findByTypeAndCity(StationType type, String city) {
        return sd.findByTypeAndCity(type, city);
    }
}
