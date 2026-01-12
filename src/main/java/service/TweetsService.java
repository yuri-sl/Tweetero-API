package service;


import DTO.requests.CriarTweetDTORequest;
import DTO.responses.CriarTweetDTOResponse;
import DTO.responses.CriarUsuarioDTOResponse;
import entity.TweetsEntity;
import entity.UsuarioEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import repository.TweetsRepository;
import repository.UsuarioRepository;

import java.util.List;

@AllArgsConstructor
@ApplicationScoped
public class TweetsService {

    final TweetsRepository tweetsRepository;
    final UsuarioRepository usuarioRepository;

    @Transactional
    public CriarTweetDTOResponse insertTweet(CriarTweetDTORequest criarTweetDTORequest){
        try{
            UsuarioEntity usuarioEncontrado = usuarioRepository.buscarUsuarioPorId(criarTweetDTORequest.getUserId()).getFirst();

            CriarUsuarioDTOResponse dtoUsuarioEncontrado  = CriarUsuarioDTOResponse.builder()
                    .id(usuarioEncontrado.getId())
                    .avatar(usuarioEncontrado.getAvatar())
                    .username(usuarioEncontrado.getUsername())
                    .build();


            TweetsEntity tweetCriado = TweetsEntity.builder()
                    .text(criarTweetDTORequest.getText())
                    .usuarioEntity(usuarioEncontrado)
                    .build();
            tweetsRepository.persist(tweetCriado);
            tweetsRepository.flush();

            return CriarTweetDTOResponse.builder()
                    .id(tweetCriado.getId())
                    .text(tweetCriado.getText())
                    .criarUsuarioDTOResponse(dtoUsuarioEncontrado)
                    .build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public List<TweetsEntity> buscarTweetsUsuarios(){
        return tweetsRepository.listAll();
    }

}
