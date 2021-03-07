import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
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
      this.createFormGroup();
      this.validateMessageError();
    }
    
    createFormGroup() {
      this.form = this.formBuilder.group({
        agencia: ['', [Validators.required]],
        numeroConta: ['', [Validators.required]],
        dataInicio: ['', [Validators.required]],
        dataFim: ['', [Validators.required]],
      },{
        validator: [ValidatorsCustom.menorQue('dataFim', 'dataInicio')],
       

      },
      );
    }
  
    validateMessageError() {
      this.createValidateFieldMessage({
        agencia: {
          required: 'Agencia é obrigatória.',
        },
        numeroConta: {
          required: 'Número da conta obrigatório.'
        },
        dataInicio: {
          required: 'Data do inicio é obrigatório',         
          menorQue: 'Data informada deve ser menor que data Fim',
          
          
        },
        dataFim: {
          required: 'Data do fim é obrigatório',
          menorQue: 'Data informada deve ser maior que data Inicio.',
                
        }
      });
    }
    
  
    onSubmit() {
      if (this.form.valid) {
        let extrato = new ExtratoDTO(this.form.value);
       
        this.contaService.consultarExtrato(extrato).subscribe(
          (response) => {
            this.listaExtrato = response.body.map(item => {
              return new ExtratoDTO({
                agencia: item.agencia,
                numeroConta: item.conta,
                data: item.data,
                transacao: item.transacao,
                valor: item.valor,
              })
            });
          },
        );
      }
    }
  
  }  
