package com.example.Proyecto_SWAD.Controladores;

import com.example.Proyecto_SWAD.Clases.Compra;
import com.example.Proyecto_SWAD.Clases.CompraAux;
import com.example.Proyecto_SWAD.Clases.Venta;
import com.example.Proyecto_SWAD.Clases.VentaAux;
import com.example.Proyecto_SWAD.Interfaces.ICompraDetalleService;
import com.example.Proyecto_SWAD.Interfaces.ICompraService;
import com.example.Proyecto_SWAD.Interfaces.IVentaDetalleService;
import com.example.Proyecto_SWAD.Interfaces.IVentaService;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/Informe/")
@Controller
public class ControladorInforme {

    String carpeta = "Informe/";

    @Autowired
    IVentaService service_v;

    @Autowired
    IVentaDetalleService service_vd;

    @Autowired
    ICompraService service_c;

    @Autowired
    ICompraDetalleService service_cd;

    @GetMapping("/Reporte") //localhost/
    public String InformeVentasCompras(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        model.addAttribute("username", username);
        ArrayList<VentaAux> listaV_aux = new ArrayList();
        List<Venta> ventas = service_v.Listar();
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

            listaV_aux.add(aux);
        }

        ArrayList<CompraAux> listaC_aux = new ArrayList();
        List<Compra> compras = service_c.Listar();
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

            listaC_aux.add(aux);
        }
        Double soles_total_venta = service_vd.SolesVentaTotal();

        Double soles_total_compra = service_cd.SolesCompraTotal();

        Double diferencia = soles_total_venta - soles_total_compra;

        DecimalFormat formato = new DecimalFormat("#.##");
        String numeroFormateado = formato.format(diferencia);
        double diferenciaFinal = Double.parseDouble(numeroFormateado);

        model.addAttribute("solesVentas", soles_total_venta);
        model.addAttribute("solesCompras", soles_total_compra);
        model.addAttribute("diferencia", diferenciaFinal);
        model.addAttribute("ventas", listaV_aux);
        model.addAttribute("compras", listaC_aux);
        return carpeta + "reporte";
    }

}
