package com.example.Proyecto_SWAD.Interfaces;

import com.example.Proyecto_SWAD.Clases.Producto;
import java.util.List;
import java.util.Optional;


public interface IProductoService {
    public List<Producto> Listar();
    public Optional<Producto> ConsultarId(int id);
    public void Guardar(Producto pro);
    public void Eliminar(int id);
    public List<Producto> Buscar(String desc);
}
