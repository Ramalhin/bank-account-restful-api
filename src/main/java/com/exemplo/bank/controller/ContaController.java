package com.exemplo.bank.controller;

import com.exemplo.bank.model.Conta;
import com.exemplo.bank.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @GetMapping
    public List<Conta> listarContas() {
        return contaService.listarContas();
    }

    @GetMapping("/{id}")
    public Conta buscarPorId(@PathVariable Long id) {
        return contaService.buscarPorId(id);
    }

    @PostMapping
    public Conta criarConta(@RequestBody Conta conta) {
        return contaService.criarConta(conta);
    }

    @PutMapping("/{id}/saldo")
    public Conta atualizarSaldo(@PathVariable Long id, @RequestParam double novoSaldo) {
        return contaService.atualizarSaldo(id, novoSaldo);
    }

    @DeleteMapping("/{id}")
    public void deletarConta(@PathVariable Long id) {
        contaService.deletarConta(id);
    }
}
