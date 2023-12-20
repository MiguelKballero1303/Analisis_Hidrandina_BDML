package com.example.Proyecto_SWAD.Servicios;

import com.example.Proyecto_SWAD.Clases.Proveedor;
import com.example.Proyecto_SWAD.Interfaces.IProveedorService;
import com.example.Proyecto_SWAD.Repositorios.IProveedor;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProveedorService implements IProveedorService{

    @Autowired
    private IProveedor data;
    
    @Override
    public List<Proveedor> Listar() {
        return (List<Proveedor>) data.findAll();
    }

    @Override
    public Optional<Proveedor> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Proveedor p) {
        data.save(p);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Proveedor> Buscar(String desc) {
        return data.findForAll(desc);
    }
    
}
