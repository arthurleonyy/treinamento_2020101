import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { CoreModule } from "src/app/core/core.module";
import { SharedModule } from "src/app/shared/shared.module";
import { ContaRoutingModule } from "./conta-routing.module";
import { ContaComponent } from './pages/conta.component';
import { OperacoesComponent } from "./pages/operacoes/operacoes.component";
import { DepositarSacarComponent } from './pages/depositar-sacar/depositar-sacar.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { TransferenciaComponent } from './pages/transferencia/transferencia.component';
import { ConsultaSaldoComponent } from './pages/consulta-de-saldo/consulta-de-saldo.component';

@NgModule({
    declarations:[
        ContaComponent,
        OperacoesComponent,
        DepositarSacarComponent,
        TransferenciaComponent,
        ConsultaSaldoComponent,
    ],
    imports:[
        CommonModule,
        ContaRoutingModule,
        SharedModule.forRoot(),
        CoreModule,
        FormsModule,
        ReactiveFormsModule
    ]
})
export class ContaModule{ }