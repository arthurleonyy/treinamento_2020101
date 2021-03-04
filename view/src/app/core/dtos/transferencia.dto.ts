export class TransferenciaDTO {

    agenciaOrigem?: string;
    agenciaDestino?: string;
    numeroContaOrigem?: string;
    numeroContaDestino?: string;
    valor?: number;

    constructor(obj?) {
        if (obj) {
            this.agenciaOrigem = obj.agenciaOrigem;
            this.agenciaDestino = obj.agenciaDestino;
            this.numeroContaOrigem = obj.numeroContaOrigem;
            this.numeroContaDestino = obj.numeroContaDestino;
            this.valor = obj.valor;

        }
    }



}