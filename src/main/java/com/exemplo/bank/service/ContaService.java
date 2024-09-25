package com.exemplo.bank.service;

import com.exemplo.bank.model.Conta;
import com.exemplo.bank.model.Transacao;
import com.exemplo.bank.repository.ContaRepository;
import com.exemplo.bank.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Date;

@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public List<Conta> listarContas() {
        return contaRepository.findAll();
    }

    public Conta buscarPorId(Long id) {
        return contaRepository.findById(id).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
    }

    public Conta criarConta(Conta conta) {
        return contaRepository.save(conta);
    }

    public void deletarConta(Long id) {
        contaRepository.deleteById(id);
    }

    public Conta realizarTransacao(Long id, Double valor, String tipo) {
        Conta conta = buscarPorId(id);

        if (tipo.equals("DEPOSITO")) {
            conta.depositar(valor);
        } else if (tipo.equals("SAQUE")) {
            if (conta.getSaldo() < valor) {
                throw new RuntimeException("Saldo insuficiente");
            }
            conta.sacar(valor);
        } else {
            throw new RuntimeException("Tipo de transação inválido");
        }

        // Cria e registra a transação
        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setData(new Date());
        transacao.setValor(valor);
        transacao.setTipo(tipo);

        transacaoRepository.save(transacao);
        return contaRepository.save(conta);
    }
}
