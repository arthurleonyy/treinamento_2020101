export class TransferenciaDTO {
    agenciaOrigem?: string
    numeroContaOrigem?: string
    
    agenciaDestino?: string
    numeroContaDestino?: string
    
    valor?: number

    constructor(objeto) {
        if(objeto) {
            this.agenciaOrigem = objeto.agenciaOrigem
            this.numeroContaOrigem = objeto.numeroContaOrigem

            this.agenciaDestino = objeto.agenciaDestino
            this.numeroContaDestino = objeto.numeroContaDestino
            
            this.valor = objeto.valor
        }
    }
}