
package com.example.Proyecto_SWAD.Clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name="ventadetalle")
public class VentaDetalle {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private Double cantidad;
    private Double precio;
    private Double total;
    
    @ManyToOne()
    @JoinColumn(name="venta_id")
    private Venta venta;
    
    @ManyToOne()
    @JoinColumn(name="producto_id")
    private Producto producto;
   
}
