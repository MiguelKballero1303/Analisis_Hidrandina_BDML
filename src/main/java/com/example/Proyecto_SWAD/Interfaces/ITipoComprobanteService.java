
package com.example.Proyecto_SWAD.Interfaces;

import com.example.Proyecto_SWAD.Clases.TipoComprobante;
import java.util.List;
import java.util.Optional;


public interface ITipoComprobanteService {
    public List<TipoComprobante> Listar();
    public Optional<TipoComprobante> ConsultarId(int id);
    public void Guardar(TipoComprobante tc);
    public void Eliminar(int id);
    public List<TipoComprobante> Buscar(String desc);
}
