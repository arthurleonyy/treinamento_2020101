import { SweetalertCustom } from './../../../../shared/utils/sweetalert-custom';
import { TransferenciaDTO } from './../../../../core/dtos/transferencia.dto';
import { ContaService } from 'src/app/core/services/conta.service';
import { FormBuilder, Validators } from '@angular/forms';
import { FormBase } from 'src/app/core/classes/form-base';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ValidatorsCustom } from 'src/app/shared/utils/validators-custom';
import { type } from 'os';

@Component({
  selector: 'app-transferir',
  templateUrl: './transferir.component.html',
  styleUrls: ['./transferir.component.scss']
})
export class TransferirComponent extends FormBase implements OnInit {

  constructor(
    private FormBuilder: FormBuilder,
    private contaService: ContaService,
    private router: Router
  ) {
    super();
   }

  ngOnInit() {
    this.validateMensageError();
    this.createFormGroup();
  }

  createFormGroup() {
    this.form = this.FormBuilder.group({
      agenciaOrigem: ['', [Validators.required]],
      agenciaDestino: ['', [Validators.required]],
      numeroContaOrigem: ['', [Validators.required]],
      numeroContaDestino: ['', [Validators.required]],
      valor:        [0, [Validators.required, ValidatorsCustom.lessThanOne]],

    });
  }
  validateMensageError() {
    this.createValidateFieldMessage({
      agenciaOrigem: {
        required: 'Informar a agência de origem'
      },
      agenciaDestino: {
        required: 'Informar a agência de destino'
      },
      numeroContaOrigem: {
        required: 'Informar a Numero da Conta de origem'
      },
      numeroContaDestino: {
        required: 'Informar a Numero da Conta de destino'
      },
      valor: {
        required: 'Valor obrigatório.',
        lessThanOne: 'Valor informado deve ser maior que zero.'
      },
    });

//    onSubmit() {
//      if (this.form.valid) {
//        const transfarencia = new TransferenciaDTO(this.form.value);
//       this.contaService.transferir(transfarencia).subscribe(
//          response => {
//            SweetalertCustom.showAlertTimer('Transfarencia executada com sucesso ', {type: 'success'}).then (
//              result => {
//                if (result.dismiss) {
//                  this.router.navigate(['conta/operacoes']);
//                }
//              }
//            );
//          }
//
//        );
//     }
    }
  }
//}
