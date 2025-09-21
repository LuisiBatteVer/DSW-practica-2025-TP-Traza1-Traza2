# Sistema de Gestión — Traza 1 + Traza 2 (Java + Lombok)

Este repo integra dos dominios:
- **Traza 1:** País → Provincia → Localidad → Domicilio → Sucursal → Empresa  
- **Traza 2:** Artículo → (Insumo | Manufacturado) → Detalle → Categoría → Imagen → UnidadMedida

La unión se resuelve con la clase puente **`SucursalArticulo`**, que modela el **inventario por sucursal** (stock y precio opcional por sucursal) sin acoplar innecesariamente los modelos.

**Tech & criterios:**
- Java + **Lombok** (getters/setters, builders, `equals/hashCode` controlado).
- Multiplicidades con **`HashSet`**.
- Relaciones consistentes vía helpers (ej.: `Sucursal.agregarAlInventario(...)`).
- `toString` limpio (excluye colecciones/back-refs).

**Qué muestra el `main`:**
- Alta de entidades de Traza 1 y Traza 2.
- Integración cargando inventario (helper + **instancia explícita** de `SucursalArticulo`).
- Listados y CRUD básico sobre **artículos manufacturados** + actualización de stock/precio por sucursal.

**Estructura de paquetes:**
- traza.app/ -> App (demo de ejecución)
- traza.traza1/ -> País, Provincia, Localidad, Domicilio, Sucursal, Empresa
- traza.traza2/ -> Artículo, Insumo, Manufacturado, Detalle, Categoría, Imagen, UnidadMedida
- traza.Integracion/ -> SucursalArticulo (inventario por sucursal)

