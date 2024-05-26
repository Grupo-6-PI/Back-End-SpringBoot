/*package school.sptech.projetotfg.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.domain.cadastro.Familia
import school.sptech.projetotfg.repository.FamiliaRepository

@RestController
@RequestMapping("/familia")
class FamiliaController (val familiaRepository: FamiliaRepository){

    @GetMapping("/listar_familias")
    fun getListaFamiliasCadastradas():ResponseEntity<List<Familia>>{
        val listaFamiliasCadastradas:List<Familia> = familiaRepository.findAll()
        if(listaFamiliasCadastradas.isNotEmpty()){
            return ResponseEntity.status(200).body(listaFamiliasCadastradas)
        }
            return  ResponseEntity.status(204).build()
    }

    @GetMapping("/buscar_familia/{id}")
    fun getFamiliaPorId(@PathVariable id:Int):ResponseEntity<Familia>{
        if(familiaRepository.existsById(id)){
            val familiaEncontrada = familiaRepository.findById(id).get()
            return ResponseEntity.status(200).body(familiaEncontrada)
        }
            return ResponseEntity.status(404).build()
    }

    @PostMapping
    fun postFamilia(@RequestBody familiaNova: Familia):ResponseEntity<Familia>{
        if(!familiaRepository.existsById(familiaNova.idFamilia)){
            familiaRepository.save(familiaNova)
            return ResponseEntity.status(201).body(familiaNova)
        }
            return ResponseEntity.status(400).build()
    }

    @PutMapping("/alterar_dados")
    fun putFamiliaPorId(@RequestBody dadosNovosDaFamilia: Familia):ResponseEntity<Familia> {
        val familia = familiaRepository.findById(dadosNovosDaFamilia.idFamilia).get()
        if (familiaRepository.existsById(dadosNovosDaFamilia.idFamilia)) {
            familia.pessoaDeficiencia = dadosNovosDaFamilia.pessoaDeficiencia
            familia.quantidadePessoas = dadosNovosDaFamilia.quantidadePessoas
            familia.rendaFamiliar = dadosNovosDaFamilia.rendaFamiliar
            familia.situacao = dadosNovosDaFamilia.situacao
            familia.rendaFamiliar = dadosNovosDaFamilia.rendaFamiliar
            familia.urgenciaFamiliar = dadosNovosDaFamilia.urgenciaFamiliar
            familia.dataUltimaAtualizacao = dadosNovosDaFamilia.dataUltimaAtualizacao
            familiaRepository.save(familia)
            return ResponseEntity.status(200).body(familia)
        }
            return ResponseEntity.status(404).build()
    }

    @DeleteMapping("/{id}")
    fun deleteFamiliaPorId(@PathVariable id:Int):ResponseEntity<String>{
        if(familiaRepository.existsById(id)){
            familiaRepository.deleteById(id)
            return ResponseEntity.status(200).body("Família removida com sucesso")
        }
            return ResponseEntity.status(404).build()
    }

}

 */