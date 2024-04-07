package kaem0n.u5w1d5.dao;

import kaem0n.u5w1d5.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingDAO extends JpaRepository<Building, Long> {
    boolean existsByNameAndCity(String name, String city);
}
