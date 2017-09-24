package by.bsuir.psp.utlik.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author alexsandr
 * @since 14.04.17.
 */
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Equipment implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String details;
    private String description;
    private String status;
    private String price;

}
