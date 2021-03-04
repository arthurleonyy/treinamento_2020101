export class SaldoDTO{

  agencia?: string;
  numeroConta?: string;
  saldo?: number;

  constructor(obj?) {
    if(obj){
      this.agencia = obj.agencia;
      this.numeroConta = obj.numeroConta;
      this.saldo = obj.saldo;
    }
  }
}
