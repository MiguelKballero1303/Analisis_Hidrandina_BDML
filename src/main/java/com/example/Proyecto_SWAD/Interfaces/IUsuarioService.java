package com.example.Proyecto_SWAD.Interfaces;

import com.example.Proyecto_SWAD.Clases.Usuario;
import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public List<Usuario> Listar();
    public Optional<Usuario> ConsultarId(int id);
    public void Guardar(Usuario u);
    public void Eliminar(int id);
    public List<Usuario> Buscar(String desc);
}
