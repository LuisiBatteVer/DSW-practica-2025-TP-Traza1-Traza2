package traza.traza2;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Categoria {
    private static long SEQ = 1;

    @EqualsAndHashCode.Include
    @Builder.Default
    private Long id = SEQ++;

    @NonNull
    private String denominacion;

    // Mantengo unidireccional desde Articulo -> Categoria (como en el UML).
    @Override public String toString() { return "Categoria{id=%d, denom=%s}".formatted(id, denominacion); }
}
