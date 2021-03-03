export class TransferenciaDTO{

  agenciaDestino?: string;
  agenciaOrigem?: string;
  numeroContaDestino?: string;
  numeroContaOrigem?: string;
  valor?: number;

  constructor(obj?) {
    if(obj){
      this.agenciaOrigem = obj.agenciaOrigem;
      this.numeroContaOrigem = obj.numeroContaOrigem;
      this.agenciaDestino = obj.agenciaDestino;
      this.numeroContaDestino = obj.numeroContaDestino;
      this.valor = obj.valor;
    }
  }
}
