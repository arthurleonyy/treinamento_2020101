export class ExtratoDTO {

    agencia?: string;
    numeroConta?: string;
    dataInicio?: string;
    dataFim?: string;
    valor?: number;
    transacao?: string;
    data?: string;
    
    constructor(obj?) {
        if (obj) {
            this.agencia = obj.agencia;
            this.numeroConta = obj.numeroConta;
            this.dataInicio = obj.dataInicio;
            this.dataFim = obj.dataFim;     
            this.valor = obj.valor;
            this.transacao = obj.transacao;
            this.data = obj.data;
                  
        }
    }
}