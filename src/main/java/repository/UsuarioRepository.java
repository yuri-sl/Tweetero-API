package repository;

import DTO.requests.CriarUsuarioDTORequest;
import entity.UsuarioEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<UsuarioEntity> {
    public List<UsuarioEntity> verificarUsuarioExiste(CriarUsuarioDTORequest criarUsuarioDTORequest){
        return find("where username = ?1", criarUsuarioDTORequest.getUsername()).stream().toList();
    }

    public List<UsuarioEntity> buscarUsuarioPorId(Long userId){
        return find("where id = ?1",userId).stream().toList();
    }

    public UsuarioEntity encontrarUsuarioId(long user_id){
        return find("id",user_id).firstResult();
    }
}
