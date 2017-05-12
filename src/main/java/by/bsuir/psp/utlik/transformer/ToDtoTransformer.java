package by.bsuir.psp.utlik.transformer;

import by.bsuir.psp.utlik.dto.Dto;

/**
 * @author alexsandr
 * @since 14.04.17.
 */
@FunctionalInterface
public interface ToDtoTransformer<D extends Dto, E> {
    D transform(E entity);
}
