package kaem0n.u5w1d5.dao;

import kaem0n.u5w1d5.entities.Building;
import kaem0n.u5w1d5.entities.Station;
import kaem0n.u5w1d5.entities.StationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationDAO extends JpaRepository<Station, Long> {

    boolean existsByDescriptionAndBuilding(String description, Building building);

    long countByTypeAndBuilding(StationType type, Building building);

    @Query("SELECT s FROM Station s, Building b WHERE s.type = :type AND b.city = :city AND s.building = b")
    List<Station> findByTypeAndCity(StationType type, String city);
}
