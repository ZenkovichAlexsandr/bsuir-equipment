package by.bsuir.psp.utlik.transformer;

import by.bsuir.psp.utlik.dto.EquipmentDto;
import by.bsuir.psp.utlik.model.Equipment;
import org.springframework.stereotype.Component;

/**
 * @author alexsandr
 * @since 14.04.17.
 */
@Component
public class EquipmentTransformer implements DtoTransformer<EquipmentDto, Equipment> {

    @Override
    public EquipmentDto transform(Equipment entity) {
        return EquipmentDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .details(entity.getDetails())
                .description(entity.getDescription())
                .status(entity.getStatus())
                .price(entity.getPrice())
                .build();
    }

    @Override
    public Equipment transform(EquipmentDto dto) {
        return Equipment.builder()
                .name(dto.getName())
                .details(dto.getDetails())
                .description(dto.getDescription())
                .status(dto.getStatus())
                .price(dto.getPrice())
                .build();
    }
}
