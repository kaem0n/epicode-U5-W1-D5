package kaem0n.u5w1d5.dao;

import kaem0n.u5w1d5.entities.Station;
import kaem0n.u5w1d5.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationService {
    @Autowired
    private StationDAO sd;

    public void save(Station station) {
        if (!sd.existsByDescriptionAndBuilding(station.getDescription(), station.getBuilding())) {
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
}
