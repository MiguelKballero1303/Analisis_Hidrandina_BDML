package com.example.Proyecto_SWAD.Repositorios;

import com.example.Proyecto_SWAD.Clases.VentaDetalle;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaDetalle extends CrudRepository<VentaDetalle, Integer> {

    @Query(value = "SELECT SUM(total) as monto_total "
            + "FROM ventadetalle "
            + "WHERE venta_id = ?1", nativeQuery = true)
    public Double AllSales(int id);

    @Query(value = "SELECT COUNT(*) as cant_total "
            + "FROM ventadetalle "
            + "WHERE venta_id = ?1", nativeQuery = true)
    public Double AllCount(int id);

    @Query(value = "SELECT SUM(total) as monto_total "
            + "FROM ventadetalle ", nativeQuery = true)
    public Double AllSalesv();

    @Query("SELECT vd FROM VentaDetalle vd WHERE vd.venta.id = :ventaId")
    List<VentaDetalle> findByVentaId(@Param("ventaId") int ventaId);

}
