package com.example.transporte.controller;

import com.example.transporte.exception.ResourceNotFoundException;
import com.example.transporte.model.Usuario;
import com.example.transporte.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    public UsuarioController(UsuarioService usuarioService, PasswordEncoder passwordEncoder) {
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping
    public Usuario crearUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }

    @GetMapping
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuarioPorId(@PathVariable Long id) throws Exception {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @Valid @RequestBody Usuario usuario) throws Exception {
        return usuarioService.actualizarUsuario(id, usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
    }
    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody Map<String, String> credenciales) {
        String dni = credenciales.get("dni");
        String contraseña = credenciales.get("contraseña");

        try {
            Usuario usuarioExistente = usuarioService.obtenerUsuarioPorDni(dni);

            // Verificar la contraseña
            if (passwordEncoder.matches(contraseña, usuarioExistente.getContraseña())) {
                // Las credenciales son correctas
                // Puedes devolver la información necesaria para el frontend
                return ResponseEntity.ok(usuarioExistente);
            } else {
                // Contraseña incorrecta
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Contraseña incorrecta");
            }
        } catch (ResourceNotFoundException e) {
            // Usuario no encontrado
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        }
    }
}