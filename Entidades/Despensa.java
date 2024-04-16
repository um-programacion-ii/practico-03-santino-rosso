package Entidades;

import Excepciones.StockInsuficiente;
import Interfaces.IDespensable;
import Excepciones.VidaUtilInsuficiente;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Despensa {
    Map<String,IDespensable> despensableMap;

    public Despensa() {
        despensableMap = new HashMap<>();
    }

    public Map<String,IDespensable> getDespensableMap() {
        return despensableMap;
    }

    public int getCantidad(Ingrediente ingredienteNecesario) {
        int cantidad = 0;
        if (despensableMap.containsKey(ingredienteNecesario.getNombre())) {
            IDespensable despensable = despensableMap.get(ingredienteNecesario.getNombre());
            if (despensable instanceof Ingrediente) {
                cantidad = ((Ingrediente) despensable).getCantidad();
            }
        }
        return cantidad;
    }

    public int getVidaUtil(Utensilio utensilioNecesario){
        int vidaUtil = 0;
        if (despensableMap.containsKey(utensilioNecesario.getNombre())){
            IDespensable despensable = despensableMap.get(utensilioNecesario.getNombre());
            if(despensable instanceof Utensilio){
                vidaUtil = ((Utensilio) despensable).getVidaUtil();
            }
        }
        return vidaUtil;
    }


    public void addDespensable(String nombre, IDespensable despensable) {
        despensableMap.put(nombre, despensable);
    }

    public String getDespensable(String nombre, int cantidadASacar) throws VidaUtilInsuficiente, StockInsuficiente {

        IDespensable despensable = despensableMap.get(nombre);
        if (despensable != null){
            return despensable.sacar(cantidadASacar);
        }
        return "No se encontro el objeto";
    }

    @Override
    public String toString() {
        String despensa = "";
        despensa += despensableMap.values().stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
        return despensa;
    }


}
