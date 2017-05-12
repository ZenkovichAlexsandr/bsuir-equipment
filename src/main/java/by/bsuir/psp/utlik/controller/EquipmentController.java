package by.bsuir.psp.utlik.controller;

import by.bsuir.psp.utlik.dto.EquipmentDto;
import by.bsuir.psp.utlik.dto.PageDto;
import by.bsuir.psp.utlik.service.EquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author alexsandr
 * @since 14.04.17.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/equipment", produces = MediaType.APPLICATION_JSON_VALUE)
public class EquipmentController {

    private final EquipmentService equipmentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public PageDto<EquipmentDto> findAll(final Pageable pageable) {
        return this.equipmentService.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EquipmentDto create(@RequestBody @Valid final EquipmentDto dto) {
        return this.equipmentService.create(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EquipmentDto update(@RequestBody @Valid final EquipmentDto dto, @PathVariable final Long id) {
        return this.equipmentService.update(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final Long id) {
        this.equipmentService.delete(id);
    }
}
