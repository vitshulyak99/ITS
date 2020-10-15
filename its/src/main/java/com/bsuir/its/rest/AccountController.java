package com.bsuir.its.rest;

import com.bsuir.its.dto.UserDTO;
import com.bsuir.its.dto.requests.CurrentNameRequestDTO;
import com.bsuir.its.dto.requests.NameRequestDTO;
import com.bsuir.its.dto.requests.PasswordDTO;
import com.bsuir.its.service.AccountService;
import com.bsuir.its.dto.responses.NameResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class AccountController {
    private AccountService accountService;
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = "test", method = RequestMethod.POST)
    public ResponseEntity test(){
        return ResponseEntity.ok("OK!");
    }

    @RequestMapping(method = RequestMethod.POST, value = "getName")
    public ResponseEntity name(@RequestBody CurrentNameRequestDTO requestDTO){
        NameResponseDTO name = accountService.getName(requestDTO.getEmail());
        return ResponseEntity.ok(name);
    }

    @RequestMapping(method = RequestMethod.POST, value = "changeName")
    public ResponseEntity name(@RequestBody NameRequestDTO nameDTO){
        UserDTO user = accountService.setName(nameDTO);

        if(user==null){
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.ok(user);
    }

    @RequestMapping (method = RequestMethod.POST, value = "changePassword")
    public ResponseEntity changePassword(@RequestBody PasswordDTO passwordDTO) throws Exception {
            if(accountService.ChangePassword(passwordDTO)){
                return ResponseEntity.ok("Success!");
            }
            else   return ResponseEntity.ok("Failed!");

    }
}
