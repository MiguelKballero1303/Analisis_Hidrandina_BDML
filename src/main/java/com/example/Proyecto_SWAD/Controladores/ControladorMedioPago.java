package com.example.Proyecto_SWAD.Controladores;

import com.example.Proyecto_SWAD.Clases.MedioPago;
import com.example.Proyecto_SWAD.Interfaces.IMedioPagoService;
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

@RequestMapping("/MedioPago/")
@Controller
public class ControladorMedioPago {

    ArrayList<MedioPago> lista = new ArrayList();
    String carpeta = "MedioPago/";

    @Autowired
    IMedioPagoService service;
    @PreAuthorize("hasAuthority('ROL_ADMIN')")     
    @GetMapping("/NuevoMedioPago") //localhost/mediopago/nuevo
    public String NuevoMediopago(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        return carpeta + "nuevoMedioPago"; //nuevoUsuario.html
    }

    @PostMapping("/RegistrarMedioPago")
    @PreAuthorize("hasAuthority('ROL_ADMIN')")          
    public String RegistrarMedioPago(
            @RequestParam("nom") String nom,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        //Aqui va el proceso de registrar
        MedioPago mp = new MedioPago();
        //c.setId(cod);
        mp.setNombre(nom);

        //lista.add(mp);
        service.Guardar(mp);

        return ListarMedioPago(model);
    }

    @GetMapping("/ListaMedioPago") //localhost/
    public String ListarMedioPago(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        model.addAttribute("mediopagos", service.Listar());
        return carpeta + "listaMedioPago"; //listaMediopago.html
    }

    @GetMapping("/EliminarMedioPago") //localhost/eliminar
    @PreAuthorize("hasAuthority('ROL_ADMIN')")         
    public String EliminarMediopago(@RequestParam("cod") int cod,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        service.Eliminar(cod);
        return ListarMedioPago(model);
    }

    @GetMapping("/EditarMedioPago") //localhost/editar
     @PreAuthorize("hasAuthority('ROL_ADMIN')")        
    public String EditarMediopago(@RequestParam("cod") int cod,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        Optional<MedioPago> med = service.ConsultarId(cod);

        model.addAttribute("mediopago", med);
        return carpeta + "editarMedioPago"; //editarMediopago.html
    }

    @PostMapping("/ActualizarMedioPago") //localhost/actualizar
    @PreAuthorize("hasAuthority('ROL_ADMIN')")            
    public String ActualizarMediopago(@RequestParam("id") int cod,
            @RequestParam("nombre") String nom,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);        
        //Aqui va el proceso de registrar
        MedioPago mp = new MedioPago();
        mp.setId(cod);
        mp.setNombre(nom);

        service.Guardar(mp); //Cuando se envia el ID -> Actualizar

        return ListarMedioPago(model);
    }

    @PostMapping("/BuscarMedioPago") //localhost/buscar
    public String BuscarMedioPago(@RequestParam("desc") String desc, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);        
        List<MedioPago> resultados = service.Buscar(desc);
        model.addAttribute("mediopagos", resultados);
        return carpeta + "listaMedioPago";
    }
}
