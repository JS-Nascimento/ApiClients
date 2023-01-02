package br.dev.jstec.apiclients.rest.controller;


import br.dev.jstec.apiclients.rest.dto.CostumerDTO;
import br.dev.jstec.apiclients.service.CostumerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/costumers")
public class CostumerController {
    private final CostumerService service;

    public CostumerController ( CostumerService service ) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public CostumerDTO findById( @PathVariable Long id ){

        return service.findById(id);

    }
    @GetMapping
    public ResponseEntity<Page <CostumerDTO>> findAll(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "5") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {

        PageRequest pageRequest = PageRequest.of( page, linesPerPage, Sort.Direction.valueOf( direction ), orderBy );
        Page<CostumerDTO> list = service.findAllPaged(pageRequest);
        return ResponseEntity
                .ok()
                .body(list);
    }

    @PostMapping
    public ResponseEntity<CostumerDTO> create(@RequestBody CostumerDTO dto){
        dto = service.create(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest().path( "/{id}" )
                .buildAndExpand( dto.getId() ).toUri();
        return ResponseEntity.created(uri).body( dto );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CostumerDTO> update (@PathVariable Long id , @RequestBody CostumerDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok().body( dto );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Long id){
        service.delete(id);
    }
}
