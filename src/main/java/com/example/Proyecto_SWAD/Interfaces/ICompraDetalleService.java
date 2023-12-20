package com.example.Proyecto_SWAD.Interfaces;

import com.example.Proyecto_SWAD.Clases.CompraDetalle;
import java.util.List;
import java.util.Optional;


public interface ICompraDetalleService {
    public List<CompraDetalle> Listar();
    public Optional<CompraDetalle> ConsultarId(int id);
    public void Guardar(CompraDetalle compra);
    public void Eliminar(int id);
     public Double SolesCompra(int id);
    public Double CantidadCompra(int id);
    public Double SolesCompraTotal();    
    public List<CompraDetalle> ListarPorCompra(int compraId);    
}
