
package com.example.Proyecto_SWAD.Servicios;

import com.example.Proyecto_SWAD.Clases.TipoComprobante;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Proyecto_SWAD.Interfaces.ITipoComprobanteService;
import com.example.Proyecto_SWAD.Repositorios.ITipoComprobante;

@Service
public class TipoComprobanteService implements ITipoComprobanteService {

    @Autowired
    private ITipoComprobante data;

    @Override
    public List<TipoComprobante> Listar() {
        return (List<TipoComprobante>) data.findAll();
    }

    @Override
    public Optional<TipoComprobante> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(TipoComprobante tc) {
        data.save(tc);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<TipoComprobante> Buscar(String desc) {
        return data.findForAll(desc);
    }
}