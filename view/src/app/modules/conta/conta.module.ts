import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ContaComponent } from './pages/conta.component';
import { ContaRoutingModule } from './conta-routing.module';

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
