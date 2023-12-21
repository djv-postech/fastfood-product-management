package com.fiap.postech.fastfood.product.management.application.api.auth;


import com.fiap.postech.fastfoodsystemcore.domain.vo.CPF;
import com.fiap.postech.fastfoodsysteminfra.security.JwtTokenUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autenticacao")
@Tag(name = "Autenticação", description = "Rest api para operações de gerenciamento de json web tokens.")
public class JwtAuthenticationController {

    //FIXME - Criar bean de config
    @Autowired
    JwtTokenUtils jwtTokenUtil;

    @Operation(summary = "Validar token")
    @GetMapping("/validar/{token}")
    public boolean validarToken(@PathVariable String token) {
        return jwtTokenUtil.validarToken(token);
    }

    @Operation(summary = "Gerar novo token")
    @GetMapping(value = "/autenticar/{cpf}")
    public ResponseEntity<?> gerarToken(@PathVariable String cpf) {
        CPF clienteCPF = new CPF(cpf);

        String token = jwtTokenUtil.gerarToken(clienteCPF.getNumero());

        return ResponseEntity.ok(new JwtResponse(token));
    }
}