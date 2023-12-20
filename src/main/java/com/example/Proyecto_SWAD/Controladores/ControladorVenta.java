package com.example.Proyecto_SWAD.Controladores;

import com.example.Proyecto_SWAD.Clases.Carrito;
import com.example.Proyecto_SWAD.Clases.Cliente;
import com.example.Proyecto_SWAD.Clases.MedioPago;
import com.example.Proyecto_SWAD.Clases.Producto;
import com.example.Proyecto_SWAD.Clases.TipoComprobante;
import com.example.Proyecto_SWAD.Clases.Usuario;
import com.example.Proyecto_SWAD.Clases.Venta;
import com.example.Proyecto_SWAD.Clases.VentaAux;
import com.example.Proyecto_SWAD.Clases.VentaDetalle;
import com.example.Proyecto_SWAD.Interfaces.IClienteService;
import com.example.Proyecto_SWAD.Interfaces.IMedioPagoService;
import com.example.Proyecto_SWAD.Interfaces.IProductoService;
import com.example.Proyecto_SWAD.Interfaces.ITipoComprobanteService;
import com.example.Proyecto_SWAD.Interfaces.IVentaDetalleService;
import com.example.Proyecto_SWAD.Interfaces.IVentaService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

@RequestMapping("/Venta/")
@Controller
public class ControladorVenta {

    String carpeta = "Venta/";

    @Autowired
    IVentaService service;

    @Autowired
    IVentaDetalleService service_vd;

    @Autowired
    IClienteService service_cli;

    @Autowired
    IMedioPagoService service_mp;

    @Autowired
    ITipoComprobanteService service_tc;

    @Autowired
    IProductoService service_pro;

    ArrayList<Carrito> carrito = new ArrayList();

    @GetMapping("/ListaVentas") //localhost/
    public String ListarVenta(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        ArrayList<VentaAux> lista_aux = new ArrayList();
        List<Venta> ventas = service.Listar();
        for (int i = 0; i < ventas.size(); i++) {
            int id_venta = ventas.get(i).getId();
            Double soles = service_vd.SolesVenta(id_venta);
            Double cantidad = service_vd.CantidadVenta(id_venta);

            VentaAux aux = new VentaAux();
            aux.setId(id_venta);
            aux.setSoles(soles);
            aux.setCantidad(cantidad);
            aux.setNom_cliente(ventas.get(i).getCliente().getNombre());
            aux.setApe_cliente(ventas.get(i).getCliente().getApellido());
            aux.setMediopago(ventas.get(i).getMediopago().getNombre());
            aux.setTipocomprobante(ventas.get(i).getTipocomprobante().getNombre());
            aux.setNom_usuario(ventas.get(i).getUsuario().getNombre());
            aux.setApe_usuario(ventas.get(i).getUsuario().getApellido());
            aux.setFecha(ventas.get(i).getFecha());
            aux.setEstado(ventas.get(i).getEstado());

            lista_aux.add(aux);
        }

        model.addAttribute("ventas", lista_aux);
        return carpeta + "listaVenta"; //listaVenta.html
    }

    @GetMapping("/DetalleVenta")
    public String ListarVentaDetalle(@RequestParam("venta_id") int ventaId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        // Aquí debes implementar la lógica para obtener los registros de VentaDetalle relacionados con el ventaId
        List<VentaDetalle> ventadetalles = service_vd.ListarPorVenta(ventaId);
        ArrayList<VentaAux> lista_aux = new ArrayList();
        List<Venta> ventas = service.Listar();
        for (int i = 0; i < ventas.size(); i++) {
            int id_venta = ventas.get(i).getId();
            Double soles = service_vd.SolesVenta(id_venta);
            Double cantidad = service_vd.CantidadVenta(id_venta);

            VentaAux aux = new VentaAux();
            aux.setId(id_venta);
            aux.setSoles(soles);
            aux.setCantidad(cantidad);
            aux.setNom_cliente(ventas.get(i).getCliente().getNombre());
            aux.setApe_cliente(ventas.get(i).getCliente().getApellido());
            aux.setMediopago(ventas.get(i).getMediopago().getNombre());
            aux.setTipocomprobante(ventas.get(i).getTipocomprobante().getNombre());
            aux.setNom_usuario(ventas.get(i).getUsuario().getNombre());
            aux.setApe_usuario(ventas.get(i).getUsuario().getApellido());
            aux.setFecha(ventas.get(i).getFecha());
            aux.setEstado(ventas.get(i).getEstado());

            lista_aux.add(aux);
        }

        model.addAttribute("ventas", lista_aux);
        model.addAttribute("ventadetalles", ventadetalles);
        return carpeta + "listaVenta";
    }

    @GetMapping("/NuevoVenta") //localhost/venta/nuevo
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")
    public String NuevoVenta(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        model.addAttribute("clientes", service_cli.Listar());
        model.addAttribute("mediopagos", service_mp.Listar());
        model.addAttribute("tipocomprobantes", service_tc.Listar());
        model.addAttribute("productos", service_pro.Listar());
        model.addAttribute("carrito", carrito);

        return carpeta + "nuevoVenta"; //nuevoVenta.html
    }

    @PostMapping("/AgregarVenta")
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")
    public String AgregarCarrito(@RequestParam("producto_id") int id,
            @RequestParam("cant") Double cant,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        Optional<Producto> producto = service_pro.ConsultarId(id);
        String nombre = producto.get().getNombre();
        Double precio = producto.get().getPreciov();
        Double total = precio * cant;

        Carrito car = new Carrito();
        car.setId(id);
        car.setProducto(nombre);
        car.setPrecio(precio);
        car.setCantidad(cant);
        car.setTotal(total);

        carrito.add(car);

        return NuevoVenta(model);
    }

    @GetMapping("/QuitarVenta")
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")
    public String QuitarCarrito(@RequestParam("cod") int cod,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        carrito.remove(cod - 1);
        return NuevoVenta(model);
    }

    @PostMapping("/RegistrarVenta")
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")
    public String RegistrarVenta(@RequestParam("cliente_id") Cliente cliente,
            @RequestParam("tipocomprobante_id") TipoComprobante tipocomprobante,
            @RequestParam("mediopago_id") MedioPago mediopago,
            @RequestParam("fec") String fec,
            Model model) throws ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        //2023-10-31T21:53
        String[] parts = fec.split("T");
        String part1 = parts[0]; //2023-10-31
        String part2 = parts[1]; //21:53
        String fec_ = part1 + " " + part2 + ":00";
        //2023-10-31 21:53:00

        SimpleDateFormat formateador_fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fecha = formateador_fecha.parse(fec_);

        Usuario usuario = new Usuario();
        usuario.setId(1);

        Venta v = new Venta();
        v.setFecha(fecha);
        v.setCliente(cliente);
        v.setMediopago(mediopago);
        v.setTipocomprobante(tipocomprobante);
        v.setUsuario(usuario);
        v.setEstado("activo");

        service.Guardar(v);

        int id_venta = service.UltimoIdVenta();
        Venta vent = new Venta();
        vent.setId(id_venta);

        //Registrar venta detalle
        for (int i = 0; i < carrito.size(); i++) {

            int id_producto = carrito.get(i).getId();
            Producto p = new Producto();
            p.setCodigo(id_producto);

            Double precio = carrito.get(i).getPrecio();
            Double cantidad = carrito.get(i).getCantidad();
            Double total = carrito.get(i).getTotal();

            VentaDetalle vd = new VentaDetalle();
            vd.setVenta(vent);
            vd.setProducto(p);
            vd.setPrecio(precio);
            vd.setCantidad(cantidad);
            vd.setTotal(total);

            //guardar la venta detalle
            service_vd.Guardar(vd);
        }

        carrito.clear();

        return ListarVenta(model);
    }

    @PostMapping("/BuscarVenta")
    public String Buscar(@RequestParam("desc") String desc,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);

        ArrayList<VentaAux> lista_aux = new ArrayList();

        List<Venta> ventas = service.Buscar(desc);
        for (int i = 0; i < ventas.size(); i++) {
            int id_venta = ventas.get(i).getId();
            Double soles = service_vd.SolesVenta(id_venta);
            Double cantidad = service_vd.CantidadVenta(id_venta);

            VentaAux aux = new VentaAux();
            aux.setId(id_venta);
            aux.setSoles(soles);
            aux.setCantidad(cantidad);
            aux.setNom_cliente(ventas.get(i).getCliente().getNombre());
            aux.setApe_cliente(ventas.get(i).getCliente().getApellido());
            aux.setMediopago(ventas.get(i).getMediopago().getNombre());
            aux.setTipocomprobante(ventas.get(i).getTipocomprobante().getNombre());
            aux.setNom_usuario(ventas.get(i).getUsuario().getNombre());
            aux.setApe_usuario(ventas.get(i).getUsuario().getApellido());
            aux.setFecha(ventas.get(i).getFecha());
            aux.setEstado(ventas.get(i).getEstado());
            lista_aux.add(aux);
        }

        model.addAttribute("ventas", lista_aux);
        return carpeta + "listaVenta";
    }

    @GetMapping("/AnularVenta")
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")
    public String AnularVenta(@RequestParam("venta_id") int ventaId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        Optional<Venta> optionalVenta = service.ConsultarId(ventaId);

        if (optionalVenta.isPresent()) {
            Venta venta = optionalVenta.get();
            if ("anulado".equalsIgnoreCase(venta.getEstado())) {
            } else {
                venta.setEstado("anulado");
                service.Guardar(venta);
            }
        } else {

        }

        return ListarVenta(model);
    }
}
