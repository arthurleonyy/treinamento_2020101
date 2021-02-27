import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { ContaComponent } from './pages/conta.component';

import { OperacoesComponent } from './operacoes/operacoes.component';


const routes: Routes = [
    {
        path: '',
        component: ContaComponent,
    }, {
        path: 'operacoes',
        component: OperacoesComponent
    }
];
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ContaRoutingModule { }