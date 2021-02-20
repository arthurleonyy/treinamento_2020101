import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContaRoutingModule } from './conta-routing.module';
import { ContaComponent } from './pages/conta.component';

@NgModule({
  declarations: [
    ContaComponent,
  ],
  imports: [
    CommonModule,
    ContaRoutingModule,
  ]
})
export class ContaModule { }
