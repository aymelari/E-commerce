package org.example.ecommerce.service;

import lombok.RequiredArgsConstructor;
import org.example.ecommerce.dto.UserRequestDto;
import org.example.ecommerce.dto.UserResponseDto;
import org.example.ecommerce.entity.Order;
import org.example.ecommerce.entity.User;
import org.example.ecommerce.repository.OrderRepository;
import org.example.ecommerce.repository.UserRepository;
import org.example.ecommerce.security.JWTService;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final OrderRepository orderRepository;
    private final AuthenticationManager authenticationManager;
   private final JWTService jwtService;
    private BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(12);

    public UserResponseDto register(UserRequestDto userRequestDto){
        String password = userRequestDto.getPassword();
        String encodedPassword= bCryptPasswordEncoder.encode(password);


        Set<Order> orders=new HashSet<>();
        Set<Long> orderIds = userRequestDto.getOrderId();
        if (orderIds == null) {
            orderIds = new HashSet<>();
        }
        for(Long orderId1 : orderIds){
            Order order = orderRepository.findById(orderId1).orElse(null);
            if (order != null){
                orders.add(order);
            }}
        User user = User.builder()
                .username(userRequestDto.getUsername())
                .password(encodedPassword)
                .email(userRequestDto.getEmail())
                .role(userRequestDto.getRole())
                .order(orders).build();


            userRepository.save(user);

            return UserResponseDto.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .orderId(orderIds).build();

    }

    public String verify(UserRequestDto userRequestDto){
        Authentication authentication =
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequestDto.getUsername(),userRequestDto.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(userRequestDto.getUsername());
        }
        return "fail";
    }





    public UserResponseDto getUser(Long id)
    {
        User user = userRepository.findById(id).orElse(null);
        Set<Long> collect = user.getOrder().stream().map(order -> order.getId()).collect(Collectors.toSet());
        return UserResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .role(user.getRole())
                .orderId(collect).build();
    }


    public void editUser(Long id, UserRequestDto userRequestDto){
        User user = userRepository.findById(id).orElse(null);
        Set<Long> orderId = userRequestDto.getOrderId();

        Set<Order> orders=new HashSet<>();

        for(Long orderId1 : orderId){
            Order order = orderRepository.findById(orderId1).orElse(null);
            if (order != null){
                orders.add(order);
            }
        }


        User build = User.builder()
                .id(id)
                .username(userRequestDto.getUsername())
                .password(userRequestDto.getPassword())
                .email(userRequestDto.getEmail())
                .role(userRequestDto.getRole())
                .order(orders).build();

        userRepository.save(build);
    }
}