import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { ExtratoDTO } from 'src/app/core/dtos/extrato.dto';
import { ContaService } from 'src/app/core/services/conta.service';
import { ValidatorsCustom } from 'src/app/shared/utils/validators-custom';

@Component({
  selector: 'app-emitir-extrato',
  templateUrl: './emitir-extrato.component.html',
  styleUrls: ['./emitir-extrato.component.scss']
})
export class EmitirExtratoComponent extends FormBase implements OnInit {

  extrato = new Array<ExtratoDTO>();

  constructor(
    private formBuilder: FormBuilder,
    private contaService: ContaService,
    public router: Router,
  ) {
    super();
   }

  ngOnInit(): void {
    this.createFormGroup();
    this.validateErrorMessage();
  }

  createFormGroup(){
    this.form = this.formBuilder.group({
      agencia: ['', [Validators.required]],
      numeroConta: ['', [Validators.required]],
      dataInicio: [''],
      dataFim: [''],
      filtro: [false]
    },
     {validator: [ValidatorsCustom.validarData('dataInicio', 'dataFim')]}
    );
  }

  validateErrorMessage(){
    this.createValidateFieldMessage({
      agencia: {
        required: "Agência obrigatória"
      },
      numeroConta: {
        required: "Número da Conta obrigatório"
      },
      dataInicio: {
        invalidStartDate: "Data Início não pode ser menor que a Data Fim",
        invalidDate: "Data inválida. Por favor, digite uma data válida.",
        invalidDay: "Dia inválido. Por favor, digite um dia válido (01-31)",
        invalidMonth: "Mês inválido. Por favor, digite um mês válido (01-12)",
        invalidYear: "Ano inválido. Por favor, digite um ano válido"
      },
      dataFim: {
        invalidDate: "Data inválida. Por favor, digite uma data válida.",
        invalidDay: "Dia inválido. Por favor, digite um dia válido (01-31)",
        invalidMonth: "Mês inválido. Por favor, digite um mês válido (01-12)",
        invalidYear: "Ano inválido. Por favor, digite um ano válido"
      }
    })
  }

  onSubmit(){
    if(this.form.valid){
      const agencia: string = this.form.get('agencia').value;
      const conta: string = this.form.get('numeroConta').value;
      const dataInicio: string = this.form.get('dataInicio').value;
      const dataFim: string = this.form.get('dataFim').value;

      if((dataInicio === '') || (dataFim === '')){
        this.contaService.emitirExtrato(agencia, conta).subscribe(
          (response) => {
            this.extrato = response.body.map(
              (item) => {
                return new ExtratoDTO({
                  id: item.id,
                  nome: item.cliente.nome,
                  agencia: item.agencia,
                  conta: item.conta,
                  operacao: item.operacao,
                  valor: item.valor,
                  data: item.dataView
                })
              }
            );
          },
          (error) => {
            this.extrato = new Array<ExtratoDTO>();
          }
        );
      } else{
        let novaDataInicio = this.converterData(dataInicio);
        let novaDataFim = this.converterData(dataFim);
        this.contaService.emitirExtratoComData(agencia, conta, novaDataInicio, novaDataFim).subscribe(
          (response) => {
            this.extrato = response.body.map(
              (item) => {
                return new ExtratoDTO({
                  id: item.id,
                  nome: item.cliente.nome,
                  agencia: item.agencia,
                  conta: item.conta,
                  operacao: item.operacao,
                  valor: item.valor,
                  data: item.dataView
                });
              }
            );
          },
          (error) => {
            this.extrato = new Array<ExtratoDTO>();
          }
        )
      }
    }
  }

  converterData(data: string){
    let novaData = new Date(data.split('/').reverse().join('-') + ' 00:00:00');
    let format = new DatePipe('en-US');

    return format.transform(novaData, 'yyyy-MM-dd');
  }

  limparCampos(){
    this.form.get('dataInicio').setValue("");
    this.form.get('dataFim').setValue("");
  }

}
