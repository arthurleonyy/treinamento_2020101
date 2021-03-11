import { ValidatorsCustom } from 'src/app/shared/utils/validators-custom';
import { ContaDTO } from 'src/app/core/dtos/conta.dto';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ContaService } from 'src/app/core/services/conta.service';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { ContaListDTO } from 'src/app/core/dtos/conta-list.dto';

@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.scss']
})
export class ExtratoComponent extends FormBase implements OnInit {

  listaContas = new Array<ContaListDTO>();
  conta: ContaDTO;

  constructor( 
    private formBuilder: FormBuilder,
    private contaService: ContaService,
    public router: Router
  ) {
    super()
  }

  ngOnInit() {
    this.createFormGroup();
    this.validateMessageError();

  }
  createFormGroup() {
    this.form = this.formBuilder.group({
      agencia: ['', [Validators.required]],
      dataIni: ['', [Validators.required, ValidatorsCustom.validDate]],
      dataFim: ['', [Validators.required, ValidatorsCustom.validDate]],
      numeroConta: ['', [Validators.required]]
    });
  }

  validateMessageError() {
    throw new Error('Method not implemented.');
  }

}
