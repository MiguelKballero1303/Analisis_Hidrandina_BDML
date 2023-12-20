package com.example.Proyecto_SWAD.Interfaces;

import com.example.Proyecto_SWAD.Clases.Venta;
import java.util.List;
import java.util.Optional;


public interface IVentaService {
    public List<Venta> Listar();
    public Optional<Venta> ConsultarId(int id);
    public void Guardar(Venta v);
    public void Eliminar(int id);
    public List<Venta> Buscar(String desc);
    public int UltimoIdVenta();
}
