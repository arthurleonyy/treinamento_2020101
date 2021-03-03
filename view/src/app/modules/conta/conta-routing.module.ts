import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContaComponent } from './pages/conta.component';
import { DepositarSacarComponent } from './pages/depositar-sacar/depositar-sacar.component';
import { OperacoesComponent } from './pages/operacoes/operacoes.component';
import { TransferirComponent } from './pages/transferir/transferir.component';

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
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContaRoutingModule { }
