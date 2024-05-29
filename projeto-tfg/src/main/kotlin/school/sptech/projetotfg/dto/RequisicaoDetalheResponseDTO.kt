package school.sptech.projetotfg.dto

import school.sptech.projetotfg.domain.cadastro.RendaFamiliar
import school.sptech.projetotfg.domain.cadastro.UrgenciaFamiliar
import java.time.LocalDate

data class RequisicaoDetalheResponseDTO(
    val id:Long,
    val nomeSolicitante: String,
    val cpf:String,
    val dataNascimento:LocalDate,
    val endereco: EnderecoDTO,
    val idFamilia:Long,
    val tipoDoacao:String,
    //val descricaoDoacao:String,
    val familia: FamiliaDTO,
    val qtdPessoas:Long,
    val temCrianca:Boolean,
    val temPcd:Boolean,
    val urgencia:UrgenciaResponseDTO,
    val rendaFamiliar: RendaFamiliarResponseDTO
)
