export class TransferenciaDTO{
    agenciaOrigem?: string;
    contaOrigem?: string;
    agenciaDestino?: string;
    contaDestino?: string;
    valor?: number;

    constructor(obj?){
        this.agenciaOrigem = obj.agenciaOrigem;
        this.contaOrigem = obj.contaOrigem;
        this.agenciaDestino = obj.agenciaDestino;
        this.contaDestino = obj.contaDestino;
        this.valor = obj.valor;
    }


}