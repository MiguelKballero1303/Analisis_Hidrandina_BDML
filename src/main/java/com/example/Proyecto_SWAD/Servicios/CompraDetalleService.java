package com.example.Proyecto_SWAD.Servicios;

import com.example.Proyecto_SWAD.Clases.CompraDetalle;
import com.example.Proyecto_SWAD.Interfaces.ICompraDetalleService;
import com.example.Proyecto_SWAD.Repositorios.ICompraDetalle;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraDetalleService implements ICompraDetalleService {

    @Autowired
    private ICompraDetalle data;

    @Override
    public List<CompraDetalle> Listar() {
        return (List<CompraDetalle>) data.findAll();
    }

    @Override
    public Optional<CompraDetalle> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(CompraDetalle c) {
        data.save(c);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public Double SolesCompra(int id) {
        return data.AllSales(id);
    }

    @Override
    public Double CantidadCompra(int id) {
        return data.AllCount(id);
    }

    @Override
    public List<CompraDetalle> ListarPorCompra(int compraId) {
        return data.findByCompraId(compraId);
    }
    @Override
    public Double SolesCompraTotal() {
        return data.AllSalesc();
    }
}
