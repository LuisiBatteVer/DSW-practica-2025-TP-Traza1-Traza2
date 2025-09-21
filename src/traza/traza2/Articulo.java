package traza.traza2;

import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Articulo {

    private static long SEQ = 1;

    @EqualsAndHashCode.Include
    protected Long id = SEQ++;

    @NonNull protected String denominacion;
    @NonNull protected Double precioVenta;

    @ToString.Exclude
    protected Set<Imagen> imagenes = new HashSet<>();

    @NonNull protected Categoria categoria;
    @NonNull protected UnidadMedida unidadMedida;

    // no-args para Lombok builders en subclases
    protected Articulo() {}

    // ctor común que llaman las subclases
    protected Articulo(String denominacion, Double precioVenta,
                       Categoria categoria, UnidadMedida unidadMedida) {
        this.denominacion = denominacion;
        this.precioVenta = precioVenta;
        this.categoria = categoria;
        this.unidadMedida = unidadMedida;
    }

    public void addImagen(Imagen img) {
        if (img == null) return;
        img.setArticulo(this);
        imagenes.add(img);
    }
}
