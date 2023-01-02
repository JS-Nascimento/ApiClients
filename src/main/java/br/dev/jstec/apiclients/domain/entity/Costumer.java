package br.dev.jstec.apiclients.domain.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Costumer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String cpf;
    @Getter
    @Setter
    private Double income;
    @Getter
    @Setter
    private LocalDate birthDate;
    @Getter
    @Setter
    private Integer children;


}
