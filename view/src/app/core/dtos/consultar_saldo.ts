export class ConsultarSaldoDTO{

agencia?: string;
    numeroConta?: string;

    constructor(obj?) {
        if (obj) {
            this.agencia = obj.agencia;
            this.numeroConta = obj.numeroConta;
        }
    }
}