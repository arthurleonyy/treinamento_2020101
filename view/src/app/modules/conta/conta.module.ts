import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContaRoutingModule } from './conta-routing.module';
import { ContaComponent } from './componentes/conta/conta.component';
import { OperacoesComponent } from './componentes/operacoes/operacoes.component';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatButtonModule, MatFormFieldModule, MatIconModule, MatInputModule, MatNativeDateModule } from '@angular/material';
import { SaqueComponent } from './componentes/operacoes/saque/saque.component';
import { TransferenciaComponent } from './componentes/operacoes/transferencia/transferencia.component';
import { DepositoComponent } from './componentes/operacoes/deposito/deposito.component';
import { ConsultarextratoComponent } from './componentes/operacoes/consultarextrato/consultarextrato.component';
import { MatDatepickerModule}  from '@angular/material/datepicker';
import { ReactiveFormsModule } from '@angular/forms';




@NgModule({
  declarations: [
    ContaComponent,
    OperacoesComponent,
    SaqueComponent,
    DepositoComponent,
    TransferenciaComponent,
    ConsultarextratoComponent,
    
    
    
  ],
  imports: [
    CommonModule,
    ContaRoutingModule,
    MatExpansionModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatDatepickerModule,
    MatNativeDateModule,
    ReactiveFormsModule,

    
   
  ]
})
export class ContaModule {

 
 }

