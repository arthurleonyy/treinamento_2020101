import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { CoreModule } from "src/app/core/core.module";
import { SharedModule } from "src/app/shared/shared.module";
import { ContaRoutingModule } from "./conta-routing.module";
import { ContaComponent } from './pages/conta/conta.component';
import { OperacoesComponent } from "./pages/operacoes/operacoes.component";
import { DepositoSaqueComponent } from './pages/deposito-saque/deposito-saque.component';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { TransferenciaComponent } from './pages/transferencia/transferencia.component';
import { ConsultaSaldoComponent } from './pages/consulta-saldo/consulta-saldo.component';
import { ConsultarContasComponent } from './pages/consultar-contas/consultar-contas.component';
import { NgxMaskModule } from "ngx-mask";

@NgModule({
    declarations:[
        ContaComponent,
        OperacoesComponent,
        DepositoSaqueComponent,
        TransferenciaComponent,
        ConsultaSaldoComponent,
        ConsultarContasComponent,
    ],
    imports:[
        CommonModule,
        ContaRoutingModule,
        SharedModule.forRoot(),
        CoreModule,
        FormsModule,
        ReactiveFormsModule,
        NgxMaskModule.forRoot()
    ]
})
export class ContaModule{ }