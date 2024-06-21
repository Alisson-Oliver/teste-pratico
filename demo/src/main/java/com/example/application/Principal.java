package com.example.application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.entities.Funcionario;

public class Principal {
    public static void main(String[] args) {

        // =================== 3.1 - Inserir todos os funcionários, na mesma ordem e informações da tabela acima =============================

        // Cria uma lista de funcionários.
        List<Funcionario> funcionarios = new ArrayList<>();
        
        // 10 funcionários instanciados e adicionados à lista de funcionários
        funcionarios.add(new Funcionario("Maria", LocalDate.parse("2000-10-18"), BigDecimal.valueOf(2009.44), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.parse("1990-05-12"),  BigDecimal.valueOf(2284.38), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.parse("1961-05-02"),  BigDecimal.valueOf(9836.14), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.parse("1988-10-14"), BigDecimal.valueOf(19119.88), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.parse("1995-01-05"), BigDecimal.valueOf(2234.68), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.parse("1999-11-19"), BigDecimal.valueOf(1582.72), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.parse("1993-03-31"), BigDecimal.valueOf(4071.84), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.parse("1994-07-08"), BigDecimal.valueOf(3017.45), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.parse("2003-05-24"), BigDecimal.valueOf(1606.85), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.parse("1996-09-02"), BigDecimal.valueOf(2799.93), "Gerente"));

        // =================== 3.2 - Remover o funcionário “João” da lista.=============================

        // Remove o funcionário na posição 1 = João
        funcionarios.remove(1);

        /*  =================== 3.3 -  Imprimir todos os funcionários com todas suas informações, sendo que: =============================
            • informação de data deve ser exibido no formato dd/mm/aaaa;
            • informação de valor numérico deve ser exibida no formatado com separador de milhar como ponto e decimal como vírgula. 
            ==============================================================================================================================
        */

        // Método para formatar a data
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Método para formatar o salário
        DecimalFormat formatarSalario = new DecimalFormat("R$#,##0.00");

        // Cabeçalho da tabela
        String coluna = String.format("%-10s %-20s %-15s %-15s", "Nome", "Data de Nascimento", "Salário", "Função");
        System.out.println(coluna);
        System.out.println("*".repeat(coluna.length()));

        // Imprime os funcionários
        for (Funcionario funcionario : funcionarios) {
            String linha = String.format("%-10s %-20s %-15s %-15s", 
                                         funcionario.getNome(), 
                                         funcionario.getDataNascimento().format(formatar), 
                                         formatarSalario.format(funcionario.getSalario()),
                                         funcionario.getFuncao());
            System.out.println(linha);
        }

        // =================== 3.4 - Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionários com novo valor. =============================

        funcionarios.forEach(funcionario -> funcionario.setSalario(funcionario.getSalario().multiply(BigDecimal.valueOf(1.1))));
    
        System.out.println("\n\n\nSalários atualizados em 10%");
        listar(funcionarios);

        
        // ===================3.5 - Agrupar os funcionários por função em um MAP, sendo a chave a “função” e o valor a “lista de funcionários”. =============================

        // Filtra os funcionários com base na função
        Map<String, List<Funcionario>> agruparPorFuncao = funcionarios.stream().collect(Collectors.groupingBy(Funcionario::getFuncao));


        // =================== 3.6 – Imprimir os funcionários, agrupados por função. =============================
        System.out.println("\n\n\nFuncionários agrupados por função");

        // Imprime os funcionários agrupados por função
        for (Map.Entry<String, List<Funcionario>> entry : agruparPorFuncao.entrySet()) {
            System.out.println("\nFunção: " + entry.getKey());
            listar(entry.getValue());
        }

        // =================== Questão 3.7 =============================
        // Acredito que a questão 3.7 não foi feita ou deletada, pois não foi solicitado.

        // =================== 3.8 – Imprimir os funcionários que fazem aniversário no mês 10 e 12. =============================

        System.out.println("\n\n\nFuncionários que fazem aniversário no mês 10 e 12");

        // Filtra os funcionários que fazem aniversário no mês 10 e 12
        List<Funcionario> funcionariosAniversario = funcionarios.stream().filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 || funcionario.getDataNascimento().getMonthValue() == 12).collect(Collectors.toList());

        // Imprime os funcionários que fazem aniversário no mês 10 e 12
        listar(funcionariosAniversario);

        // =================== 3.9 – Imprimir o funcionário com a maior idade, exibir os atributos: nome e idade. =============================

        // Método para calcular a idade
        LocalDate dataAtual = LocalDate.now();
        Funcionario funcionarioMaisVelho = funcionarios.stream().max((f1, f2) -> f1.getDataNascimento().until(dataAtual).getYears() - f2.getDataNascimento().until(dataAtual).getYears()).get();
    
        System.out.println("\n\n\nFuncionário mais velho");

        // Imprime o funcionário mais velho
        System.out.println("Nome: " + funcionarioMaisVelho.getNome());

        // =================== 3.10 – Imprimir a lista de funcionários por ordem alfabética. =============================
        
        // Ordena os funcionários por nome
        funcionarios.sort((f1, f2) -> f1.getNome().toUpperCase().compareTo(f2.getNome().toUpperCase()));

        System.out.println("\n\n\nFuncionários em ordem alfabética");

        // Imprime os funcionários em ordem alfabética
        listar(funcionarios);

        // =================== 3.11 – Imprimir o total dos salários dos funcionários. =============================

        BigDecimal totalSalarios = funcionarios.stream().map(Funcionario::getSalario).reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\n\n\nTotal dos salários dos funcionários: " + formatarSalario.format(totalSalarios));
 
        // =================== 3.12 – Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00. =============================

        // Salário mínimo em BigDecimal
        BigDecimal salarioMinimo = BigDecimal.valueOf(1212.00);
        System.out.println("\n\n\nSalários em salários mínimos");

        // Imprime os salários em salários mínimos
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome() + " - " + funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.HALF_UP));
        }


    }

    /*
     * Método para listar os funcionários
     * @param funcionarios Lista de funcionários
     * @return void
     */
    public static void listar(List<Funcionario> funcionarios){
      
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat formatarSalario = new DecimalFormat("R$#,##0.00");

        String coluna = String.format("%-10s %-20s %-15s %-15s", "Nome", "Data de Nascimento", "Salário", "Função");
        System.out.println(coluna);
        System.out.println("*".repeat(coluna.length()));

        for (Funcionario funcionario : funcionarios) {
            String linha = String.format("%-10s %-20s %-15s %-15s", 
                                         funcionario.getNome(), 
                                         funcionario.getDataNascimento().format(formatar), 
                                         formatarSalario.format(funcionario.getSalario()),
                                         funcionario.getFuncao());
            System.out.println(linha);
        }
    }
}