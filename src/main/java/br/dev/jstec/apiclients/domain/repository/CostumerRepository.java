package br.dev.jstec.apiclients.domain.repository;


import br.dev.jstec.apiclients.domain.entity.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Long> {
}
