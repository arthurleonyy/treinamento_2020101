export class ExtratoDTO{
  id? : number;
  nome? : string;
  agencia? : string;
  conta? : string;
  operacao? : string;
  valor? : number;
  data? : string;

  constructor(obj?){
      if(obj){
        this.id = obj.id;
        this.nome = obj.nome;
        this.agencia = obj.agencia;
        this.conta = obj.conta;
        this.operacao = obj.operacao;
        this.valor = obj.valor;
        this.data = obj.data;
      }
        

  } 
}