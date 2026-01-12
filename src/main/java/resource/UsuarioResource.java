package resource;

import DTO.requests.CriarUsuarioDTORequest;
import DTO.responses.CriarUsuarioDTOResponse;
import entity.UsuarioEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.jboss.resteasy.reactive.RestResponse;
import repository.UsuarioRepository;
import service.UsuarioService;

@AllArgsConstructor


@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UsuarioResource {
    final UsuarioService usuarioService;
    final UsuarioRepository usuarioRepository;

    @POST
    public RestResponse<?> criarUsuarioNovo(@RequestBody CriarUsuarioDTORequest usuarioDTO){
        try {
            CriarUsuarioDTOResponse criarUsuarioDTOResponse = usuarioService.adicionarUsuario(usuarioDTO);
            return RestResponse.status(Response.Status.CREATED,criarUsuarioDTOResponse);
        }
        catch (RuntimeException e) {
            return RestResponse.status(Response.Status.CONFLICT,e.getMessage());
        }
    };
    @GET
    public RestResponse<?> teste(){
        return RestResponse.status(RestResponse.Status.OK);
    }

    @Path("/encontrar")
    @GET
    public RestResponse<UsuarioEntity> encontrarUsuarioId( Long usuario_id){
        UsuarioEntity usuarioEntityEncontrado = usuarioRepository.encontrarUsuarioId(usuario_id);
        return RestResponse.status(RestResponse.Status.FOUND,usuarioEntityEncontrado);
    }
}
