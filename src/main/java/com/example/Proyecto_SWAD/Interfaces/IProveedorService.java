package com.example.Proyecto_SWAD.Interfaces;

import java.util.List;
import java.util.Optional;

import com.example.Proyecto_SWAD.Clases.Proveedor;


public interface IProveedorService {
    public List<Proveedor> Listar();
    public Optional<Proveedor> ConsultarId(int id);
    public void Guardar(Proveedor p);
    public void Eliminar(int id);
    public List<Proveedor> Buscar(String desc);
}
