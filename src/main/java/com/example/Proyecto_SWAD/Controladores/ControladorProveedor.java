/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Proyecto_SWAD.Controladores;

import com.example.Proyecto_SWAD.Clases.Proveedor;
import com.example.Proyecto_SWAD.Interfaces.IProveedorService;
import java.util.ArrayList;
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

@RequestMapping("/Proveedor/")
@Controller
public class ControladorProveedor {

    String carpeta = "Proveedor/";
    ArrayList<Proveedor> listap = new ArrayList();

    @Autowired
    IProveedorService service;

    @GetMapping("/NuevoProveedor")
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")
    public String NuevoProveedor(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);        
        return carpeta + "nuevoProveedor"; // nuevoProveedor.html
    }

    @PostMapping("/RegistrarProveedor") // localhost/registrar
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")     
    public String RegistrarProveedor(
            @RequestParam("nom") String nom,
            @RequestParam("ape") String ape,
            @RequestParam("ruc") String ruc,
            @RequestParam("dni") String dni,
            @RequestParam("cel") String cel,
            @RequestParam("email") String email,
            @RequestParam("dir") String dir,
            @RequestParam("rs") String razon,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);        
        //Aqui va el proceso de registrar tus datos
        Proveedor p = new Proveedor();
        p.setNombre(nom);
        p.setApellido(ape);
        p.setRuc(ruc);
        p.setDni(dni);
        p.setCelular(cel);
        p.setEmail(email);
        p.setDireccion(dir);
        p.setRazonsocial(razon);
        //listap.add(p);
        service.Guardar(p);

        return ListarProveedor(model);
    }

    @GetMapping("/ListaProveedores")
    public String ListarProveedor(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);        
        model.addAttribute("proveedores", service.Listar());
        return carpeta + "listaProveedor"; //listaProveedor.html
    }

    @GetMapping("/EliminarProveedor")
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")       
    public String EliminarProveedor(@RequestParam("codP") int cod, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);        
        service.Eliminar(cod);
        return ListarProveedor(model);

    }

    @GetMapping("/EditarProveedor")
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")       
    public String EditarProveedor(@RequestParam("codP") int cod, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);        
        Proveedor p = service.ConsultarId(cod).orElse(new Proveedor()); // Obtener el proveedor o un proveedor vacío
        model.addAttribute("proveedor", p);
        return carpeta + "editarProveedor";
    }

    @PostMapping("/ActualizarProveedor")//
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")         
    public String ActualizarProveedor(@RequestParam("id") int cod,
            @RequestParam("nombre") String nom,
            @RequestParam("apellido") String ape,
            @RequestParam("ruc") String ruc,
            @RequestParam("dni") String dni,
            @RequestParam("celular") String cel,
            @RequestParam("email") String email,
            @RequestParam("direccion") String dir,
            @RequestParam("razonsocial") String razon,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);        
        Proveedor p = new Proveedor();
        p.setId(cod);
        p.setNombre(nom);
        p.setApellido(ape);
        p.setRuc(ruc);
        p.setDni(dni);
        p.setCelular(cel);
        p.setEmail(email);
        p.setDireccion(dir);
        p.setRazonsocial(razon);
        service.Guardar(p);
        return ListarProveedor(model);

    }

    @PostMapping("/BuscarProveedor")
    public String BuscarProveedor(@RequestParam("desc") String desc, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);        
        List<Proveedor> resultados = service.Buscar(desc);
        model.addAttribute("proveedores", resultados);
        return carpeta + "listaProveedor";
    }

}
