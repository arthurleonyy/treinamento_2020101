import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContaRoutingModule } from './conta-routing.module';
import { ContaComponent } from './pages/conta.component';
import { OperacoesComponent } from './pages/operacoes/operacoes.component';
import { TransferenciaComponent } from './pages/transferencia/transferencia.component';

@NgModule({
  declarations: [
    ContaComponent,
    OperacoesComponent,
    TransferenciaComponent,
  ],
  imports: [
    CommonModule,
    ContaRoutingModule,
  ]
})
export class ContaModule { }
