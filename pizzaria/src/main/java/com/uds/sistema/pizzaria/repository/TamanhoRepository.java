package com.uds.sistema.pizzaria.repository;

import com.uds.sistema.pizzaria.enums.TamanhoEnum;
import com.uds.sistema.pizzaria.model.Tamanho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TamanhoRepository extends JpaRepository<Tamanho, Long> {

    @Query(value = "select t from Tamanho t where tamanhoEnum = :tamanhoEnum and dataCriacao <= :data order by dataCriacao desc ")
    Optional<List<Tamanho>> detalheUltimaData(TamanhoEnum tamanhoEnum, Date data);

}
