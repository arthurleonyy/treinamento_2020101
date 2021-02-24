import { NgModule } from "@angular/core";
import { Routes, RouterModule } from "@angular/router"
import { ContaComponent } from "./pages/conta/conta.component";
import { OperacoesComponent } from "./pages/operacoes/operacoes.component";

const routes: Routes = [
    {
        path: '',
        component: ContaComponent,
        children:[
            {
                path: '',
                component: OperacoesComponent
            },
            {
                path: 'operacoes',
                component: OperacoesComponent
            }
        ]
    },
    
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ContaRoutingModule{ }