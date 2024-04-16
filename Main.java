import Entidades.*;
import Interfaces.IDespensable;
import Recetas.ArrozBlanco;
import Recetas.HuevoDuro;
import Recetas.HuevosRevueltos;

import java.time.LocalDate;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        List<Receta> recetas = List.of(new HuevoDuro(), new HuevosRevueltos(), new ArrozBlanco());

        List<IDespensable> despensables = List.of(
                new Ingrediente("Arroz", 100),
                new Ingrediente("Agua", 100),
                new Ingrediente("Sal", 100),
                new Utensilio("Olla", 100),
                new Ingrediente("Huevo", 100),
                new Ingrediente("Aceite", 100),
                new Utensilio("Tazón", 100),
                new Utensilio("Sartén", 100),
                new Utensilio("Cucharon", 100)
        );

        List<Despensa> despensas = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Despensa despensa = new Despensa();
            for (IDespensable despensable : despensables) {
                String nombreDespensable = "";
                if (despensable instanceof Ingrediente) {
                    nombreDespensable = ((Ingrediente) despensable).getNombre();
                } else if (despensable instanceof Utensilio) {
                    nombreDespensable = ((Utensilio) despensable).getNombre();
                }
                despensa.addDespensable(nombreDespensable, despensable);
            }
            despensas.add(despensa);
        }

        LocalDate fechaActual = LocalDate.now();
        DayOfWeek diaSemana = fechaActual.getDayOfWeek();
        int numChefs = 0;
        if (diaSemana.compareTo(DayOfWeek.MONDAY) >= 0 && diaSemana.compareTo(DayOfWeek.THURSDAY) <= 0) {
            numChefs = 3;
        } else {
            numChefs = 5;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(numChefs);

        Random random = new Random();
        for (int i = 0; i < numChefs; i++) {
            Chef chef = new Chef("Chef " + (i+1), (i % 3) + 1, despensas.get(i), null);
            Receta receta = recetas.get(random.nextInt(recetas.size()));
            chef.setReceta(receta);

            executorService.submit(chef);
        }
        executorService.shutdown();
    }
}
