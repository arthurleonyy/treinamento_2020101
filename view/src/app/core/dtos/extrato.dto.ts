export class ExtratoDTO {

    agencia?: string;
    numeroConta?: string;
    data1?: Date;
    data2?: Date;
    acao?: string;
    data?: string;

    constructor(obj?) {
        if (obj) {
            this.agencia = obj.agencia;
            this.numeroConta = obj.numeroConta;
            this.data1 = obj.data1;
            this.data2 = obj.data2;
            this.acao = obj.acao;
            this.data = obj.data;
        }
    }
    
}