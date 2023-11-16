package enums;

import java.util.Arrays;
import java.util.List;

public enum Nacionalidades {
    AMERICANO("Americano"),
    CANADIAN("Canadense"),
    MEXICANO("Mexicano"),
    GROENLANDES("Groenlandês"),
    BERMUDIANO("Bermudiano"),
    PORTO_RIQUENHO("Porto Riquenho"),
    ARGENTINO("Argentino"),
    BRAZILEIRO("Brazileiro"),
    CHILENO("Chileno"),
    COLOMBIANO("Colombiano"),
    PERUANO("Peruano"),
    VENEZUELANO("Venezuelano");


    private final String descricao;
    Nacionalidades(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public static List<Nacionalidades> getNacionalidades(){
        return Arrays.asList(Nacionalidades.values());
    }

}
