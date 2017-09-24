package by.bsuir.psp.utlik.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author alexsandr
 * @since 14.04.17.
 */
@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDto implements Dto {
    private Long id;
    @NotNull
    private String name;

    private String details;
    private String description;
    @NotNull
    private String status;
    @NotNull
    private String price;
}
