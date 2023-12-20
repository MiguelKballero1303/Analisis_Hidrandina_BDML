package com.example.Proyecto_SWAD.Repositorios;

import com.example.Proyecto_SWAD.Clases.MedioPago;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IMedioPago extends CrudRepository<MedioPago, Integer> {

    @Query(value = "SELECT * FROM mediopago WHERE nombre LIKE %:desc%", nativeQuery = true)
    List<MedioPago> findForAll(@Param("desc") String desc);
}
