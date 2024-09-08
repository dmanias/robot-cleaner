package temp;

import com.demo.robot_cleaner.model.HooverRequest;
import com.demo.robot_cleaner.model.HooverResponse;
import com.demo.robot_cleaner.service.HooverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hoover")
public class HooverController {

    @Autowired
    private HooverService hooverService;

    @PostMapping("/navigate")
    public ResponseEntity<HooverResponse> navigateRoom(@Valid @RequestBody HooverRequest request) {
        HooverResponse response = hooverService.processHooverMovement(request);
        return ResponseEntity.ok(response);
    }
}
