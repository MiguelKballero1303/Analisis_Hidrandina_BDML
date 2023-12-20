package com.example.Proyecto_SWAD.Interfaces;

import com.example.Proyecto_SWAD.Clases.Compra;
import java.util.List;
import java.util.Optional;


public interface ICompraService {
    public List<Compra> Listar();
    public Optional<Compra> ConsultarId(int id);
    public void Guardar(Compra compra);
    public void Eliminar(int id);
    public List<Compra> Buscar(String desc);
    public int UltimoIdCompra();
}
