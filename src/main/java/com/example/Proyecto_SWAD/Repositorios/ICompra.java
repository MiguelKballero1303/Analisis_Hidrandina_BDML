package com.example.Proyecto_SWAD.Repositorios;

import com.example.Proyecto_SWAD.Clases.Compra;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompra extends CrudRepository<Compra,Integer> {

    @Query(value = "SELECT * FROM compra "
            + "ORDER BY id DESC "
            + "LIMIT 1", nativeQuery = true)
    public int ConsultarIdVenta();
    
        @Query(value="SELECT "
                + "c.id as id,"
                + "c.proveedor_id,"
                + "c.mediopago_id,"
                + "c.tipocomprobante_id,"
                + "c.usuario_id,"
                + "c.estado,"                
                + "p.id as id_p,"
                + "mp.id as id_mp,"
                + "tc.id as id_tc,"
                + "u.id as id_u,"
                + "c.fecha,"
                + "p.nombre,"
                + "p.apellido,"
                + "mp.nombre,"
                + "tc.nombre,"
                + "u.nombre,"
                + "u.apellido "
                + "FROM compra c "
                + "INNER JOIN proveedor p ON c.proveedor_id = p.id "
                + "INNER JOIN mediopago mp ON c.mediopago_id = mp.id "
                + "INNER JOIN tipocomprobante tc ON c.tipocomprobante_id = tc.id "
                + "INNER JOIN usuario u ON c.usuario_id = u.id "
                + "WHERE p.nombre LIKE %?1% "
                + "OR p.apellido LIKE %?1% "
                + "OR mp.nombre LIKE %?1% "
                + "OR tc.nombre LIKE %?1% "
                + "OR u.nombre LIKE %?1% "
                + "OR u.apellido LIKE %?1%",nativeQuery=true)
    public List<Compra> findForAll(String desc);

    
}
