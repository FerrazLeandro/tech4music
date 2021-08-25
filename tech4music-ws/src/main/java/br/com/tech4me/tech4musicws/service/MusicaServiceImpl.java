package br.com.tech4me.tech4musicws.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.tech4me.tech4musicws.model.Musica;
import br.com.tech4me.tech4musicws.repository.MusicaRepository;
import br.com.tech4me.tech4musicws.shared.MusicaDto;

@Service
public class MusicaServiceImpl implements MusicaService{
    @Autowired
    MusicaRepository repositorio;

    @Override
    public List<MusicaDto> listarMusicas() {
        List<Musica> musicas = repositorio.findAll();
    
        return musicas.stream().map(m -> new ModelMapper().map(m, MusicaDto.class)).collect(Collectors.toList());
    }

    @Override
    public MusicaDto cadastrarMusica(MusicaDto musica) {
        Musica gravarMusica = new ModelMapper().map(musica, Musica.class);
        gravarMusica = repositorio.save(gravarMusica);

        return new ModelMapper().map(gravarMusica, MusicaDto.class);
    }

    @Override
    public Optional<MusicaDto> atualizarMusica(String id, MusicaDto musica) {
        Optional<Musica> musicaOriginal = repositorio.findById(id);

        if (musicaOriginal.isPresent()){
           Musica musicaParaAtualizar = new ModelMapper().map(musica, Musica.class);
           musicaParaAtualizar.setId(id);
           musicaParaAtualizar = repositorio.save(musicaParaAtualizar);
           return Optional.of(new ModelMapper().map(musicaParaAtualizar, MusicaDto.class));
        }
        return Optional.empty();
    }

    @Override
    public void excluirMusica(String id) {
        repositorio.deleteById(id);
        
    }


}
