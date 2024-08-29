@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {
    @Query("SELECT u FROM Ubicacion u WHERE FUNCTION('distance', u.latitud, u.longitud, :lat, :lng) < :radio")
    List<Ubicacion> encontrarCercanas(@Param("lat") double latitud, @Param("lng") double longitud, @Param("radio") double radio);
}