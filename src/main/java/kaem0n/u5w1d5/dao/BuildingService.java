package kaem0n.u5w1d5.dao;

import kaem0n.u5w1d5.entities.Building;
import kaem0n.u5w1d5.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {
    @Autowired
    private BuildingDAO bd;

    public void save(Building building) {
        if (!bd.existsByNameAndCity(building.getName(), building.getCity())) {
            bd.save(building);
            System.out.println("Building " + building.getName() + " based in " + building.getCity() + " saved on db.");
        }
    }

    public Building findById(long id) {
        return bd.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void delete(long id) {
        Building found = this.findById(id);
        bd.delete(found);
        System.out.println("Building " + found.getName() + " (ID " + found.getId() + ") deleted from db.");
    }
}
