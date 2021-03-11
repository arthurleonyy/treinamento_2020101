import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ContaDTO } from "../dtos/conta.dto";
import { ApiService } from "./api.service";
import { TransferenciaDTO } from "../dtos/transferencia.dto";
import { ConsultarDTO } from "../dtos/consultar.dto";
import { ExtratoDTO } from "../dtos/extrato.dto";

@Injectable({
    providedIn: 'root'
})
export class ContaService {
    
    private controller = 'conta';
    private controller2 = 'extrato';

    constructor(private apiService: ApiService) {}

    depositar(conta: ContaDTO): Observable<any> {
        return this.apiService.post(`${this.controller}/deposito`, conta);
    }

    sacar(conta: ContaDTO): Observable<any> {
        return this.apiService.post(`${this.controller}/saque`, conta);
    }

    transferir(conta: TransferenciaDTO): Observable<any> {
        return this.apiService.post(`${this.controller}/transferencia`, conta);
    }

    consultar(conta: ConsultarDTO): Observable<any> {
        return this.apiService.get(`${this.controller}/consultar-saldo/${conta.agencia}/${conta.numeroConta}`);
      
    }

    extrato(conta: ExtratoDTO): Observable<any> {
        return this.apiService.get(`${this.controller2}/gerar-extrato/${conta.agencia}/${conta.numeroConta}`);
      
    }

    getContas(cpf: string): Observable<any> {
        return this.apiService.get(`${this.controller}/consultar-contas-cliente/${cpf}`);
    }

    extratoData(conta: ExtratoDTO): Observable<any> {//?agencia=0002&conta=22334-5&data1=01%2F01%2F2021&data2=10%2F03%2F2021
        return this.apiService.get(`${this.controller2}/gerar-extrato-data/{ag}/{numCont}/{d1}/{d2}/?agencia=${conta.agencia}&conta=${conta.numeroConta}&data1=${conta.data1}&data2=${conta.data2}`);
      
    }

}
