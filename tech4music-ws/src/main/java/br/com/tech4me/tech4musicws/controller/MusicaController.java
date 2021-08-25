package br.com.tech4me.tech4musicws.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.tech4me.tech4musicws.service.MusicaService;
import br.com.tech4me.tech4musicws.shared.MusicaDto;


@RestController
@RequestMapping("/api/musicas")
public class MusicaController {
    @Autowired
    MusicaService servico;

    @GetMapping
    public List<MusicaDto> listarMusicas(){
        return servico.listarMusicas();
    }

    @PostMapping
    public MusicaDto cadastrarMusica(@RequestBody MusicaDto musica) {   
        return servico.cadastrarMusica(musica);
    }
    
    @PutMapping(value="{id}")
    public MusicaDto atualizarMusica(@PathVariable String id, @RequestBody MusicaDto musica) {
        Optional<MusicaDto> musicaParaAtualizar = servico.atualizarMusica(id, musica);  

        if (musicaParaAtualizar.isPresent()){
            return musicaParaAtualizar.get(); 
        }
        return null;
    }

    @DeleteMapping(value="/{id}")
    public void excluirMusica(@PathVariable String id){
        servico.excluirMusica(id);
    }

}
