package resource;


import DTO.requests.CriarTweetDTORequest;
import DTO.responses.CriarTweetDTOResponse;
import entity.TweetsEntity;
import entity.UsuarioEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import lombok.AllArgsConstructor;
import org.jboss.resteasy.reactive.RestResponse;
import service.TweetsService;

import java.util.List;

@Path("/tweets")
@ApplicationScoped
@AllArgsConstructor
public class TweetsResource {
    final TweetsService tweetsService;

    @POST
    public RestResponse<CriarTweetDTOResponse> criarTweetPorUsuario(CriarTweetDTORequest criarTweetDTORequest){
        try{
            CriarTweetDTOResponse criarTweetDTOResponse = tweetsService.insertTweet(criarTweetDTORequest);
            return RestResponse.status(RestResponse.Status.CREATED,criarTweetDTOResponse);
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    };
    @GET
    public RestResponse<List<TweetsEntity>> buscarTweetsUsuarios(){
        try{
            List<TweetsEntity> listaTweetsUsuarios = tweetsService.buscarTweetsUsuarios();
            return RestResponse.status(RestResponse.Status.FOUND,listaTweetsUsuarios);
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}
