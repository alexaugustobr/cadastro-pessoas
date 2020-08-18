package br.com.softplan.pessoas.domain.model;

public enum Genero {

    MASCULINO('M'),
    FEMININO('F');

    private char sigla;

    Genero(char sigla) {
        this.sigla = sigla;
    }

    public char getSigla() {
        return sigla;
    }
}
