package traza.traza1;

import lombok.*;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Empresa {

    // Genero IDs en memoria
    private static final AtomicLong SEQ = new AtomicLong(1);

    @EqualsAndHashCode.Include
    @Builder.Default
    private Long id = SEQ.getAndIncrement();

    @NonNull private String nombre;
    @NonNull private String razonSocial;

    // Uso Long para CUIT por comodidad
    @NonNull private Long cuit;

    private String logo;

    // Varias sucursales por empresa. HashSet para multiplicidad y evitar duplicados.
    @Builder.Default
    @ToString.Exclude
    private Set<Sucursal> sucursales = new HashSet<>();

    public void addSucursal(Sucursal s) {
        if (s != null) sucursales.add(s);
    }
}
