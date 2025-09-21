package traza.traza1;

import lombok.*;
import java.util.HashSet;
import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pais {

    // Clave lógica: el nombre del país. Me alcanza para igualdad en este TP.
    @EqualsAndHashCode.Include
    @NonNull
    private String nombre;

    // Conjunto de provincias
    @Builder.Default
    @ToString.Exclude
    private Set<Provincia> provincias = new HashSet<>();

    //Cuando agrego una provincia, además de meterla al set, seteo 'pais' en la provincia.
    public void addProvincia(Provincia p) {
        if (p == null) return;
        p.setPais(this);
        provincias.add(p);
    }
}
