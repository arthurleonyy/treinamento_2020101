import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContaComponent } from './pages/conta.component';
import { DepositarSacarComponent } from './pages/depositar-sacar/depositar-sacar.component';
import { OperacoesComponent } from './pages/operacoes/operacoes.component';
import { ConsultarSaldoComponent } from './pages/consultar-saldo/consultar-saldo.component'
import { TransferirComponent} from './pages/transferir/transferir.component'
import { ExtratoComponent } from './pages/extrato/extrato.component'
import { ConsultarContasComponent } from './pages/consultar-contas/consultar-contas.component'

const routes: Routes = [
  {
    path: '',
    component: ContaComponent,
    children: [
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
        component: DepositarSacarComponent
      },
      {
        path: 'sacar',
        component: DepositarSacarComponent
      },
      {
        path: 'transferir',
        component: TransferirComponent
      },
      {
        path: 'consultar-saldo',
        component: ConsultarSaldoComponent
      },
      {
        path: 'gerar-extrato',
        component: ExtratoComponent
      },
      {
        path: 'consultar-contas',
        component: ConsultarContasComponent
      }

    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContaRoutingModule { }
