package com.example.Proyecto_SWAD.Controladores;

import com.example.Proyecto_SWAD.Clases.Cliente;
import com.example.Proyecto_SWAD.Interfaces.IClienteService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/Cliente/")
@Controller
public class ControladorCliente {

    String carpeta = "Cliente/";

    @Autowired
    IClienteService service;

    @GetMapping("/NuevoCliente")
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")
    public String NuevoCliente(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        return carpeta + "nuevoCliente";
    }

    @PostMapping("/RegistrarCliente")
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")
    public String RegistrarCliente(
            @RequestParam("nom") String nom,
            @RequestParam("ape") String ape,
            @RequestParam("dni") String dni,
            @RequestParam("cel") String cel,
            @RequestParam("email") String email,
            @RequestParam("dir") String dir,
            Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        Cliente c = new Cliente();
        c.setNombre(nom);
        c.setApellido(ape);
        c.setDni(dni);
        c.setCelular(cel);
        c.setEmail(email);
        c.setDireccion(dir);

        service.Guardar(c);
        return ListarCliente(model);
    }

    @GetMapping("/ListaClientes")
    public String ListarCliente(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        model.addAttribute("clientes", service.Listar());
        return carpeta + "listaCliente";
    }

    @GetMapping("/EliminarCliente")
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")
    public String EliminarCliente(@RequestParam("codC") int cod, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        service.Eliminar(cod);
        return ListarCliente(model);

    }

    @GetMapping("/EditarCliente")
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")
    public String EditarCliente(@RequestParam("codC") int cod, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        Optional<Cliente> cli = service.ConsultarId(cod);
        model.addAttribute("cliente", cli);
        return carpeta + "editarCliente";
    }

    @PostMapping("/ActualizarCliente")//
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")
    public String ActualizarCliente(@RequestParam("codigo") int cod,
            @RequestParam("nombre") String nom,
            @RequestParam("apellido") String ape,
            @RequestParam("dni") String dni,
            @RequestParam("celular") String cel,
            @RequestParam("email") String email,
            @RequestParam("direccion") String dir,
            Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        Cliente c = new Cliente();
        c.setCodigo(cod);
        c.setNombre(nom);
        c.setApellido(ape);
        c.setDni(dni);
        c.setCelular(cel);
        c.setEmail(email);
        c.setDireccion(dir);
        service.Guardar(c);
        return ListarCliente(model);

    }

    @PostMapping("/BuscarCliente")
    public String BuscarCliente(@RequestParam("desc") String desc, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        List<Cliente> resultados = service.Buscar(desc);
        model.addAttribute("clientes", resultados);
        return carpeta + "listaCliente";
    }
}
