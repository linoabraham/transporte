package com.example.transporte.service;

import com.example.transporte.model.Usuario;
import com.example.transporte.repository.UsuarioRepository;
import com.example.transporte.exception.ResourceNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario crearUsuario(Usuario usuario) {
        // Encriptar la contraseña antes de guardar
        String contraseñaEncriptada = passwordEncoder.encode(usuario.getContraseña());
        usuario.setContraseña(contraseñaEncriptada);
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario obtenerUsuarioPorId(Long id) throws Exception {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    public Usuario obtenerUsuarioPorDni(String dni) throws ResourceNotFoundException {
        return usuarioRepository.findByDni(dni)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    }

    public Usuario actualizarUsuario(Long id, Usuario usuarioActualizado) throws Exception {
        Usuario usuario = obtenerUsuarioPorId(id);
        usuario.setNombres(usuarioActualizado.getNombres());
        usuario.setApellidos(usuarioActualizado.getApellidos());
        usuario.setDni(usuarioActualizado.getDni());
        usuario.setEdad(usuarioActualizado.getEdad());
        usuario.setTelefono(usuarioActualizado.getTelefono());
        usuario.setTipoUsuario(usuarioActualizado.getTipoUsuario());
        // Encriptar la nueva contraseña si se actualiza
        if (usuarioActualizado.getContraseña() != null && !usuarioActualizado.getContraseña().isEmpty()) {
            String contraseñaEncriptada = passwordEncoder.encode(usuarioActualizado.getContraseña());
            usuario.setContraseña(contraseñaEncriptada);
        }
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
