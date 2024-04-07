package kaem0n.u5w1d5.dao;

import kaem0n.u5w1d5.entities.Building;
import kaem0n.u5w1d5.entities.Station;
import kaem0n.u5w1d5.entities.StationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationDAO extends JpaRepository<Station, Long> {

    boolean existsByDescriptionAndBuilding(String description, Building building);

    long countByTypeAndBuilding(StationType type, Building building);
}
