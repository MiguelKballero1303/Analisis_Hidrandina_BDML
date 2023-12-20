package com.example.Proyecto_SWAD.Controladores;

import com.example.Proyecto_SWAD.Clases.Carrito;
import com.example.Proyecto_SWAD.Clases.MedioPago;
import com.example.Proyecto_SWAD.Clases.Producto;
import com.example.Proyecto_SWAD.Clases.TipoComprobante;
import com.example.Proyecto_SWAD.Clases.Usuario;
import com.example.Proyecto_SWAD.Clases.Compra;
import com.example.Proyecto_SWAD.Clases.CompraAux;
import com.example.Proyecto_SWAD.Clases.CompraDetalle;
import com.example.Proyecto_SWAD.Clases.Proveedor;
import com.example.Proyecto_SWAD.Interfaces.IMedioPagoService;
import com.example.Proyecto_SWAD.Interfaces.IProductoService;
import com.example.Proyecto_SWAD.Interfaces.ITipoComprobanteService;
import com.example.Proyecto_SWAD.Interfaces.ICompraDetalleService;
import com.example.Proyecto_SWAD.Interfaces.ICompraService;
import com.example.Proyecto_SWAD.Interfaces.IProveedorService;
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

@RequestMapping("/Compra/")
@Controller
public class ControladorCompra {

    String carpeta = "Compra/";

    @Autowired
    ICompraService service;

    @Autowired
    ICompraDetalleService service_cd;

    @Autowired
    IProveedorService service_prov;

    @Autowired
    IMedioPagoService service_mp;

    @Autowired
    ITipoComprobanteService service_tc;

    @Autowired
    IProductoService service_pro;

    ArrayList<Carrito> carrito = new ArrayList();

    @GetMapping("/ListaCompras")
    public String ListarCompra(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        ArrayList<CompraAux> lista_aux = new ArrayList();
        List<Compra> compras = service.Listar();
        for (int i = 0; i < compras.size(); i++) {
            int id_compra = compras.get(i).getId();
            Double soles = service_cd.SolesCompra(id_compra);
            Double cantidad = service_cd.CantidadCompra(id_compra);

            CompraAux aux = new CompraAux();
            aux.setId(id_compra);
            aux.setSoles(soles);
            aux.setCantidad(cantidad);
            aux.setNom_proveedor(compras.get(i).getProveedor().getNombre());
            aux.setApe_proveedor(compras.get(i).getProveedor().getApellido());
            aux.setMediopago(compras.get(i).getMediopago().getNombre());
            aux.setTipocomprobante(compras.get(i).getTipocomprobante().getNombre());
            aux.setNom_usuario(compras.get(i).getUsuario().getNombre());
            aux.setApe_usuario(compras.get(i).getUsuario().getApellido());
            aux.setFecha(compras.get(i).getFecha());
            aux.setEstado(compras.get(i).getEstado());

            lista_aux.add(aux);
        }

        model.addAttribute("compras", lista_aux);
        return carpeta + "listaCompra"; //listaVenta.html
    }

    @GetMapping("/DetalleCompra")
    public String ListarCompraDetalle(@RequestParam("compra_id") int compraId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        List<CompraDetalle> compradetalles = service_cd.ListarPorCompra(compraId);
        ArrayList<CompraAux> lista_aux = new ArrayList();
        List<Compra> compras = service.Listar();
        for (int i = 0; i < compras.size(); i++) {
            int id_compra = compras.get(i).getId();
            Double soles = service_cd.SolesCompra(id_compra);
            Double cantidad = service_cd.CantidadCompra(id_compra);

            CompraAux aux = new CompraAux();
            aux.setId(id_compra);
            aux.setSoles(soles);
            aux.setCantidad(cantidad);
            aux.setNom_proveedor(compras.get(i).getProveedor().getNombre());
            aux.setApe_proveedor(compras.get(i).getProveedor().getApellido());
            aux.setMediopago(compras.get(i).getMediopago().getNombre());
            aux.setTipocomprobante(compras.get(i).getTipocomprobante().getNombre());
            aux.setNom_usuario(compras.get(i).getUsuario().getNombre());
            aux.setApe_usuario(compras.get(i).getUsuario().getApellido());
            aux.setFecha(compras.get(i).getFecha());
            aux.setEstado(compras.get(i).getEstado());

            lista_aux.add(aux);
        }

        model.addAttribute("compras", lista_aux);
        model.addAttribute("compradetalles", compradetalles);
        return carpeta + "listaCompra";
    }

    @GetMapping("/NuevoCompra") //localhost/venta/nuevo
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")
    public String NuevoCompra(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        model.addAttribute("proveedores", service_prov.Listar());
        model.addAttribute("mediopagos", service_mp.Listar());
        model.addAttribute("tipocomprobantes", service_tc.Listar());
        model.addAttribute("productos", service_pro.Listar());
        model.addAttribute("carrito", carrito);

        return carpeta + "nuevoCompra"; //nuevoVenta.html
    }

    @PostMapping("/AgregarCompra")
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

        return NuevoCompra(model);
    }

    @GetMapping("/QuitarCompra")
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")
    public String QuitarCarrito(@RequestParam("cod") int cod,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        carrito.remove(cod - 1);
        return NuevoCompra(model);
    }

    @PostMapping("/RegistrarCompra")
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")
    public String RegistrarCompra(@RequestParam("proveedor_id") Proveedor proveedor,
            @RequestParam("tipocomprobante_id") TipoComprobante tipocomprobante,
            @RequestParam("mediopago_id") MedioPago mediopago,
            @RequestParam("fec") String fec,
            Model model) throws ParseException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        String[] parts = fec.split("T");
        String part1 = parts[0]; //2023-10-31
        String part2 = parts[1]; //21:53
        String fec_ = part1 + " " + part2 + ":00";
        //2023-10-31 21:53:00

        SimpleDateFormat formateador_fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fecha = formateador_fecha.parse(fec_);

        Usuario usuario = new Usuario();
        usuario.setId(1);

        Compra c = new Compra();
        c.setFecha(fecha);
        c.setProveedor(proveedor);
        c.setMediopago(mediopago);
        c.setTipocomprobante(tipocomprobante);
        c.setUsuario(usuario);
        c.setEstado("activo");

        service.Guardar(c);

        int id_compra = service.UltimoIdCompra();
        Compra comp = new Compra();
        comp.setId(id_compra);

        for (int i = 0; i < carrito.size(); i++) {

            int id_producto = carrito.get(i).getId();
            Producto p = new Producto();
            p.setCodigo(id_producto);

            Double precio = carrito.get(i).getPrecio();
            Double cantidad = carrito.get(i).getCantidad();
            Double total = carrito.get(i).getTotal();

            CompraDetalle cd = new CompraDetalle();
            cd.setCompra(comp);
            cd.setProducto(p);
            cd.setPrecio(precio);
            cd.setCantidad(cantidad);
            cd.setTotal(total);

            service_cd.Guardar(cd);
        }

        carrito.clear();

        return ListarCompra(model);
    }

    @PostMapping("/BuscarCompra")
    public String Buscar(@RequestParam("desc") String desc,
            Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        ArrayList<CompraAux> lista_aux = new ArrayList();

        List<Compra> compras = service.Buscar(desc);
        for (int i = 0; i < compras.size(); i++) {
            int id_compra = compras.get(i).getId();
            Double soles = service_cd.SolesCompra(id_compra);
            Double cantidad = service_cd.CantidadCompra(id_compra);

            CompraAux aux = new CompraAux();
            aux.setId(id_compra);
            aux.setSoles(soles);
            aux.setCantidad(cantidad);
            aux.setNom_proveedor(compras.get(i).getProveedor().getNombre());
            aux.setApe_proveedor(compras.get(i).getProveedor().getApellido());
            aux.setMediopago(compras.get(i).getMediopago().getNombre());
            aux.setTipocomprobante(compras.get(i).getTipocomprobante().getNombre());
            aux.setNom_usuario(compras.get(i).getUsuario().getNombre());
            aux.setApe_usuario(compras.get(i).getUsuario().getApellido());
            aux.setFecha(compras.get(i).getFecha());
            aux.setEstado(compras.get(i).getEstado());
            lista_aux.add(aux);
        }

        model.addAttribute("compras", lista_aux);
        return carpeta + "listaCompra";
    }

    @GetMapping("/AnularCompra")
    @PreAuthorize("hasAuthority('ROL_ADMIN') or hasAuthority('ROL_ENC')")
    public String AnularCompra(@RequestParam("compra_id") int compraId, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        Optional<Compra> optionalCompra = service.ConsultarId(compraId);

        if (optionalCompra.isPresent()) {
            Compra compra = optionalCompra.get();
            if ("anulado".equalsIgnoreCase(compra.getEstado())) {
            } else {
                compra.setEstado("anulado");
                service.Guardar(compra);
            }
        } else {

        }

        return ListarCompra(model);
    }
}
