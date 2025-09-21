package traza.traza2;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ArticuloManufacturadoDetalle {
    private static long SEQ = 1;

    @EqualsAndHashCode.Include
    @Builder.Default
    private Long id = SEQ++;

    @NonNull private Integer cantidad;

    // Referencias (n:1 hacia ambos lados)
    @ToString.Exclude @NonNull private ArticuloInsumo insumo;
    @ToString.Exclude private ArticuloManufacturado manufacturado;

    @Override public String toString() {
        return "Detalle{id=%d, cant=%d, insumo=%s}"
                .formatted(id, cantidad, insumo.getDenominacion());
    }
}
