package traza.traza1;

import lombok.*;
import java.util.HashSet;
import java.util.Set;

//Localidad : Pertenece a una Provincia y agrupa Domicilios.

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Localidad {

    @EqualsAndHashCode.Include
    @NonNull
    private String nombre;

    @EqualsAndHashCode.Include
    @ToString.Exclude
    private Provincia provincia;

    // Varios domicilios por localidad en HashSet
    @Builder.Default
    @ToString.Exclude
    private Set<Domicilio> domicilios = new HashSet<>();

    //Seteo la localidad del domicilio y lo agrego al set.
    public void addDomicilio(Domicilio d) {
        if (d == null) return;
        d.setLocalidad(this);
        domicilios.add(d);
    }
}
