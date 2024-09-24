package com.exemplo.bank.service;

import com.exemplo.bank.model.Conta;
import com.exemplo.bank.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    public Conta buscarPorId(Long id) {
        return contaRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public Conta criarConta(Conta conta) {
        return contaRepository.save(conta);
    }

    public Conta atualizarSaldo(Long id, double novoSaldo) {
        Conta conta = buscarPorId(id);
        conta.setSaldo(novoSaldo);
        return contaRepository.save(conta);
    }

    public void deletarConta(Long id) {
        contaRepository.deleteById(id);
    }
}
