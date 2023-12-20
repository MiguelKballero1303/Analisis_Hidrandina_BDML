
package com.example.Proyecto_SWAD.Repositorios;

import com.example.Proyecto_SWAD.Clases.TipoComprobante;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ITipoComprobante extends CrudRepository<TipoComprobante,Integer> {
    @Query(value="SELECT * FROM tipocomprobante "
            + "WHERE nombre LIKE %:desc% " , nativeQuery=true)
    List<TipoComprobante> findForAll(@Param("desc") String desc);
}
