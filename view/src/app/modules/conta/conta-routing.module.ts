import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ContaComponent } from './conta.component';
import { OperacoesComponent } from './operacoes/operacoes.component';

const routes: Routes = [
  {
    path: '',
    component: ContaComponent,
    children: [
      {
        path:'operacoes',
        component: OperacoesComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ContaRoutingModule { }
