package entity;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ApplicationScoped
//Fiz toda a Entidade já?
public class UsuarioEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String avatar;

    @Column
    private String username;

}
