package com.example.Proyecto_SWAD.Clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity //Entidad de base de datos
@Table(name="producto")
public class Producto {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    public int codigo;
    public String nombre;
    public String descripcion;
    public Double preciov;
    public Double precioc;
}
