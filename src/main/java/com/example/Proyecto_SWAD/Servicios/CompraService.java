package com.example.Proyecto_SWAD.Servicios;


import com.example.Proyecto_SWAD.Clases.Compra;
import com.example.Proyecto_SWAD.Interfaces.ICompraService;
import com.example.Proyecto_SWAD.Repositorios.ICompra;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraService implements ICompraService {

    @Autowired
    private ICompra data;

    @Override
    public List<Compra> Listar() {
        return (List<Compra>) data.findAll();
    }

    @Override
    public Optional<Compra> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Compra c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Compra> Buscar(String desc) {
        return data.findForAll(desc);
    }
    
    @Override
    public int UltimoIdCompra(){
        return data.ConsultarIdVenta();
    }

}
