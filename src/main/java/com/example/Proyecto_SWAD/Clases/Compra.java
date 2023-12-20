package com.example.Proyecto_SWAD.Clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name = "compra")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Date fecha;
    private String estado;
    @ManyToOne()
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    @ManyToOne()
    @JoinColumn(name = "tipocomprobante_id")
    private TipoComprobante tipocomprobante;

    @ManyToOne()
    @JoinColumn(name = "mediopago_id")
    private MedioPago mediopago;

    @ManyToOne()
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
