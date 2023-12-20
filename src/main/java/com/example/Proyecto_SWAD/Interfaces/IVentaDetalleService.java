package com.example.Proyecto_SWAD.Interfaces;

import com.example.Proyecto_SWAD.Clases.VentaDetalle;
import java.util.List;
import java.util.Optional;


public interface IVentaDetalleService {
    public List<VentaDetalle> Listar();
    public Optional<VentaDetalle> ConsultarId(int id);
    public void Guardar(VentaDetalle v);
    public void Eliminar(int id);
    public Double SolesVenta(int id);
    public Double CantidadVenta(int id);
    public Double SolesVentaTotal();    
    public List<VentaDetalle> ListarPorVenta(int ventaId);    
}
