package com.example.Proyecto_SWAD.Repositorios;

import com.example.Proyecto_SWAD.Clases.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProveedor extends CrudRepository<Proveedor, Integer> {

    @Query(value = "SELECT * FROM proveedor "
            + "WHERE nombre LIKE %:desc% "
            + "OR apellido LIKE %:desc% "
            + "OR ruc LIKE %:desc% "
            + "OR dni LIKE %:desc% "
            + "OR celular LIKE %:desc% "
            + "OR email LIKE %:desc% "
            + "OR direccion LIKE %:desc% "
            + "OR razonsocial LIKE %:desc% ", nativeQuery = true)
    List<Proveedor> findForAll(@Param("desc") String desc);

}
