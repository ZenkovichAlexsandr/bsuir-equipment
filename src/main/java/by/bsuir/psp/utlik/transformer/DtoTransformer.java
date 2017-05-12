package by.bsuir.psp.utlik.transformer;

import by.bsuir.psp.utlik.dto.Dto;

/**
 * @author alexsandr
 * @since 14.04.17.
 */
public interface DtoTransformer<D extends Dto, E> extends ToDtoTransformer<D, E>, FromDtoTransformer<D, E> {

}
