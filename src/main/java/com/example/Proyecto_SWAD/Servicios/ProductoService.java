package com.example.Proyecto_SWAD.Servicios;

import com.example.Proyecto_SWAD.Clases.Producto;
import com.example.Proyecto_SWAD.Interfaces.IProductoService;
import com.example.Proyecto_SWAD.Repositorios.IProducto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProducto data;

    @Override
    public List<Producto> Listar() {
        return (List<Producto>) data.findAll();
    }

    @Override
    public Optional<Producto> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Producto pro) {
        data.save(pro);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Producto> Buscar(String desc) {
        return data.findForAll(desc);
    }

}
