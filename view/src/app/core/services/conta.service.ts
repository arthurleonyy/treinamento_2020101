import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ContaDTO } from "../dtos/conta.dto";
import { ApiService } from "./api.service";

@Injectable({
  providedIn: 'root'
})

export class ContaService {

   private controller = 'conta';

   constructor(private apiService: ApiService){}

   depositar(conta: ContaDTO): Observable<any> {
     return this.apiService.post(`${this.controller}/deposito`, conta);
   }
}
