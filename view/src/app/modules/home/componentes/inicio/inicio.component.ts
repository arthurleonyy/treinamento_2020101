import { Component, OnInit } from '@angular/core';
import { ContaService } from 'src/app/core/services/conta.service';
import { RegistroTransacoesService } from 'src/app/core/services/registro-transacoes.service';

@Component({
  selector: 'app-inicio',
  templateUrl: './inicio.component.html',
  styleUrls: ['./inicio.component.scss']
})
export class InicioComponent implements OnInit {

  constructor(private contaService: ContaService,
              private registroTransacoesService: RegistroTransacoesService) { }

  ngOnInit() {
  }

}
