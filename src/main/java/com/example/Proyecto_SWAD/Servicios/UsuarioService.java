package com.example.Proyecto_SWAD.Servicios;

import com.example.Proyecto_SWAD.Clases.Usuario;
import com.example.Proyecto_SWAD.Interfaces.IUsuarioService;
import com.example.Proyecto_SWAD.Repositorios.IUsuario;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {

    @Autowired
    private IUsuario data;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = data.findByUser(username);
        return usuario.map(UsuarioServiceDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));
    }

    @Override
    public List<Usuario> Listar() {
        return (List<Usuario>) data.findAll();
    }

    @Override
    public Optional<Usuario> ConsultarId(int id) {
        return data.findById(id);
    }

    @Override
    public void Guardar(Usuario u) {
        data.save(u);
    }

    @Override
    public void Eliminar(int id) {
        data.deleteById(id);
    }

    @Override
    public List<Usuario> Buscar(String desc) {
        return data.findForAll(desc);
    }
}
