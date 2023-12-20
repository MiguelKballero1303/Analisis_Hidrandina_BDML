package com.example.Proyecto_SWAD.Controladores;

import com.example.Proyecto_SWAD.Clases.Usuario;
import com.example.Proyecto_SWAD.Interfaces.IUsuarioService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/Usuario/")
@Controller
public class ControladorUsuario {

    BCryptPasswordEncoder bcript = new BCryptPasswordEncoder();

    ArrayList<Usuario> lista = new ArrayList();
    String carpeta = "Usuario/";

    @Autowired
    IUsuarioService service;

    @GetMapping("/NuevoUsuario") //localhost/usuario/nuevo
    @PreAuthorize("hasAuthority('ROL_ADMIN')")          
    public String NuevoUsuario(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        return carpeta + "nuevoUsuario"; //nuevoUsuario.html
    }

    @PostMapping("/RegistrarUsuario") //localhost/cliente/registrar
    @PreAuthorize("hasAuthority('ROL_ADMIN')")       
    public String RegistrarUsuario(
            @RequestParam("nom") String nom,
            @RequestParam("ape") String ape,
            @RequestParam("dni") String dni,
            @RequestParam("cel") String cel,
            @RequestParam("email") String email,
            @RequestParam("dir") String dir,
            @RequestParam("pas") String pas,
            @RequestParam("use") String use,
            @RequestParam("rol") String rol,            
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        Usuario u = new Usuario();
        //c.setId(cod);
        u.setNombre(nom);
        u.setApellido(ape);
        u.setDni(dni);
        u.setCelular(cel);
        u.setEmail(email);
        u.setDireccion(dir);
        u.setPassword(bcript.encode(pas));
        u.setUser(use);
        u.setRoles(rol);
        //lista.add(u);
        service.Guardar(u);

        return ListarUsuario(model);
    }

    @GetMapping("/ListaUsuarios") //localhost/
    @PreAuthorize("hasAuthority('ROL_ADMIN')")        
    public String ListarUsuario(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        model.addAttribute("usuarios", service.Listar());
        return carpeta + "listaUsuario"; //listaUsuario.html
    }

    @GetMapping("/EliminarUsuario")
    @PreAuthorize("hasAuthority('ROL_ADMIN')")         
    public String EliminarUsuario(@RequestParam("cod") int cod,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        service.Eliminar(cod);
        return ListarUsuario(model);
    }

    @GetMapping("/EditarUsuario")
    @PreAuthorize("hasAuthority('ROL_ADMIN')")         
    public String EditarUsuario(@RequestParam("cod") int cod,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        Optional<Usuario> usu = service.ConsultarId(cod);

        model.addAttribute("usuario", usu);
        return carpeta + "editarUsuario"; //editarUsuario.html
    }

    @PostMapping("/ActualizarUsuario")
    @PreAuthorize("hasAuthority('ROL_ADMIN')")         
    public String ActualizarUsuario(@RequestParam("id") int cod,
            @RequestParam("nombre") String nom,
            @RequestParam("apellido") String ape,
            @RequestParam("dni") String dni,
            @RequestParam("celular") String cel,
            @RequestParam("email") String email,
            @RequestParam("direccion") String dir,
            @RequestParam("password") String pas,
            @RequestParam("user") String use,
            @RequestParam("rol") String rol,             
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        Usuario u = new Usuario();
        u.setId(cod);
        u.setNombre(nom);
        u.setApellido(ape);
        u.setDni(dni);
        u.setCelular(cel);
        u.setEmail(email);
        u.setDireccion(dir);
        u.setPassword(bcript.encode(pas));
        u.setUser(use);
        u.setRoles(rol);
        service.Guardar(u); //Cuando se envia el ID -> Actualizar

        return ListarUsuario(model);
    }

    @PostMapping("/BuscarUsuario")
    @PreAuthorize("hasAuthority('ROL_ADMIN')")         
    public String BuscarUsuario(@RequestParam("desc") String desc,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        List<Usuario> usuarios = service.Buscar(desc);
        model.addAttribute("usuarios", usuarios);
        return carpeta + "listaUsuario";
    }
}
