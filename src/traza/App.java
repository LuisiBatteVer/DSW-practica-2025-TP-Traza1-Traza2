package traza;

import traza.traza1.*;
import traza.traza2.*;
import traza.Integracion.SucursalArticulo;

import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

public class App {

    // ===== “Repos” simples en memoria =====
    private static final Set<Empresa> EMPRESAS   = new HashSet<>();
    private static final Set<Categoria> CATS     = new HashSet<>();
    private static final Set<UnidadMedida> UMS   = new HashSet<>();
    private static final Set<Articulo> ARTICULOS = new HashSet<>();

    // ===== Utilidades CRUD de manufacturados =====
    private static Optional<ArticuloManufacturado> findManuById(Long id) {
        return ARTICULOS.stream().filter(a -> a instanceof ArticuloManufacturado && a.getId().equals(id))
                .map(a -> (ArticuloManufacturado) a).findFirst();
    }
    private static boolean updateManuPrecio(Long id, double nuevo) {
        return findManuById(id).map(m -> { m.setPrecioVenta(nuevo); return true; }).orElse(false);
    }
    private static boolean deleteManuById(Long id) {
        return ARTICULOS.removeIf(a -> a instanceof ArticuloManufacturado && a.getId().equals(id));
    }

    // ===== Helpers de impresión =====
    private static void titulo(String t){ System.out.println("\n=== " + t + " ==="); }
    private static void print(Collection<?> c){ c.forEach(System.out::println); }

    public static void main(String[] args) {
        // ---------------- TRAZA 1: País → … → Empresa/Sucursal ----------------
        Pais ar = Pais.builder().nombre("Argentina").build();

        Provincia bsas = Provincia.builder().nombre("Buenos Aires").build(); ar.addProvincia(bsas);
        Provincia mza  = Provincia.builder().nombre("Mendoza").build();      ar.addProvincia(mza);

        Localidad caba     = Localidad.builder().nombre("CABA").build();                bsas.addLocalidad(caba);
        Localidad laPlata  = Localidad.builder().nombre("La Plata").build();            bsas.addLocalidad(laPlata);
        Localidad cdMza    = Localidad.builder().nombre("Ciudad de Mendoza").build();   mza.addLocalidad(cdMza);
        Localidad sanRaf   = Localidad.builder().nombre("San Rafael").build();          mza.addLocalidad(sanRaf);

        Domicilio domCaba   = Domicilio.builder().calle("Av. Corrientes").numero(1234).cp(1043).build(); caba.addDomicilio(domCaba);
        Domicilio domLP     = Domicilio.builder().calle("Calle 7").numero(777).cp(1900).build();          laPlata.addDomicilio(domLP);
        Domicilio domCdMza  = Domicilio.builder().calle("San Martín").numero(500).cp(5500).build();       cdMza.addDomicilio(domCdMza);
        Domicilio domSanRaf = Domicilio.builder().calle("Av. Mitre").numero(200).cp(5600).build();        sanRaf.addDomicilio(domSanRaf);

        Sucursal suc1 = Sucursal.builder().nombre("Sucursal 1 - CABA").horarioApertura(LocalTime.of(9,0))
                .horarioCierre(LocalTime.of(18,0)).esCasaMatriz(true).domicilio(domCaba).build();
        Sucursal suc2 = Sucursal.builder().nombre("Sucursal 2 - La Plata").horarioApertura(LocalTime.of(9,0))
                .horarioCierre(LocalTime.of(18,0)).esCasaMatriz(false).domicilio(domLP).build();
        Sucursal suc3 = Sucursal.builder().nombre("Sucursal 3 - Ciudad de Mendoza").horarioApertura(LocalTime.of(9,0))
                .horarioCierre(LocalTime.of(18,0)).esCasaMatriz(false).domicilio(domCdMza).build();
        Sucursal suc4 = Sucursal.builder().nombre("Sucursal 4 - San Rafael").horarioApertura(LocalTime.of(9,0))
                .horarioCierre(LocalTime.of(18,0)).esCasaMatriz(false).domicilio(domSanRaf).build();

        Empresa emp1 = Empresa.builder().nombre("Empresa1").razonSocial("Empresa Uno SA").cuit(30_12345678_9L).build();
        emp1.addSucursal(suc1); emp1.addSucursal(suc2);
        Empresa emp2 = Empresa.builder().nombre("Empresa2").razonSocial("Empresa Dos SRL").cuit(30_87654321_9L).build();
        emp2.addSucursal(suc3); emp2.addSucursal(suc4);
        EMPRESAS.add(emp1); EMPRESAS.add(emp2);

        // ---------------- TRAZA 2: Categorías, UMs, Insumos, Manufacturados ----------------
        Categoria catPizzas = Categoria.builder().denominacion("Pizzas").build();
        Categoria catLomos  = Categoria.builder().denominacion("Lomos").build();
        Categoria catSand   = Categoria.builder().denominacion("Sandwich").build();
        Categoria catIns    = Categoria.builder().denominacion("Insumos").build();
        CATS.addAll(Arrays.asList(catPizzas, catLomos, catSand, catIns));

        UnidadMedida gr = UnidadMedida.builder().denominacion("Gramos").build();
        UnidadMedida kg = UnidadMedida.builder().denominacion("Kilogramos").build();
        UnidadMedida lt = UnidadMedida.builder().denominacion("Litros").build();
        UMS.addAll(Arrays.asList(gr, kg, lt));

        ArticuloInsumo sal = ArticuloInsumo.builder()
                .denominacion("Sal").precioVenta(0.0).categoria(catIns).unidadMedida(gr)
                .precioCompra(200.0).stockActual(1000).stockMaximo(5000).esParaElaborar(true).build();

        ArticuloInsumo aceite = ArticuloInsumo.builder()
                .denominacion("Aceite").precioVenta(0.0).categoria(catIns).unidadMedida(lt)
                .precioCompra(1800.0).stockActual(200).stockMaximo(1000).esParaElaborar(true).build();

        ArticuloInsumo carne = ArticuloInsumo.builder()
                .denominacion("Carne").precioVenta(0.0).categoria(catIns).unidadMedida(kg)
                .precioCompra(4500.0).stockActual(50).stockMaximo(200).esParaElaborar(true).build();

        ArticuloInsumo harina = ArticuloInsumo.builder()
                .denominacion("Harina").precioVenta(0.0).categoria(catIns).unidadMedida(kg)
                .precioCompra(1200.0).stockActual(300).stockMaximo(2000).esParaElaborar(true).build();

        ARTICULOS.addAll(Arrays.asList(sal, aceite, carne, harina));

        Imagen img1 = Imagen.builder().denominacion("imagen1-Hawaiana-1").build();
        Imagen img2 = Imagen.builder().denominacion("imagen2-Hawaiana-2").build();
        Imagen img3 = Imagen.builder().denominacion("imagen3-Hawaiana-3").build();
        Imagen img4 = Imagen.builder().denominacion("imagen4-Lomo-1").build();
        Imagen img5 = Imagen.builder().denominacion("imagen5-Lomo-2").build();
        Imagen img6 = Imagen.builder().denominacion("imagen6-Lomo-3").build();

        ArticuloManufacturadoDetalle dp1 = ArticuloManufacturadoDetalle.builder().cantidad(1).insumo(sal).build();
        ArticuloManufacturadoDetalle dp2 = ArticuloManufacturadoDetalle.builder().cantidad(2).insumo(harina).build();
        ArticuloManufacturadoDetalle dp3 = ArticuloManufacturadoDetalle.builder().cantidad(1).insumo(aceite).build();

        ArticuloManufacturadoDetalle dl1 = ArticuloManufacturadoDetalle.builder().cantidad(1).insumo(sal).build();
        ArticuloManufacturadoDetalle dl2 = ArticuloManufacturadoDetalle.builder().cantidad(1).insumo(aceite).build();
        ArticuloManufacturadoDetalle dl3 = ArticuloManufacturadoDetalle.builder().cantidad(1).insumo(carne).build();

        ArticuloManufacturado pizza = ArticuloManufacturado.builder()
                .denominacion("Pizza Hawaiana").precioVenta(6500.0)
                .categoria(catPizzas).unidadMedida(gr)
                .descripcion("Pizza con jamón y ananá").tiempoEstimadoMinutos(25).preparacion("Hornear 25 min")
                .build();
        pizza.addImagen(img1); pizza.addImagen(img2); pizza.addImagen(img3);
        pizza.addDetalle(dp1); pizza.addDetalle(dp2); pizza.addDetalle(dp3);

        ArticuloManufacturado lomo = ArticuloManufacturado.builder()
                .denominacion("Lomo Completo").precioVenta(7200.0)
                .categoria(catLomos).unidadMedida(gr)
                .descripcion("Lomo con todo").tiempoEstimadoMinutos(15).preparacion("Plancha 15 min")
                .build();
        lomo.addImagen(img4); lomo.addImagen(img5); lomo.addImagen(img6);
        lomo.addDetalle(dl1); lomo.addDetalle(dl2); lomo.addDetalle(dl3);

        ARTICULOS.add(pizza); ARTICULOS.add(lomo);

        // ---------------- INTEGRACIÓN: inventario por Sucursal ----------------
        // Vía helper
        suc1.agregarAlInventario(pizza, 5, null);        // 5 pizzas, precio de lista
        suc1.agregarAlInventario(harina, 30, null);      // insumo en góndola
        suc2.agregarAlInventario(lomo, 3, 6990.0);       // override de precio en suc2
        suc2.agregarAlInventario(aceite, 10, null);

        // Instancia EXPLÍCITA (para demostrar ambas formas)
        SucursalArticulo itemExplicito = SucursalArticulo.builder()
                .sucursal(suc3)
                .articulo(pizza)
                .stock(2)
                .precioOverride(6800.0)
                .build();
        suc3.getInventario().add(itemExplicito);

        // Demostración completa (Mostramos funcionamiento de todas las clases)
        titulo("Empresas y Sucursales");
        print(EMPRESAS);

        titulo("Categorías");         print(CATS);
        titulo("Unidades de Medida"); print(UMS);

        titulo("Artículos INSUMO");
        print(ARTICULOS.stream().filter(a -> a instanceof ArticuloInsumo).collect(Collectors.toList()));

        titulo("Artículos MANUFACTURADOS");
        print(ARTICULOS.stream().filter(a -> a instanceof ArticuloManufacturado).collect(Collectors.toList()));

        titulo("Detalles de Pizza Hawaiana");
        ((ArticuloManufacturado) ARTICULOS.stream().filter(a -> a.getDenominacion().equals("Pizza Hawaiana")).findFirst().get())
                .getDetalles().forEach(System.out::println);

        titulo("Inventario por sucursal");
        for (Sucursal s : Arrays.asList(suc1, suc2, suc3, suc4)) {
            System.out.println("-> " + s.getNombre());
            s.getInventario().forEach(System.out::println);
        }

        // CRUD de manufacturados
        titulo("Buscar manufacturado por ID (pizza)");
        System.out.println(findManuById(pizza.getId()).orElse(null));

        titulo("Actualizar precio de pizza por ID");
        updateManuPrecio(pizza.getId(), 6999.0);
        System.out.println(findManuById(pizza.getId()).orElse(null));

        titulo("Eliminar manufacturado por ID (lomo)");
        deleteManuById(lomo.getId());
        print(ARTICULOS.stream().filter(a -> a instanceof ArticuloManufacturado).collect(Collectors.toList()));

        // Mini-CRUD inventario (stock/precio) usando la instancia explícita
        titulo("Actualizar stock/precio en item explícito (suc3/pizza)");
        itemExplicito.setStock(4);
        itemExplicito.setPrecioOverride(6700.0);
        System.out.println(itemExplicito);

        titulo("Eliminar del inventario (suc1: aceite)");
        suc2.getInventario().removeIf(i -> i.getArticulo().getId().equals(aceite.getId()));
        suc2.getInventario().forEach(System.out::println);
    }
}
