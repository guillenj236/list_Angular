package app.service;

import app.dto.CarroDTO;
import app.entity.Carro;
import app.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CarroService {
    @Autowired
    private CarroRepository carroRepository;

    public List<CarroDTO> listAll(){
        List<Carro> lista = carroRepository.findAll();
        List<CarroDTO> listaDTO = new ArrayList<>();

        for(int i=0; i<lista.size(); i++)
            listaDTO.add(this.tocarroDTO(lista.get(i)));

        return listaDTO;
    }
    public void delete(final Long id) {
        final Carro carroBanco = this.carroRepository.findById(id).orElse(null);
        this.carroRepository.delete(carroBanco);
    }
    public CarroDTO save(CarroDTO carroDTO){
        Carro carro = this.tocarro(carroDTO);

        Carro carrosalva = carroRepository.save(carro);

        return this.tocarroDTO(carrosalva);
    }



    private CarroDTO tocarroDTO(Carro carro) {
        CarroDTO carroDTO = new CarroDTO();
        carroDTO.setId(carro.getId());
        carroDTO.setNome(carro.getNome());
        carroDTO.setAno(carro.getAno());
        return carroDTO;
    }

    private Carro tocarro(CarroDTO carroDTO) {
        Carro carro = new Carro();
        carro.setId(carroDTO.getId());
        carro.setNome(carroDTO.getNome());
        carro.setAno(carroDTO.getAno());
        return carro;
    }
}
