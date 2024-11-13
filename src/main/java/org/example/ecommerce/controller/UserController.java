package org.example.ecommerce.controller;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.dto.UserRequestDto;
import org.example.ecommerce.dto.UserResponseDto;
import org.example.ecommerce.entity.User;
import org.example.ecommerce.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
private final UserService userService;

  @PostMapping("/register")
   public ResponseEntity<UserResponseDto> register(@RequestBody UserRequestDto userRequestDto){
      return ResponseEntity.ok(userService.register(userRequestDto));
  }


  @PostMapping("/login")
  public ResponseEntity<String> login(@RequestBody UserRequestDto userRequestDto){
      return ResponseEntity.ok(userService.verify(userRequestDto));
  }


    @GetMapping
    public String SayHi(){
        return "helo aysu ";
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUser( @PathVariable Long id) {
       return ResponseEntity.ok( userService.getUser(id));
    }



    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@RequestBody UserRequestDto userRequestDto, @RequestBody @PathVariable Long id) {
        userService.editUser(id, userRequestDto);
        return ResponseEntity.ok().build();
    }
}
