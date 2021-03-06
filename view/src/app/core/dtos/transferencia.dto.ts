export class TransferenciaDTO {

    agenciaOrigem?: string;
    numeroContaOrigem?: string;
    agenciaDestino?: string;
    numeroContaDestino?: string;
    valor?: number;

    constructor(obj?: any) {
        this.agenciaOrigem = obj.agenciaOrigem;
        this.numeroContaOrigem = obj.numeroContaOrigem;
        this.agenciaDestino = obj.agenciaDestino;
        this.numeroContaDestino = obj.numeroContaDestino;
        this.valor = obj.valor;
    }
    
}
