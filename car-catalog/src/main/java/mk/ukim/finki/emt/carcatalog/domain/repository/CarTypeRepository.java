package mk.ukim.finki.emt.carcatalog.domain.repository;

import mk.ukim.finki.emt.carcatalog.domain.models.CarType;
import mk.ukim.finki.emt.carcatalog.domain.models.CarTypeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarTypeRepository extends JpaRepository<CarType, CarTypeId> {
}
