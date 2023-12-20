package com.example.Proyecto_SWAD.Repositorios;

import com.example.Proyecto_SWAD.Clases.CompraDetalle;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICompraDetalle extends CrudRepository<CompraDetalle, Integer> {

    @Query(value = "SELECT SUM(total) as monto_total "
            + "FROM compradetalle "
            + "WHERE compra_id = ?1", nativeQuery = true)
    public Double AllSales(int id);

    @Query(value = "SELECT SUM(total) as monto_total "
            + "FROM compradetalle ", nativeQuery = true)
    public Double AllSalesc();

    @Query(value = "SELECT COUNT(*) as cant_total "
            + "FROM compradetalle "
            + "WHERE compra_id = ?1", nativeQuery = true)
    public Double AllCount(int id);

    @Query("SELECT cd FROM CompraDetalle cd WHERE cd.compra.id = :compraId")
    List<CompraDetalle> findByCompraId(@Param("compraId") int ventaId);

}
