import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/*
 * Autor: Matheus Monteiro Bueno
*/
public class Conta {

    public String nome;
    private int saldo;
    private int valor;
    private boolean especial;

    /**
     * Construtor da classe Conta.
     *
     * @param nome   Nome do titular da conta
     * @param espec  Indica se o cliente é especial
     */
    public Conta(String nome, boolean especial) {
        this.nome = nome;
        this.saldo = 0;
        this.valor = 0;
        this.especial = especial;
    }

    /**
     * Configuração inicial de um cliente com saldo negativo de 200 reais na conta.
     *
     * @param cliente Cliente utilizado
     */
    @Given("^Um cliente especial com saldo atual de -(\\d+) reais$")
    public void um_cliente_especial_com_saldo_atual_de_reais(Conta cliente) {
        saldo = -200;
        if (cliente.especial) {
            System.out.println(String.format("Olá bom dia, %s seu saldo é atualmente de %d reais", cliente.nome, saldo));
        } else {
            System.out.println(String.format("Nos pague %s seu saldo é atualmente de %d reais", cliente.nome, saldo));
        }
    }

    /**
     * Define a solicitação de um saque de 100 reais na conta bancaria de um cliente especial e de 200 reais de um cliente comum.
     *
     * @param cliente Cliente utilizado
     */
    @When("^for solicitado um saque no valor de (\\d+) reais$")
    public void for_solicitado_um_saque_no_valor_de_reais(Conta cliente) {
        if (cliente.especial) {
            valor = 100;
            System.out.println(String.format("Atenção %s Seu saldo é de %d e você quer realizar um saque de %d reais", cliente.nome, saldo, valor));
        } else {
            valor = 200;
            System.out.println(String.format("Atenção %s Seu saldo é de %d e você quer realizar um saque de %d reais", cliente.nome, saldo, valor));
        }
    }

    /**
     * Verificação do processo correto do saque e atualização do saldo da conta.
     *
     * @param cliente Cliente utilizado
     */
    @Then("^deve efetuar o saque e atualizar o saldo da conta para -(\\d+) reais$")
    public void deve_efetuar_o_saque_e_atualizar_o_saldo_da_conta_para_reais(Conta cliente) {
        if (cliente.especial) {
            cliente.saldo = cliente.saldo - valor;
            valor = 0;
            check_more_outcomes(cliente);
        } else {
            if (cliente.saldo < valor) {
                System.out.println(String.format("Desculpe, %s você não pode efetuar um saque de %d reais", cliente.nome, valor));
            } else {
                cliente.saldo = cliente.saldo - valor;
                valor = 0;
                check_more_outcomes(cliente);
            }
        }
    }

    /**
     * Verifica resultados adicionados e avisa o cliente após a operação de saque.
     *
     * @param cliente Cliente utilizado
     */
    @Then("^check more outcomes$")
    public void check_more_outcomes(Conta cliente) {
        if (cliente.valor == 0) {
            System.out.println(String.format("Parabéns %s seu saque foi concluído e seu saldo atual é de %d reais", cliente.nome, cliente.saldo));
        } else {
            valor = 0;
        }
    }
}