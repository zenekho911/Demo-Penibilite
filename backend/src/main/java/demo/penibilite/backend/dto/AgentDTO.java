package demo.penibilite.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AgentDTO {
    private Long id;
    private String nomComplet;
    private String email;
    private String username;
}
