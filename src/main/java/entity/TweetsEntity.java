package entity;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tweet")
@Builder
@ApplicationScoped
public class TweetsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",columnDefinition = "serial")
    private Long id;

    @Column(name = "text",nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UsuarioEntity usuarioEntity;

}
