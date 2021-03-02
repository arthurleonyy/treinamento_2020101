export class ContaDTO {

    agencia?: string;
    conta?: string;
    valor?: number;

    constructor(obj?) {
        if (obj) {
            this.agencia = obj.agencia;
            this.conta = obj.conta;
            this.valor = obj.valor;
        }
    }
}
