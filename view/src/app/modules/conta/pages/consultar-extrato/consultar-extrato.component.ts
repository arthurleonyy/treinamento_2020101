import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { ExtratoDTO } from 'src/app/core/dtos/extrato.dto';
import { RegistroTransacoesService } from 'src/app/core/services/registro-transacoes.service';
import { ValidatorsCustom } from 'src/app/shared/utils/validators-custom';

@Component({
  selector: 'app-consultar-extrato',
  templateUrl: './consultar-extrato.component.html',
  styleUrls: ['./consultar-extrato.component.scss']
})
export class ConsultarExtratoComponent extends FormBase implements OnInit {

  extratos = new Array<ExtratoDTO>();

  constructor(
    private registroTransacoesService: RegistroTransacoesService,
    public router: Router,
    private formBuilder: FormBuilder
    ) { super()}

  ngOnInit(): void {
    this.createFormGroup()
    this.validateMessageError()
  }

  createFormGroup() {
    this.form = this.formBuilder.group({
      agencia: ['', [Validators.required]],
      conta: ['', [Validators.required]],
      dataInicio: ['', [Validators.required, ValidatorsCustom.minDate("DDMMYYYY"),ValidatorsCustom.validDate("DDMMYYYY")]],
      dataFim: ['', [Validators.required, ValidatorsCustom.minDate("DDMMYYYY"), ValidatorsCustom.validDate("DDMMYYYY")]]
    },
    {
      validators: [ValidatorsCustom.PeriodoCorreto("dataInicio","dataFim","DDMMYYYY")]
    });
  }

  validateMessageError() {
    this.createValidateFieldMessage({
      agencia: {
        required: 'Agência é obrigatório'
      },
      conta: {
        required:'Conta é obrigatório'
      },
      dataInicio: {
        required: 'Começando em é obrigatório.',
        validDate: "A data não está no formato adequado.",
        minDate: "A data informada não é valida",
        periodoCorreto: "O período de inicio não pode ser menor que o de termino"
      },
      dataFim: {
        required: 'Até é obrigatório.',
        validDate: "A data não esta no formato adequado.",
        minDate: "A data informada não é valida",
        periodoCorreto: "O período de inicio não pode ser menor que o de termino"
      }
    });
  }

  onSubmit() {
    if (this.form.valid) {
       this.getExtrato()
    }
  }

  getExtrato() {
    let h = new HttpParams({fromObject: this.form.value});
    this.registroTransacoesService.getExtrato(h).subscribe({next: response=> {
      this.extratos = response.body.map(item => {
                                    return new ExtratoDTO({
                                    id : item.id,
                                    tipo : item.tipoTransacao,
                                    descricao : item.descricao,
                                    data : item.data,
                                    valor : item.valor
                                    
                                })
                              })
        console.log(this.extratos)
      }})
    }
}
