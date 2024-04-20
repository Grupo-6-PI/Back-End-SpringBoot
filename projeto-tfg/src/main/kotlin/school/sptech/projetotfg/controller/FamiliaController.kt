package school.sptech.projetotfg.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import school.sptech.projetotfg.domain.Familia
import school.sptech.projetotfg.repository.FamiliaRepository

@RestController
@RequestMapping("/familia")
class FamiliaController (val familiaRepository: FamiliaRepository){

    @GetMapping("/listar_familias")
    fun listaFamiliasCadastradas():ResponseEntity<List<Familia>>{
        val listaFamilias = familiaRepository.findAll()
        if(listaFamilias.isNotEmpty()){
            return ResponseEntity.status(200).body(listaFamilias)
        }
            return  ResponseEntity.status(204).build()
    }

    @GetMapping("/buscar_familia/{id}")
    fun buscaFamiliaPorId(@PathVariable id:Int):ResponseEntity<Familia>{
        if(familiaRepository.existsById(id)){
            val familiaPesquisada = familiaRepository.findById(id).get()
            return ResponseEntity.status(200).body(familiaPesquisada)
        }
            return ResponseEntity.status(404).build()
    }

    @PostMapping
    fun cadastrarFamilia(@RequestBody familia: Familia):ResponseEntity<Familia>{
        if(!familiaRepository.existsById(familia.idFamilia)){
            familiaRepository.save(familia)
            return ResponseEntity.status(201).body(familia)
        }
            return ResponseEntity.status(400).build()
    }

    @PutMapping("/alterar_dados")
    fun atualizaFamiliaPorId(@RequestBody familiaAtualizada:Familia):ResponseEntity<Familia> {
        val familia = familiaRepository.findById(familiaAtualizada.idFamilia).get()
        if (familiaRepository.existsById(familiaAtualizada.idFamilia)) {
            familia.pessoaDeficiencia = familiaAtualizada.pessoaDeficiencia
            familia.quantidadePessoas = familiaAtualizada.quantidadePessoas
            familia.rendaFamiliar = familiaAtualizada.rendaFamiliar
            familia.situacao = familiaAtualizada.situacao
            familia.rendaFamiliar = familiaAtualizada.rendaFamiliar
            familia.urgenciaFamiliar = familiaAtualizada.urgenciaFamiliar
            familia.dataUltimaAtualizacao = familiaAtualizada.dataUltimaAtualizacao
            familiaRepository.save(familia)
            return ResponseEntity.status(200).body(familia)
        }
            return ResponseEntity.status(404).build()
    }

    @DeleteMapping("/{id}")
    fun deletaFamiliaPorId(@PathVariable id:Int):ResponseEntity<String>{
        if(familiaRepository.existsById(id)){
            familiaRepository.deleteById(id)
            return ResponseEntity.status(200).body("Família removida com sucesso")
        }
            return ResponseEntity.status(404).build()
    }

}