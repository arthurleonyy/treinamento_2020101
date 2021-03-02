import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ContaDTO } from "../dtos/conta.dto";
import { TransferenciaDTO } from "../dtos/transferencia.dto";
import { ApiService } from "./api.service";

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

    transferir(transferenciaInfo: TransferenciaDTO): Observable<any> {
        return this.apiService.post(`${this.controller}/transferencia`,transferenciaInfo);
    }
    saldo(agencia:string,conta:string){
        return this.apiService.get(`${this.controller}/consultar-saldo/${agencia}/${conta}`)
    }

}
