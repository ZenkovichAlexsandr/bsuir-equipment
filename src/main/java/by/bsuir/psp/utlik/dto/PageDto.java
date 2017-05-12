package by.bsuir.psp.utlik.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author alexsandr
 * @since 14.04.17.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageDto<D extends Dto> implements Dto {
    private int page;
    private int pageSize;
    private int totalPages;
    private List<D> results;


    public PageDto(final Page<?> page, final List<D> resultSet) {
        this.page = page.getNumber();
        this.pageSize = page.getSize();
        this.totalPages = page.getTotalPages();
        this.results = resultSet;
    }
}
