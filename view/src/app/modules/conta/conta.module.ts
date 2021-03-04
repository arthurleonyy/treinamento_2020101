import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContaComponent } from './pages/conta.component';
import { ContaRoutingModule } from './conta-routing.module';
import { OperacoesComponent } from './pages/operacoes/operacoes.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { CoreModule } from 'src/app/core/core.module';
import { RouterModule } from '@angular/router';
import { DepositarSacarComponent } from './pages/depositar-sacar/depositar-sacar.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { TransferirComponent } from './pages/transferir/transferir.component';
import { SaldoComponent } from './pages/saldo/saldo.component';

@NgModule({
  declarations: [
    ContaComponent,
    OperacoesComponent,
    DepositarSacarComponent,
    TransferirComponent,
    SaldoComponent
  ],
  imports: [
    CommonModule,
    ContaRoutingModule,
    RouterModule,
    SharedModule.forRoot(),
    CoreModule,
    FormsModule,
    ReactiveFormsModule,
  ]
})
export class ContaModule { }
