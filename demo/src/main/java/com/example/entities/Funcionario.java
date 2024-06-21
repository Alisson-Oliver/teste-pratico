package com.example.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

/*
 * Classe Funcionario
 * Esta classe é uma subclasse de Pessoa
 */
public class Funcionario extends Pessoa{
    
    /*
     * Atributos
     */
    private BigDecimal salario;
    private String funcao;

    public Funcionario(){
    }

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((funcao == null) ? 0 : funcao.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Funcionario other = (Funcionario) obj;
        if (funcao == null) {
            if (other.funcao != null)
                return false;
        } else if (!funcao.equals(other.funcao))
            return false;
        return true;
    }
}
