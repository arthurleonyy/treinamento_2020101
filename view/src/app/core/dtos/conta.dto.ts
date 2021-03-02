export class ContaDTO{
    agencia?: string;
    numeroConta?: string;
    valor?: number;

    constructor(obj?){
        if(obj){
            this.agencia = obj.agencia;
            this.numeroConta = obj.numeroConta;
            this.valor = obj.valor;
        }
    }
}