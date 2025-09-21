package traza.traza2;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Imagen {
    private static long SEQ = 1;

    @EqualsAndHashCode.Include
    @Builder.Default
    private Long id = SEQ++;

    @NonNull
    private String denominacion;

    // back-ref para cumplir UML (n:1). Lo excluyo del toString para evitar cascadas.
    @ToString.Exclude
    private Articulo articulo;
}
