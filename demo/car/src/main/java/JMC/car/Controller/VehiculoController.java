package JMC.car.Controller;

import JMC.car.Entity.Vehiculo;
import JMC.car.dto.VehiculoSearchRequest;
import JMC.car.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import java.util.List;


@RestController
@RequestMapping("api/vehiculo")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // permitir llamadas desde el front (ajustar origen en producción)
public class VehiculoController {

    private final VehiculoService vehiculoService;


    @GetMapping("/findAll")
    public ResponseEntity<List<Vehiculo>> findAll(){
        return ResponseEntity.ok().body(vehiculoService.listAllVehiculos());
    }

    @PostMapping(value = "/nearest", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehiculo> findNearest(@RequestBody VehiculoSearchRequest req){
        Vehiculo resultado = vehiculoService.buscarMasCercanoPorParametros(
                req.isEsRural(),
                req.isEsManual(),
                req.getCantidadPasajeros(),
                req.getRuido(),
                req.getPrecio(),
                req.getPropulsion()
        );

        if (resultado == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(resultado);
    }

    // GET alternative: /api/vehiculo/nearest?esRural=true&esManual=false&cantidadPasajeros=4&ruido=0.5&precio=20000&propulsion=1
    @GetMapping(value = "/nearest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vehiculo> findNearestByQuery(
            @RequestParam boolean esRural,
            @RequestParam boolean esManual,
            @RequestParam int cantidadPasajeros,
            @RequestParam double ruido,
            @RequestParam double precio,
            @RequestParam int propulsion
    ){
        Vehiculo resultado = vehiculoService.buscarMasCercanoPorParametros(esRural, esManual, cantidadPasajeros, ruido, precio, propulsion);
        if (resultado == null) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(resultado);
    }

}
