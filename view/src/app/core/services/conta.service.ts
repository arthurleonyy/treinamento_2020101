import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ContaDTO } from "../dtos/conta.dto";
import { ExtratoDTO } from "../dtos/extrato.dto";
import { TransferenciaDTO } from "../dtos/transferencia.dto";
import { ApiService } from "./api.service";

@Injectable({
    providedIn: 'root'
})
export class ContaService {
    
    private controllerConta = 'conta';
    private controllerContaExtrtato = "extrato";

    constructor(private apiService: ApiService) {}

    depositar(conta: ContaDTO): Observable<any> {
        return this.apiService.post(`${this.controllerConta}/deposito`, conta);
    }

    sacar(conta: ContaDTO): Observable<any> {
        return this.apiService.post(`${this.controllerConta}/saque`, conta);
    }
    transferir(conta: TransferenciaDTO): Observable<any> {
        return this.apiService.post(`${this.controllerConta}/transferencia`, conta);
    }
    consultarSaldo(conta: ContaDTO) {
        return this.apiService.get(`${this.controllerConta}/consultar-saldo/${conta.agencia}/${conta.numeroConta}`);

    }
    consultacConta(cpf: number) {
        return this.apiService.get(`${this.controllerConta}/consultar-saldo/${cpf}`);

    }
    getContas(cpf: string): Observable<any> {
        return this.apiService.get(`${this.controllerConta}/consultar-contas-cliente/${cpf}`);
    }
    consultarExtrato(extrato: ExtratoDTO){
        return this.apiService.get(`${this.controllerContaExtrtato}/consultar_extrato/?agencia=${extrato.agencia}&conta=${extrato.numeroConta}&dataInicio=${extrato.dataInicio}&dataFim=${extrato.dataFim}`);
    } 
}
