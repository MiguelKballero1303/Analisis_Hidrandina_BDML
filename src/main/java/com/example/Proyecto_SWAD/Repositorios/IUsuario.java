package com.example.Proyecto_SWAD.Repositorios;

import com.example.Proyecto_SWAD.Clases.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuario extends CrudRepository<Usuario, Integer> {

    public Optional<Usuario> findByUser(String username);

    @Query(value = "SELECT * FROM usuario "
            + "WHERE nombre LIKE %:desc% "
            + "OR apellido LIKE %:desc% "
            + "OR dni LIKE %:desc% "
            + "OR celular LIKE %:desc% "
            + "OR email LIKE %:desc% "
            + "OR direccion LIKE %:desc% "
            + "OR password LIKE %:desc% "
            + "OR user LIKE %:desc%", nativeQuery = true)
    List<Usuario> findForAll(@Param("desc") String desc);
}
