import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CoreModule } from 'src/app/core/core.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { ContaRoutingModule } from './conta-routing.module';
import { ConsultarSaldoComponent } from './pages/consultar_saldo/consultar_saldo.component';
import { ContaComponent } from './pages/conta.component';
import { DepositarSacarComponent } from './pages/depositar-sacar/depositar-sacar.component';
import { OperacoesComponent } from './pages/operacoes/operacoes.component';
import { TransferirComponent } from './pages/transferir/transferir.component';

@NgModule({
  declarations: [
    ContaComponent,
    OperacoesComponent,
    DepositarSacarComponent,
    TransferirComponent,
    ConsultarSaldoComponent
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
