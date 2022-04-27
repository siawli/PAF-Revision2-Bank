package revision.pafrevision2.demo274.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import revision.pafrevision2.demo274.service.FundsTransferService;

@RestController
public class AccountRestController {

    @Autowired
    private FundsTransferService fundsTransferSvc;

    @PostMapping("/transfer")
    public ResponseEntity<String> fundsTransfer(
            @ModelAttribute("fromAcct") String fromAcct,
            @ModelAttribute("toAcct") String toAcct,
            @ModelAttribute("amount") Float amount) {

        try {
            fundsTransferSvc.fundsTransfer(fromAcct, toAcct, amount);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }

        return ResponseEntity.ok().body("ok");
        
    }    
}
