package by.bsuir.psp.utlik.service;

import by.bsuir.psp.utlik.dto.EquipmentDto;
import by.bsuir.psp.utlik.dto.PageDto;
import org.springframework.data.domain.Pageable;

/**
 * @author alexsandr
 * @since 14.04.17.
 */
public interface EquipmentService {
    PageDto<EquipmentDto> findAll(Pageable pageable);
    EquipmentDto create(EquipmentDto dto);
    void delete(Long id);
    EquipmentDto update(EquipmentDto dto, Long id);
}
