package com.uds.sistema.pizzaria.repository;

import com.uds.sistema.pizzaria.enums.AdicionalEnum;
import com.uds.sistema.pizzaria.model.Adicional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AdicionalRepository extends JpaRepository<Adicional, Long> {

    @Query(value = "select a from Adicional a where adicionalEnum = :adicionalEnum and dataCriacao <= :data order by dataCriacao desc ")
    Optional<List<Adicional>> detalheUltimaData(AdicionalEnum adicionalEnum, Date data);
}
