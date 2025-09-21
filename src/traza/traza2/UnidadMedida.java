package traza.traza2;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UnidadMedida {
    private static long SEQ = 1;

    @EqualsAndHashCode.Include
    @Builder.Default
    private Long id = SEQ++;

    @NonNull
    private String denominacion;

    @Override public String toString() { return "UM{id=%d, denom=%s}".formatted(id, denominacion); }
}
