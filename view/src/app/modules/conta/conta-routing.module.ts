import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ConsultaSaldoComponent } from './pages/consulta-saldo/consulta-saldo.component';
import { ConsultarContasComponent } from './pages/consultar-contas/consultar-contas.component';
import { ContaComponent } from './pages/conta.component';
import { DepositarSacarComponent } from './pages/depositar-sacar/depositar-sacar.component';
import { ExtratoComponent } from './pages/extrato/extrato.component';
import { OperacoesComponent } from './pages/operacoes/operacoes.component';
import { TransferenciaComponent } from './pages/transferencia/transferencia.component';

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
        component: TransferenciaComponent
      },
      {
        path: 'consultarsaldo',
        component: ConsultaSaldoComponent
      },
      {
        path: 'consultarcontas',
        component: ConsultarContasComponent
      },
      {
        path: 'extrato',
        component: ExtratoComponent
      },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContaRoutingModule { }
