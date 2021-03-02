import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router"
import { ConsultaSaldoComponent } from "./pages/consulta-saldo/consulta-saldo.component";
import { ContaComponent } from "./pages/conta/conta.component";
import { DepositoSaqueComponent } from "./pages/deposito-saque/deposito-saque.component";
import { OperacoesComponent } from "./pages/operacoes/operacoes.component";
import { TransferenciaComponent } from "./pages/transferencia/transferencia.component";

const routes: Routes = [
    {
        path: '',
        component: ContaComponent,
        children:[
            {
                path: '',
                component: OperacoesComponent
            },
            {
                path: 'operacoes',
                component: OperacoesComponent
            },
            {
                path: 'depositar',
                component: DepositoSaqueComponent
            },
            {
                path: 'sacar',
                component: DepositoSaqueComponent
            },
            {
                path: 'transferencia',
                component: TransferenciaComponent
            },
            {
                path: 'saldo',
                component: ConsultaSaldoComponent
            }
        ]
    },
    
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ContaRoutingModule{ }