import { HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ContaDTO } from "../dtos/conta.dto";
import { ExtratoDTO } from "../dtos/extrato.dto";
import { TransferenciaDTO } from "../dtos/transferencia.dto";
import { ApiService } from "./api.service";

@Injectable({
    providedIn: 'root'
})
export class RegistroTransacoesService {
    
    private controller = 'registro-transacoes';

    constructor(private apiService: ApiService) {}

    getExtrato(urlParams: any): Observable<any> {
        return this.apiService.get(`${this.controller}/consultar-extrato`,urlParams);
    }

}
