package enums;

public enum Papel {
    ADMIN("Administrador"),
    TECNICO("Tecnico")
    ;


    private final String descricao;
    Papel(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
