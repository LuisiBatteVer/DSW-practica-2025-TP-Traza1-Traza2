package traza.traza2;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class ArticuloInsumo extends Articulo {

    @NonNull private Double precioCompra;
    @NonNull private Integer stockActual;
    @NonNull private Integer stockMaximo;
    @NonNull private Boolean esParaElaborar;

    @Builder
    public ArticuloInsumo(String denominacion, Double precioVenta,
                          Categoria categoria, UnidadMedida unidadMedida,
                          Double precioCompra, Integer stockActual, Integer stockMaximo,
                          Boolean esParaElaborar) {
        super(denominacion, precioVenta, categoria, unidadMedida); // <-- protegido OK
        this.precioCompra = precioCompra;
        this.stockActual = stockActual;
        this.stockMaximo = stockMaximo;
        this.esParaElaborar = esParaElaborar;
    }
}
