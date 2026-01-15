package entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "pdf_file")
public class PdfFileEntity extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Column(nullable = false)
    public String originalName;
    @Column(nullable = false, unique = true)
    public String storedName;
    @Column(nullable = false)
    public String contentType;
    @Column(nullable = false)
    public Long size;
    @Column(nullable = false)
    public Instant created_at;

}
