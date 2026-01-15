package DTO.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PdfDTOResponse {
    Long id;
    String originalName;
    String contentType;
    Long size;
    Instant createdAt;
    String downloadUrl;
}
