package com.uds.sistema.pizzaria.repository;

import com.uds.sistema.pizzaria.enums.SaborEnum;
import com.uds.sistema.pizzaria.model.Sabor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface SaborRepository extends JpaRepository<Sabor, Long> {

    @Query(value = "select s from Sabor s where saborEnum = :saborEnum and dataCriacao <= :data order by dataCriacao desc ")
    Optional<List<Sabor>> detalheUltimaData(SaborEnum saborEnum, Date data);

}
