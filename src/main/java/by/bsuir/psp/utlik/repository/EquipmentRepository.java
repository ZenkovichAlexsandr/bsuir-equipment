package by.bsuir.psp.utlik.repository;

import by.bsuir.psp.utlik.model.Equipment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author alexsandr
 * @since 14.04.17.
 */
@Repository
public interface EquipmentRepository extends PagingAndSortingRepository<Equipment, Long>,
        CrudRepository<Equipment, Long> {
}
