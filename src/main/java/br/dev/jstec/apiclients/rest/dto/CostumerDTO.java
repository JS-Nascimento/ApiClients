package br.dev.jstec.apiclients.rest.dto;

import lombok.*;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CostumerDTO {
     private Long id;
     private String name;
     private String cpf;
     private Double income;
     private LocalDate birthDate;
     private Integer children;
}
