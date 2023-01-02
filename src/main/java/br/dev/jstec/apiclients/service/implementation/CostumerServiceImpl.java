package br.dev.jstec.apiclients.service.implementation;

import br.dev.jstec.apiclients.domain.entity.Costumer;
import br.dev.jstec.apiclients.domain.repository.CostumerRepository;
import br.dev.jstec.apiclients.rest.dto.CostumerDTO;
import br.dev.jstec.apiclients.service.CostumerService;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;

@Service
public class CostumerServiceImpl implements CostumerService {
    private final CostumerRepository repository;
    private final ModelMapper modelMapper;
    public CostumerServiceImpl ( CostumerRepository repository, ModelMapper modelMapper ) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }


    @Override
    @Transactional(readOnly = true)
    public CostumerDTO findById ( Long id ) {

        return repository.findById( id )
                .map( costumer ->
                        modelMapper.map(costumer, CostumerDTO.class ))
                .orElseThrow( () -> new ResponseStatusException( HttpStatus.NOT_FOUND) );
    }

    @Override
    @Transactional(readOnly = true)
    public Page <CostumerDTO> findAllPaged ( PageRequest pageRequest ) {
        Page<Costumer> list = repository.findAll(pageRequest);
        return list.map( costumer -> modelMapper.map( costumer, CostumerDTO.class ) );
    }

    @Override
    @Transactional
    public CostumerDTO create ( CostumerDTO dto ) {
        Costumer costumer = repository.save( modelMapper.map( dto, Costumer.class ) );
        return modelMapper.map(costumer, CostumerDTO.class);
    }

    @Override
    @Transactional
    public CostumerDTO update ( Long id, CostumerDTO dto ) {
        try {
            Costumer costumer = repository.getReferenceById( id );
            costumer = repository.save( mapperToEntity( dto, costumer ) );

            return modelMapper.map( costumer, CostumerDTO.class );
        }
        catch (EntityNotFoundException e){
            throw new EntityNotFoundException("Cliente não encontrado");
        }
    }

    @Override
    public void delete ( Long id ) {
        try {
            repository.deleteById( id );
        }
        catch  (EmptyResultDataAccessException e){
            throw new ResponseStatusException( HttpStatus.NOT_FOUND );
        }
    }

    private Costumer mapperToEntity (CostumerDTO dto, Costumer costumer){
        costumer.setName( dto.getName() );
        costumer.setCpf( dto.getCpf() );
        costumer.setIncome( dto.getIncome() );
        costumer.setBirthDate( dto.getBirthDate() );
        costumer.setChildren( dto.getChildren() );

        return costumer;
    }


}
