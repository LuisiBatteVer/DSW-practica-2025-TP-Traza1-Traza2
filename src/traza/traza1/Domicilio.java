package traza.traza1;

import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Domicilio {

    @EqualsAndHashCode.Include @NonNull private String  calle;
    @EqualsAndHashCode.Include @NonNull private Integer numero;
    @EqualsAndHashCode.Include @NonNull private Integer cp;

    // La localidad entra en la igualdad para distinguir "San Martín 500" de una localidad vs de otra.
    @EqualsAndHashCode.Include
    @ToString.Exclude
    private Localidad localidad;
}
