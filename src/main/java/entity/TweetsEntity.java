package entity;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "tweet")
@Builder
public class TweetsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text",nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    @Fetch(FetchMode.JOIN)
    private UsuarioEntity usuarioEntity;

}
