package repository;

import DTO.CriarUsuarioDTO;
import entity.UsuarioEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Data
@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<UsuarioEntity> {
    public List<UsuarioEntity> verificarUsuarioExiste(CriarUsuarioDTO criarUsuarioDTO){
        return find("where username = ?1",criarUsuarioDTO.getUsername()).stream().toList();
    }
}
