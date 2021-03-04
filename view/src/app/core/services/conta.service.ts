import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { TransferirDTO} from "../dtos/transferir.dto";
import { ContaDTO} from "../dtos/conta.dto";
import { ApiService } from "./api.service";
import { ConsultarSaldoDTO } from "../dtos/consultar_saldo";

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

    transferir(conta: TransferirDTO): Observable<any> {
        return this.apiService.post(`${this.controller}/transferir`, conta);
    }

    consultarSaldo(conta: ConsultarSaldoDTO): Observable<any> {
        return this.apiService.pull(`${this.controller}/consultar_saldo`, conta);
    }
}
    