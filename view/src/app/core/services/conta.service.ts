import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ContaDTO } from "../dtos/conta.dto";
import { TransferenciaDTO } from "../dtos/transferencia.dto";
import { ApiService } from "./api.service";

@Injectable({
    providedIn: 'root'
})
export class ContaService{
    private controller = 'conta';

    constructor(private api: ApiService){}

    depositar(conta: ContaDTO): Observable<any>{
        return this.api.post(`${this.controller}/deposito/`, conta);
    }

    sacar(conta: ContaDTO): Observable<any>{
        return this.api.post(`${this.controller}/saque/`, conta);
    }

    transferir(conta: TransferenciaDTO): Observable<any>{
        return this.api.post(`${this.controller}/transferencia/`, conta);
    }

    consultarSaldo(agencia: string, numeroConta: string): Observable<any>{
        return this.api.get(`${this.controller}/consultar-saldo/${agencia}/${numeroConta}`);
    }

    consultarContas(cpf: string): Observable<any>{
        return this.api.get(`${this.controller}/consultar-contas-cliente/${cpf}`);
    }

    emitirExtrato(agencia: string, conta: string): Observable<any>{
        return this.api.get(`extrato/emitir-extrato/${agencia}/${conta}`);
    }

    emitirExtratoComData(agencia: string, conta: string, dataInicio: string, dataFim: string){
        return this.api.get(`extrato/emitir-extrato-com-data/${agencia}/${conta}/${dataInicio}/${dataFim}`);
    }
    
}
