package edu.unideh.GymPull.controllers;

import edu.unideh.GymPull.dto.LoginRequest;
import edu.unideh.GymPull.entity.User;
import edu.unideh.GymPull.repositories.UserRepository;
import edu.unideh.GymPull.services.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ResolutionException;
import java.time.LocalDateTime;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private EncryptionService encryptionService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();

            // Buscar el usuario por email
            Optional<User> userOptional = userRepository.findByEmail(email);

            // Verificar si el usuario existe
            if (!userOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }

            User user = userOptional.get();

            // Encriptar la contraseña proporcionada por el usuario
            String encryptedPassword = encryptionService.encryptPassword(password);

            // Comparar la contraseña encriptada
            if (!user.getPassword().equals(encryptedPassword)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
            }

            return ResponseEntity.ok("Inicio de sesión exitoso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud de inicio de sesión: " + e.getMessage());
        }
    }
    @GetMapping("/getUser/{email}")
    public Optional<User> getUser(@PathVariable String email) {
        return userRepository.findByEmail(email);
    }


    @PostMapping("/saveUser")
    public ResponseEntity<?> signup(@RequestBody User user) {
        try {
            // Verificar si el correo electrónico ya existe en la base de datos
            String encryptedPassword = encryptionService.encryptPassword(user.getPassword());
            user.setPassword(encryptedPassword);
            user.setFecha_registro(LocalDateTime.now());

            // Guardar el nuevo usuario
            userRepository.save(user);

            return ResponseEntity.ok("Usuario guardado exitosamente");
        } catch (DataIntegrityViolationException e) {
            // Manejar la excepción de violación de integridad (email duplicado)
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo electrónico ya está registrado");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al guardar el usuario: " + e.getMessage());
        }
    }

    @PostMapping("/confirmarcarro")
    public ResponseEntity<?> confirmarCarro(@RequestBody LoginRequest loginRequest) {
        try {
            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();

            // Buscar el usuario por email
            Optional<User> userOptional = userRepository.findByEmail(email);

            // Verificar si el usuario existe
            if (!userOptional.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }

            User user = userOptional.get();

            // Encriptar la contraseña proporcionada por el usuario
            String encryptedPassword = encryptionService.encryptPassword(password);

            // Comparar la contraseña encriptada
            if (!user.getPassword().equals(encryptedPassword)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
            }

            // Retornar el id del usuario
            return ResponseEntity.ok(user.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar la solicitud: " + e.getMessage());
        }
    }





}

