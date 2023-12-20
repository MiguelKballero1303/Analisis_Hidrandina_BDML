
package com.example.Proyecto_SWAD.Servicios;

import com.example.Proyecto_SWAD.Clases.MedioPago;
import com.example.Proyecto_SWAD.Repositorios.IMedioPago;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Proyecto_SWAD.Interfaces.IMedioPagoService;

@Service
public class MedioPagoService implements IMedioPagoService {

    @Autowired
    private IMedioPago data;

    @Override
    public List<MedioPago> Listar() {
        return (List<MedioPago>) data.findAll();
    }

    @Override
    public Optional<MedioPago> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(MedioPago mp) {
        data.save(mp);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<MedioPago> Buscar(String desc) {
        return data.findForAll(desc);
    }
}
