package Entidades;


import Excepciones.StockInsuficiente;
import Excepciones.VidaUtilInsuficiente;
import Servicios.CocinaService;
import Servicios.DespensaService;

import java.util.Random;
import java.util.concurrent.Callable;

public class Chef implements Callable<Void>{
    private String nombre;
    private int estrellasMichelin;
    private Despensa despensa;
    private Receta receta;

    public Chef(String nombre, int estrellasMichelin, Despensa despensa, Receta receta){
        this.estrellasMichelin = estrellasMichelin;
        this.nombre = nombre;
        this.despensa = despensa;
        this.receta = receta;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public int getEstrellasMichelin(){
        return estrellasMichelin;
    }

    public void setEstrellasMichelin(int estrellasMichelin){
        this.estrellasMichelin = estrellasMichelin;
    }

    public Despensa getDespensa() {
        return despensa;
    }

    public void setDespensa(Despensa despensa) {
        this.despensa = despensa;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }

    public void cocinar(Receta receta) throws VidaUtilInsuficiente, StockInsuficiente, InterruptedException {
        Random random = new Random();
        int randomNumber = random.nextInt(10);
        Thread.sleep(randomNumber * 1000);

        System.out.println("Hola soy: " + nombre);
        CocinaService cocina = CocinaService.getInstance();
        cocina.prepararReceta(receta, despensa);
    }

    public void renovarDespensa(){
        DespensaService.renovarUtensilios(despensa);
    }

    @Override
    public String toString(){
        return "Nombre: " + nombre + ", Cantidad de estrellas michelin: " + estrellasMichelin;
    }

    @Override
    public Void call() throws Exception{
        cocinar(receta);
        return null;
    }
}


