import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ContaDTO } from "../dtos/conta.dto";
import { ApiService } from "./api.service";
import { TransferenciaDTO } from "../dtos/transferencia.dto";
import { ConsultarDTO } from "../dtos/consultar.dto";

@Injectable({
    providedIn: 'root'
})
export class ContaService {
    
    private controller = 'conta';

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
        return this.apiService.post(`${this.controller}/consultar-saldo`, conta);
    }

}
