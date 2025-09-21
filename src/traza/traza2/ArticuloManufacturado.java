package traza.traza2;

import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ArticuloManufacturado extends Articulo {

    @NonNull private String descripcion;
    @NonNull private Integer tiempoEstimadoMinutos;
    @NonNull private String preparacion;

    @ToString.Exclude
    private Set<ArticuloManufacturadoDetalle> detalles = new HashSet<>();

    // <-- Deja SOLO este @Builder en el constructor
    @Builder
    public ArticuloManufacturado(String denominacion, Double precioVenta,
                                 Categoria categoria, UnidadMedida unidadMedida,
                                 String descripcion, Integer tiempoEstimadoMinutos, String preparacion) {
        super(denominacion, precioVenta, categoria, unidadMedida);
        this.descripcion = descripcion;
        this.tiempoEstimadoMinutos = tiempoEstimadoMinutos;
        this.preparacion = preparacion;
    }

    public void addDetalle(ArticuloManufacturadoDetalle d) {
        if (d == null) return;
        d.setManufacturado(this);
        detalles.add(d);
    }
}
