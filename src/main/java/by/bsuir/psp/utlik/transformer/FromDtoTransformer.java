package by.bsuir.psp.utlik.transformer;

import by.bsuir.psp.utlik.dto.Dto;

/**
 * @author alexsandr
 * @since 14.04.17.
 */
@FunctionalInterface
public interface FromDtoTransformer<D extends Dto, E> {
    E transform(D dto);
}
