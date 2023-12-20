package com.example.Proyecto_SWAD.Repositorios;

import com.example.Proyecto_SWAD.Clases.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProducto extends CrudRepository<Producto,Integer> {

    @Query(value = "SELECT * FROM producto "
            + "WHERE nombre LIKE %:desc% "
            + "OR descripcion LIKE %:desc% "
            + "OR preciov LIKE %:desc% "
            + "OR precioc LIKE %:desc% ", nativeQuery = true)
    List<Producto> findForAll(@Param("desc") String desc);
}
