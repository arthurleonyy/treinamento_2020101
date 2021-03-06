export class ContaListDTO {

    agencia?: string;
    numeroConta?: string;
    cpf?: string;
    cliente?: string;
    email?: string;

    constructor(obj?) {
        if (obj) {
            this.agencia = obj.agencia;
            this.numeroConta = obj.numeroConta;
            this.cpf = obj.cpf;
            this.cliente = obj.cliente;
            this.email = obj.email;
        }
    }
}
