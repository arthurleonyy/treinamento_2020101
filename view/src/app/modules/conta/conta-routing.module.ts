import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ConsultarContasComponent } from './pages/consultar-contas/consultar-contas.component';
import { ConsultarExtratoComponent } from './pages/consultar-extrato/consultar-extrato.component';
import { ContaComponent } from './pages/conta.component';
import { DepositarSacarComponent } from './pages/depositar-sacar/depositar-sacar.component';
import { OperacoesComponent } from './pages/operacoes/operacoes.component';
import { TransferirComponent } from './pages/transferir/transferir.component';
import { VerificarSaldoComponent } from './pages/verificar-saldo/verificar-saldo.component';

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
        component: VerificarSaldoComponent
      },
      {
        path: 'consultar-contas',
        component: ConsultarContasComponent
      },
      {
        path:"consultar-extrato",
        component: ConsultarExtratoComponent
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContaRoutingModule { }
