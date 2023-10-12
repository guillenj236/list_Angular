package app.service;

import app.dto.LivroDTO;
import app.entity.Livro;
import app.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public List<LivroDTO> listAll(){
        List<Livro> lista = livroRepository.findAll();
        List<LivroDTO> listaDTO = new ArrayList<>();

        for(int i=0; i<lista.size(); i++)
            listaDTO.add(this.tolivroDTO(lista.get(i)));

        return listaDTO;
    }
    public void delete(final Long id) {
        final Livro livroBanco = this.livroRepository.findById(id).orElse(null);
        this.livroRepository.delete(livroBanco);
    }
    public LivroDTO save(LivroDTO livroDTO){
        Livro livro = this.tolivro(livroDTO);

        Livro livrosalva = livroRepository.save(livro);

        return this.tolivroDTO(livrosalva);
    }



    private LivroDTO tolivroDTO(Livro livro) {
        LivroDTO livroDTO = new LivroDTO();
        livroDTO.setId(livro.getId());
        livroDTO.setAutor(livro.getAutor());
        livroDTO.setTitulo(livro.getTitulo());
        return livroDTO;
    }

    private Livro tolivro(LivroDTO livroDTO) {
        Livro livro = new Livro();
        livro.setId(livroDTO.getId());
        livro.setAutor(livroDTO.getAutor());
        livro.setTitulo(livroDTO.getTitulo());
        return livro;
    }
}
