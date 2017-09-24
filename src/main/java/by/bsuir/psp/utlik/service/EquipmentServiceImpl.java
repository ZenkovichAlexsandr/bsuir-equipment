package by.bsuir.psp.utlik.service;

import by.bsuir.psp.utlik.dto.EquipmentDto;
import by.bsuir.psp.utlik.dto.PageDto;
import by.bsuir.psp.utlik.exceptions.BadRequestException;
import by.bsuir.psp.utlik.model.Equipment;
import by.bsuir.psp.utlik.repository.EquipmentRepository;
import by.bsuir.psp.utlik.transformer.EquipmentTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author alexsandr
 * @since 14.04.17.
 */
@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {
    private final EquipmentRepository repository;
    private final EquipmentTransformer transformer;

    @Override
    public PageDto<EquipmentDto> findAll(final Pageable pageable) {
        Page<Equipment> page = this.repository.findAll(pageable);

        List<EquipmentDto> resultSet = page.getContent().stream()
                                            .map(transformer::transform)
                                            .collect(Collectors.toList());
        return new PageDto<>(page, resultSet);
    }

    @Override
    public EquipmentDto create(EquipmentDto dto) {
        Equipment entity = this.transformer.transform(dto);

        entity = this.repository.save(entity);
        return this.transformer.transform(entity);
    }

    @Override
    public void delete(final Long id) {
        Equipment entity = this.repository.findOne(id);
        Optional.ofNullable(entity).orElseThrow(() -> new BadRequestException("Equipment not exist"));
        this.repository.delete(entity);
    }

    @Override
    public EquipmentDto update(final EquipmentDto dto, final Long id) {
        Equipment entity = this.repository.findOne(id);
        Equipment newEntity = this.transformer.transform(dto);

        entity.setName(newEntity.getName());
        entity.setDetails(newEntity.getDetails());
        entity.setDescription(newEntity.getDescription());
        entity.setStatus(newEntity.getStatus());
        entity.setPrice(newEntity.getPrice());
        entity = this.repository.save(entity);
        return this.transformer.transform(entity);
    }
}
