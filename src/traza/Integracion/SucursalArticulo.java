package traza.Integracion;

import traza.traza1.Sucursal;
import traza.traza2.Articulo;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class SucursalArticulo {
    private static long SEQ = 1;

    @EqualsAndHashCode.Include
    @Builder.Default
    private Long id = SEQ++;

    @NonNull private Sucursal sucursal;   // n:1
    @NonNull private Articulo articulo;   // n:1

    @Builder.Default
    private Integer stock = 0;

    // Si es null, usa el precio de lista del Articulo
    private Double precioOverride;

    public double precioEfectivo() {
        return (precioOverride != null) ? precioOverride : articulo.getPrecioVenta();
    }


}
