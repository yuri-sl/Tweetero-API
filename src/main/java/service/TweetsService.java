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
            if(verificarDTOCriarTweetValido(criarTweetDTORequest)){


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
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public List<TweetsEntity> buscarTweetsUsuarios(){
        return tweetsRepository.listAll();
    }
    public List<TweetsEntity> buscarTweetsUsuariosPorId(Long idUsuario){
        return tweetsRepository.fetchTweetsByUserId(idUsuario);
    }

    public boolean verificarDTOCriarTweetValido(CriarTweetDTORequest dados){
        boolean valid;
        //isEmpty => Checa apenas se a String tem length = 0
        //isBlank => Checa se a String é vazia ou composta por apenas caracteres whitespaces
        if(     (!dados.getText().isBlank()) &&
                (dados.getUserId() != null)
        ){
            if((dados.getText().getClass().equals(String.class)) && (dados.getUserId().getClass().equals(Long.class))){
                valid = true;}
            else{
                valid = false;
                throw  new RuntimeException("Algum dos campos está com o formato incorreto!");
            }
        }
        else{
            valid = false;
            throw new RuntimeException("Todos os campos devem estar preenchidos!");
            return false;
        }
    }

}
