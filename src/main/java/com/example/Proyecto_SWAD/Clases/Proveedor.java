package com.example.Proyecto_SWAD.Clases;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity //Entidad de base de datos
@Table(name="proveedor")
public class Proveedor {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    public int id;
    public String nombre;
    public String apellido;
    public String ruc;
    public String dni;
    public String celular;
    public String email;
    public String direccion;
    public String razonsocial;
}
