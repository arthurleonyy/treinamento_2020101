import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CoreModule } from 'src/app/core/core.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { ContaRoutingModule } from './conta-routing.module';
import { ContaComponent } from './pages/conta.component';
import { DepositarSacarComponent } from './pages/depositar-sacar/depositar-sacar.component';
import { OperacoesComponent } from './pages/operacoes/operacoes.component';
import { TransferenciaComponent } from './pages/transferencia/transferencia.component';
import { ConsultaSaldoComponent } from './pages/consulta-saldo/consulta-saldo.component';

@NgModule({
  declarations: [
    ContaComponent,
    OperacoesComponent,
    DepositarSacarComponent,
    TransferenciaComponent,
    ConsultaSaldoComponent
  ],
  imports: [
    CommonModule,
    ContaRoutingModule,
    SharedModule.forRoot(),
    CoreModule,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class ContaModule { }
