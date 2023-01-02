package br.dev.jstec.apiclients.service;


import br.dev.jstec.apiclients.rest.dto.CostumerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface CostumerService {

    CostumerDTO findById ( Long id );

    Page<CostumerDTO> findAllPaged ( PageRequest pageRequest );

    CostumerDTO create ( CostumerDTO dto );

    CostumerDTO update ( Long id, CostumerDTO dto );

    void delete ( Long id );
}
