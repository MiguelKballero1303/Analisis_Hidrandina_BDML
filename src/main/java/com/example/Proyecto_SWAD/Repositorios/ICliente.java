package com.example.Proyecto_SWAD.Repositorios;

import com.example.Proyecto_SWAD.Clases.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICliente extends CrudRepository<Cliente, Integer> {

    @Query(value = "SELECT * FROM cliente "
            + "WHERE nombre LIKE %:desc% "
            + "OR apellido LIKE %:desc% "
            + "OR dni LIKE %:desc% "
            + "OR celular LIKE %:desc% "
            + "OR email LIKE %:desc% "
            + "OR direccion LIKE %:desc%", nativeQuery = true)
    List<Cliente> findForAll(@Param("desc") String desc);

}
