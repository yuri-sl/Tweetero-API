package resource;

import DTO.requests.CriarUsuarioDTORequest;
import DTO.responses.CriarUsuarioDTOResponse;
import DTO.responses.HealthDTOResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.resteasy.reactive.RestResponse;
import service.UsuarioService;

@AllArgsConstructor


@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UsuarioResource {
    final UsuarioService usuarioService;

    @POST
    public RestResponse<?> criarUsuarioNovo(@RequestBody CriarUsuarioDTORequest usuarioDTO){
        try {
            if(usuarioDTO.getUsername() == null || usuarioDTO.getAvatar() == null){
                return RestResponse.status(Response.Status.BAD_REQUEST,"Todos os campos devem estar preenchidos");
            }
            if(usuarioDTO.getUsername().isBlank() || usuarioDTO.getAvatar().isBlank() ){
                return RestResponse.status(Response.Status.BAD_REQUEST,"Todos os campos devem ter conteúdo");
            }
            CriarUsuarioDTOResponse criarUsuarioDTOResponse = usuarioService.adicionarUsuario(usuarioDTO);
            return RestResponse.status(Response.Status.CREATED,criarUsuarioDTOResponse);
        }
        catch (RuntimeException e) {
            return RestResponse.status(Response.Status.CONFLICT,e.getMessage());
        }
    };

    @GET
    public RestResponse<HealthDTOResponse> teste(){
        HealthDTOResponse healthDTOResponse = HealthDTOResponse.builder()
                .mensagem("Estou retornando uma mensagem")
                .build();
        return RestResponse.status(RestResponse.Status.OK,healthDTOResponse);
    }
}
