package com.example.Proyecto_SWAD.Repositorios;

import com.example.Proyecto_SWAD.Clases.Venta;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVenta extends CrudRepository<Venta,Integer> {
    @Query(value = "SELECT * FROM venta "
            + "ORDER BY id DESC "
            + "LIMIT 1", nativeQuery = true)
    public int ConsultarIdVenta();
    
                @Query(value="SELECT "
                + "v.id as id,"
                + "v.cliente_id,"
                + "v.mediopago_id,"
                + "v.tipocomprobante_id,"
                + "v.usuario_id,"
                + "v.estado,"                
                + "c.codigo as id_c,"
                + "mp.id as id_mp,"
                + "tc.id as id_tc,"
                + "u.id as id_u,"
                + "v.fecha,"
                + "c.nombre,"
                + "c.apellido,"
                + "mp.nombre,"
                + "tc.nombre,"
                + "u.nombre,"
                + "u.apellido "
                + "FROM venta v "
                + "INNER JOIN cliente c ON v.cliente_id = c.codigo "
                + "INNER JOIN mediopago mp ON v.mediopago_id = mp.id "
                + "INNER JOIN tipocomprobante tc ON v.tipocomprobante_id = tc.id "
                + "INNER JOIN usuario u ON v.usuario_id = u.id "
                + "WHERE c.nombre LIKE %?1% "
                + "OR c.apellido LIKE %?1% "
                + "OR mp.nombre LIKE %?1% "
                + "OR tc.nombre LIKE %?1% "
                + "OR u.nombre LIKE %?1% "
                + "OR u.apellido LIKE %?1%",nativeQuery=true)
    public List<Venta> findForAll(String desc);


    
}
