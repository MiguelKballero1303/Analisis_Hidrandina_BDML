package com.example.Proyecto_SWAD.Controladores;

import com.example.Proyecto_SWAD.Clases.Producto;
import com.example.Proyecto_SWAD.Interfaces.IProductoService;
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

@RequestMapping("/Producto/")
@Controller
public class ControladorProducto {

    ArrayList<Producto> lista = new ArrayList();
    String carpeta = "Producto/";

    @Autowired
    IProductoService service;

    @GetMapping("/NuevoProducto") //localhost/nuevo
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_OPER')")
    public String NuevoProducto(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        return carpeta + "nuevoProducto"; //nuevoProducto.html
    }

    @PostMapping("/RegistrarProducto") //localhost/registrar
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_OPER')")
    public String RegistrarProducto(
            @RequestParam("nom") String nom,
            @RequestParam("desc") String desc,
            @RequestParam("precv") Double precv,
            @RequestParam("precc") Double precc,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        //Aqui va el proceso de registrar
        Producto p = new Producto();
        p.setNombre(nom);
        p.setDescripcion(desc);
        p.setPreciov(precv);
        p.setPrecioc(precc);

        service.Guardar(p);
        return ListarProducto(model);
    }

    @GetMapping("/ListaProductos") //localhost/
    public String ListarProducto(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        model.addAttribute("productos", service.Listar());
        return carpeta + "listaProducto"; //listaProducto.html
    }

    @GetMapping("/EliminarProducto") //localhost/eliminar
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_OPER')")
    public String EliminarProducto(@RequestParam("cod") int cod,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        service.Eliminar(cod);
        return ListarProducto(model);
    }

    @GetMapping("/EditarProducto") //localhost/editar
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_OPER')")
    public String EditarProducto(@RequestParam("cod") int cod,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        Optional<Producto> prod = service.ConsultarId(cod);
        model.addAttribute("producto", prod);
        return carpeta + "editarProducto"; //editarProducto.html
    }

    @PostMapping("/ActualizarProducto") //localhost/actualizar
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_OPER')")
    public String ActualizarProducto(@RequestParam("codigo") int cod,
            @RequestParam("nombre") String nom,
            @RequestParam("descripcion") String desc,
            @RequestParam("preciov") Double precv,
            @RequestParam("precioc") Double precc,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        Producto pro = new Producto();
        pro.setCodigo(cod);
        pro.setNombre(nom);
        pro.setDescripcion(desc);
        pro.setPreciov(precv);
        pro.setPrecioc(precc);
        service.Guardar(pro);
        return ListarProducto(model);
    }

    @PostMapping("/BuscarProducto") //localhost/buscar
    public String BuscarProducto(@RequestParam("desc") String desc,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        List<Producto> resultados = service.Buscar(desc);
        model.addAttribute("productos", resultados);
        return carpeta + "listaProducto";
    }
}
