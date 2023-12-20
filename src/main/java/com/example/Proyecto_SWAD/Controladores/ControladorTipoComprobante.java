package com.example.Proyecto_SWAD.Controladores;

import com.example.Proyecto_SWAD.Clases.TipoComprobante;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.Proyecto_SWAD.Interfaces.ITipoComprobanteService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RequestMapping("/TipoComprobante/")
@Controller
public class ControladorTipoComprobante {

    ArrayList<TipoComprobante> lista = new ArrayList();
    String carpeta = "TipoComprobante/";

    @Autowired
    ITipoComprobanteService service;

    @GetMapping("/NuevoTipoComprobante") //localhost/mediopago/nuevo
    @PreAuthorize("hasAuthority('ROL_ADMIN')")      
    public String NuevoTipocomprobante(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        return carpeta + "nuevoTipoComprobante"; //nuevoTipocomprobante.html
    }

    @PostMapping("/RegistrarTipoComprobante") //localhost/cliente/registrar
    @PreAuthorize("hasAuthority('ROL_ADMIN')")            
    public String RegistrarTipocomprobante(
            @RequestParam("nom") String nom,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        //Aqui va el proceso de registrar
        TipoComprobante tc = new TipoComprobante();
        //c.setId(cod);
        tc.setNombre(nom);

        //lista.add(mp);
        service.Guardar(tc);

        return ListarTipoComprobante(model);
    }

    @GetMapping("/ListaTipoComprobante") //localhost/
    public String ListarTipoComprobante(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        //model.addAttribute("tipocomprobante", lista);
        model.addAttribute("tipocomprobantes", service.Listar());
        return carpeta + "listaTipoComprobante"; //listaTipocomprobante.html
    }

    @GetMapping("/EliminarTipoComprobante") //localhost/eliminar
    @PreAuthorize("hasAuthority('ROL_ADMIN')")            
    public String EliminarTipoComprobante(@RequestParam("cod") int cod,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        service.Eliminar(cod);
        return ListarTipoComprobante(model);
    }

    @GetMapping("/EditarTipoComprobante") //localhost/editar
    @PreAuthorize("hasAuthority('ROL_ADMIN')")      
    public String EditarTipoComprobante(@RequestParam("cod") int cod,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        Optional<TipoComprobante> tip = service.ConsultarId(cod);

        model.addAttribute("tipocomprobante", tip);
        return carpeta + "editarTipoComprobante"; //editarTipocomprobante.html
    }

    @PostMapping("/ActualizarTipoComprobante") //localhost/actualizar
    @PreAuthorize("hasAuthority('ROL_ADMIN')")     
    public String ActualizarTipoComprobante(@RequestParam("id") int cod,
            @RequestParam("nombre") String nom,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        //Aqui va el proceso de registrar
        TipoComprobante tc = new TipoComprobante();
        tc.setId(cod);
        tc.setNombre(nom);

        service.Guardar(tc); //Cuando se envia el ID -> Actualizar

        return ListarTipoComprobante(model);
    }

    @PostMapping("/BuscarTipoComprobante") //localhost/buscar
    public String BuscarTipoComprobante(@RequestParam("desc") String desc,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        List<TipoComprobante> tipocomprobantes = service.Buscar(desc);
        model.addAttribute("tipocomprobantes", tipocomprobantes);
        return carpeta + "listaTipoComprobante";
    }

}
