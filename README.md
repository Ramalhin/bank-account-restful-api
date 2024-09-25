# Diagrama de Classes - API de Conta Bancária

```mermaid
classDiagram
    class Conta {
        +Long id
        +String numeroConta
        +String titular
        +Double saldo
        +depositar(Double valor)
        +sacar(Double valor)
    }

    class Transacao {
        +Long id
        +Date data
        +Double valor
        +String tipo
        +Conta conta
        +registrarTransacao()
    }

    class ContaController {
        +List<Conta> listarContas()
        +Conta obterConta(Long id)
        +Conta criarConta(Conta conta)
        +Conta atualizarConta(Long id, Conta conta)
        +void deletarConta(Long id)
    }

    class ContaService {
        +List<Conta> listarContas()
        +Conta obterConta(Long id)
        +Conta criarConta(Conta conta)
        +Conta atualizarConta(Long id, Conta conta)
        +void deletarConta(Long id)
        +void realizarTransacao(Long id, Double valor, String tipo)
    }

    class ContaRepository {
        +Conta findById(Long id)
        +List<Conta> findAll()
        +Conta save(Conta conta)
        +void deleteById(Long id)
    }

    Conta --> Transacao : "1 to N"
    ContaController --> ContaService : "calls"
    ContaService --> ContaRepository : "uses"
