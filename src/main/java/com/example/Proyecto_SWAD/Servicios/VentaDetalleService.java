package com.example.Proyecto_SWAD.Servicios;

import com.example.Proyecto_SWAD.Clases.VentaDetalle;
import com.example.Proyecto_SWAD.Interfaces.IVentaDetalleService;
import com.example.Proyecto_SWAD.Repositorios.IVentaDetalle;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaDetalleService implements IVentaDetalleService {

    @Autowired
    private IVentaDetalle data;

    @Override
    public List<VentaDetalle> Listar() {
        return (List<VentaDetalle>) data.findAll();
    }

    @Override
    public Optional<VentaDetalle> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(VentaDetalle v) {
        data.save(v);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public Double SolesVenta(int id) {
        return data.AllSales(id);
    }

    @Override
    public Double CantidadVenta(int id) {
        return data.AllCount(id);
    }

    @Override
    public List<VentaDetalle> ListarPorVenta(int ventaId) {
        return data.findByVentaId(ventaId);
    }

    @Override
    public Double SolesVentaTotal() {
        return data.AllSalesv();
    }
}
