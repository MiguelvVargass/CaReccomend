package JMC.car.Controller;

import JMC.car.Entity.Vehiculo;
import JMC.car.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;


@RestController
@RequestMapping("api/vehiculo")
@RequiredArgsConstructor
public class VehiculoController {

    private final VehiculoService vehiculoService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Vehiculo>> findAll(){
        return ResponseEntity.ok().body(vehiculoService.listAllVehiculos());
    }

}
