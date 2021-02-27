import { Component, NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OperacoesComponent } from './operacoes.component';

const routes: Routes = [
  {
    path: '',
    component: OperacoesComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OperacoesRoutingModule { }
