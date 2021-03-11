import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import * as moment from 'moment';
import { FormBase } from 'src/app/core/classes/form-base';
import { ExtratoDTO } from 'src/app/core/dtos/extrato.dto';
import { ContaService } from 'src/app/core/services/conta.service';
import { ValidatorsCustom } from 'src/app/shared/utils/validators-custom';

@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.scss']
})

export class ExtratoComponent extends FormBase implements OnInit {

  listaExtrato = new Array<ExtratoDTO>();

  constructor(
    private formBuilder: FormBuilder,
    private contaService: ContaService,
    public router: Router
  ) {
    super();
  }

  ngOnInit() {
    this.validateMensageError();
    this.createFormGroup();

  }

  createFormGroup() {
    this.form = this.formBuilder.group({
      agencia:      ['', [Validators.required]],
      numeroConta:  ['', [Validators.required]],
      data1:      ['', [Validators.required, ValidatorsCustom.validDate]],
      data2:  ['', [Validators.required,ValidatorsCustom.validDate]],
    
    },);
  }


  validateMensageError() {
    this.createValidateFieldMessage({
      agencia: {
        required: 'Agência obrigatória.'
      },
      numeroConta: {
        required: 'Número da conta obrigatório.'
      },
      data1: {
        required: 'Data obrigatória.',
        validDate: 'Data invalida',
      },
      data2: {
        required: 'Data obrigatória.',
        validDate: 'Data invalida',
      }
    });
  }

  onSubmit() {
    if (this.form.valid) {
      let conta = new ExtratoDTO(this.form.value);

        this.extrato(conta);      
    }
  }

  private extrato(conta: ExtratoDTO) {
    
    this.contaService.extratoData(conta).subscribe(
      (response) => {
        this.listaExtrato = response.body.map(item => {

          return new ExtratoDTO({
            agencia: item.agencia,
            numeroConta: item.conta,
            acao: item.acao,
            data: moment(item.data).format('DD/MM/YYYY'),//data recebida do REST esta no formato 2021-02-13T18:07:49.000+00:00, ou seja, é necessário uma formatação
          })
        });
      },
      (error ) => {
        this.listaExtrato = new Array<ExtratoDTO>();
      }
    );
  }

}
