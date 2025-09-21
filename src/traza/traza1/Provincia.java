package traza.traza1;

import lombok.*;
import java.util.HashSet;
import java.util.Set;


//Pertenece a un Pais y contiene varias Localidades.
//Mantengo HashSet para cumplir con multiplicidades.
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Provincia {

    // Nombre como parte de la identidad lógica dentro del país.
    @EqualsAndHashCode.Include
    @NonNull
    private String nombre;

    // Referencia al país "padre". Excluida de toString para no imprimir en cascada.
    @EqualsAndHashCode.Include
    @ToString.Exclude
    private Pais pais;

    // Set de localidades de esta provincia.
    @Builder.Default
    @ToString.Exclude
    private Set<Localidad> localidades = new HashSet<>();

    //Encapsulo la lógica de asociación bidireccional y así evito olvidarme de setear la provincia en la localidad cuando la agrego.

    public void addLocalidad(Localidad l) {
        if (l == null) return;
        l.setProvincia(this);
        localidades.add(l);
    }
}
