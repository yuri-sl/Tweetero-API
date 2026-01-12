package service;

import DTO.requests.CriarUsuarioDTORequest;
import DTO.responses.CriarUsuarioDTOResponse;
import entity.UsuarioEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import repository.UsuarioRepository;

import java.util.List;

@AllArgsConstructor
@ApplicationScoped
//Dá erro se eu tirar o AllArgsConstructor do UsuarioService. Pq q eu preciso dele?
public class UsuarioService {


    final UsuarioRepository usuarioRepository;

    @Transactional
    public CriarUsuarioDTOResponse adicionarUsuario(CriarUsuarioDTORequest usuarioDTO){
       List<UsuarioEntity> listaUsuarios  = usuarioRepository.verificarUsuarioExiste(usuarioDTO);

       if(listaUsuarios.isEmpty()){
           UsuarioEntity userCreated = UsuarioEntity.builder()
                   .avatar(usuarioDTO.getAvatar())
                   .username(usuarioDTO.getUsername())
                   .build();
           usuarioRepository.persist(userCreated);
           usuarioRepository.flush();
           return CriarUsuarioDTOResponse.builder()
                   .id(userCreated.getId())
                   .avatar(userCreated.getAvatar())
                   .username(userCreated.getUsername())
                   .build();
       } else{
           throw new java.lang.RuntimeException("Usuario ja existente no sistema");
       }
    };
}
