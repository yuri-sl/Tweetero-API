package entity;

import io.quarkus.oidc.OidcTenantConfig;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "usuario")
@Entity
@ApplicationScoped
public class UsuarioEntity {

    //Perguntar sobre essa geração de sequencia
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "serial")
    private Long id;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "username",nullable = false)
    private String username;

}
