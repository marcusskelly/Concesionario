package com.marcus.concesionario;

import com.marcus.concesionario.modelo.Coche;
import com.marcus.concesionario.repositorios.AlquileresRepository;
import com.marcus.concesionario.repositorios.CocheRepository;
import com.marcus.concesionario.repositorios.CochesAlquiladosRepository;
import com.marcus.concesionario.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConcesionarioApplication implements CommandLineRunner {


    @Autowired
    AlquileresRepository alquileresRepository;

    @Autowired
    CocheRepository cocheRepository;

    @Autowired
    CochesAlquiladosRepository cochesAlquiladosRepository;

    @Autowired
    UsuarioRepository usuarioRepository;


    public static void main(String[] args) {
        SpringApplication.run(ConcesionarioApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Coche coche1 = new Coche("Toyota","7149GYX",235.90f,3f,1,"https://www.toyota.com/imgix/content/dam/toyota/jellies/max/2022/prius/nightshadeeditionawd-e/1266/040/2.png?fm=webp&bg=white&w=768&h=328&q=90");
        Coche coche2 = new Coche("Mazda","3450LBP",287.90f,2f,2,"https://cgi.cdn.mazda.media/resized/0bd816a6b2e0ce6e35c88c3d780b1cf78da2ab068effdd457ae1b13dad733e75/MERGED/6/36.jpg");
        Coche coche3 = new Coche("Audi","2610LMV",830.90f,2f,3,"https://mediaservice.audi.com/media/live/50900/fly1400x601n1/f83rj7/2022.png?wid=850");
        Coche coche5 = new Coche("Hyundai","2376JPL",265.90f,10f,5,"https://motor.elpais.com/wp-content/uploads/2021/11/Hyundai-i30_N-2021-1600-0c-1800x728.jpg?v=1637053890");
        Coche coche8 = new Coche("Tesla","2307KYT",540.90f,1f,8,"https://motor.elpais.com/wp-content/uploads/2020/09/Tesla-Model-S-2-1800x728.jpg");
        Coche coche10 = new Coche("Citroen","3246JKT",212.90f,4f,10,"https://www.citroen.es/content/dam/citroen/master/b2c/models/new-c4-e/bbc/e_C4_Rouge_Elixir_576_324.png");
        Coche coche11 = new Coche("BMW","0126JDM",456.90f,2f,11,"https://www.bmw.es/content/dam/bmw/common/all-models/1-series/5-door/2021/navigation/bmw-1-series-modelfinder.png");
        Coche coche12 = new Coche("Mercedes","1235JJJ",710.90f,6f,12,"https://imgs.coches.com/coches/renting/mercedes/clase-a-hibrido-enchufable/high.png");

        cocheRepository.save(coche1);
        cocheRepository.save(coche2);
        cocheRepository.save(coche3);
        cocheRepository.save(coche5);
        cocheRepository.save(coche8);
        cocheRepository.save(coche10);
        cocheRepository.save(coche11);
        cocheRepository.save(coche12);

    }
}
