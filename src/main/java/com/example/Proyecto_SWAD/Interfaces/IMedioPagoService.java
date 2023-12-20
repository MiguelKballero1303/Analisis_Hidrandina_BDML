
package com.example.Proyecto_SWAD.Interfaces;

import com.example.Proyecto_SWAD.Clases.MedioPago;
import java.util.List;
import java.util.Optional;


public interface IMedioPagoService {
     public List<MedioPago> Listar();
    public Optional<MedioPago> ConsultarId(int id);
    public void Guardar(MedioPago mp);
    public void Eliminar(int id);
    public List<MedioPago> Buscar(String desc);
}
