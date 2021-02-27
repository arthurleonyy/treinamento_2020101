import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContaRoutingModule } from './conta-routing.module';
import { ContaComponent } from './pages/conta.component';
import { OperacoesComponent } from './pages/operacoes/operacoes.component';

@NgModule({
  declarations: [
    ContaComponent,
    OperacoesComponent,
  ],
  imports: [
    CommonModule,
    ContaRoutingModule,
  ]
})
export class ContaModule { }
