@Service
public class UbicacionService {
    @Autowired
    private RepoUbicacion ubicacionRepository;
    //agrego esto para probar si llego chocar contra la base
    //tengo que probar pero me dio noni
    public List<Ubicacion> obtenerUbicacionesCercanas(double latitud, double longitud, double radio) {
        return ubicacionRepository.encontrarCercanas(latitud, longitud, radio);
    }
}