package com.example.Proyecto_SWAD.Servicios;


import com.example.Proyecto_SWAD.Clases.Venta;
import com.example.Proyecto_SWAD.Interfaces.IVentaService;
import com.example.Proyecto_SWAD.Repositorios.IVenta;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVenta data;

    @Override
    public List<Venta> Listar() {
        return (List<Venta>) data.findAll();
    }

    @Override
    public Optional<Venta> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Venta v) {
        data.save(v);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Venta> Buscar(String desc) {
        return data.findForAll(desc);
    }
    
    @Override
    public int UltimoIdVenta(){
        return data.ConsultarIdVenta();
    }

}
