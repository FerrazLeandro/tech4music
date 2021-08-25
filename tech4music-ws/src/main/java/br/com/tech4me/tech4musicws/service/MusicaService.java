package br.com.tech4me.tech4musicws.service;

import java.util.List;
import java.util.Optional;

import br.com.tech4me.tech4musicws.shared.MusicaDto;

public interface MusicaService {
    List<MusicaDto> listarMusicas();
    MusicaDto cadastrarMusica(MusicaDto musica);
    Optional<MusicaDto> atualizarMusica(String id, MusicaDto filme);
    void excluirMusica(String id);
}
