package Dados;

import java.util.HashMap;
import java.util.Map;

public class Dados {

    public Map cadastrarcliente () {
        Map<String, Object> params =new HashMap<>();
        params.put("fristname", "Lucas");
        params.put("lastname", "Vasquez");
        params.put("email", "lucas@test30.com");

        return params;
    }
    public Map cadastrarcliente2 () {
    Map<String, Object> params =new HashMap<>();
        params.put("fristname", "Daianne");
        params.put("lastname", "Budziak");
        params.put("email", "dai@test30.com");

        return params;
    }

    public Map atualizarcliente () {
        Map<String, Object> params =new HashMap<>();
        params.put("fristname", "Lucas");
        params.put("lastname", "Oliveira");
        params.put("email", "lucas@test30.com");
        params.put("id", 1);

        return params;
    }

    public Map deletarcliente () {
        Map<String, Object> params =new HashMap<>();
        params.put("id", 1);

        return params;
    }

    public Map deletarclienteinexistente () {
        Map<String, Object> params =new HashMap<>();
        params.put("id", 1);

        return params;
    }
}
