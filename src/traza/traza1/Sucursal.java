package traza.traza1;

import lombok.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

import traza.Integracion.SucursalArticulo;
import traza.traza2.Articulo;

//Sucursal: Relación 1:1 con Domicilio (según el diagrama).
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sucursal {

    @EqualsAndHashCode.Include
    @NonNull
    private String nombre;

    @NonNull private LocalTime horarioApertura;
    @NonNull private LocalTime horarioCierre;
    private boolean esCasaMatriz;

    @ToString.Exclude
    @NonNull
    private Domicilio domicilio;

    // Integración
    @Builder.Default @ToString.Exclude
    private Set<SucursalArticulo> inventario = new HashSet<>();

    public SucursalArticulo agregarAlInventario(Articulo articulo, int stockInicial, Double precioOverride) {
        SucursalArticulo fila = SucursalArticulo.builder()
                .sucursal(this)
                .articulo(articulo)
                .stock(stockInicial)
                .precioOverride(precioOverride)
                .build();
        inventario.add(fila);
        return fila;
    }

}
