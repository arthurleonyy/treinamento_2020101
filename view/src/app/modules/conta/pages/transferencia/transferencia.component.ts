import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { TransferenciaDTO } from 'src/app/core/dtos/transferencia.dto';
import { ContaService } from 'src/app/core/services/conta.service';
import { SweetalertCustom } from 'src/app/shared/utils/sweetalert-custom';
import { ValidatorsCustom } from 'src/app/shared/utils/validators-custom';

@Component({
  selector: 'app-transferencia',
  templateUrl: './transferencia.component.html',
  styleUrls: ['./transferencia.component.scss']
})
export class TransferenciaComponent extends FormBase implements OnInit {

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
      agenciaOrigem:      ['', [Validators.required]],
      numeroContaOrigem:  ['', [Validators.required]],
      agenciaDestino:      ['', [Validators.required]],
      numeroContaDestino:  ['', [Validators.required]],
      valor:        [0, [Validators.required, ValidatorsCustom.lessThanOne]],
    });
  }

  /**
   * Seta a mensagem de validação que irá ser exibida ao usuário
   */
  validateMensageError() {
    this.createValidateFieldMessage({
      agenciaOrigem: {
        required: 'Agência de origem obrigatória.'
      },
      numeroContaOrigem: {
        required: 'Número da conta de origem obrigatório.'
      },
      agenciaDestino: {
        required: 'Agência de destino obrigatória.'
      },
      numeroContaDestino: {
        required: 'Número da conta de destino obrigatório.'
      },
      valor: {
        required: 'Valor obrigatório.',
        lessThanOne: 'Valor informado deve ser maior que zero.'
      }
    });
  }

  onSubmit() {
    if (this.form.valid) {
      let conta = new TransferenciaDTO(this.form.value);
       this.transferir(conta);
      
    }
  }

  private transferir(conta: TransferenciaDTO) {
    this.contaService.transferir(conta).subscribe(
      response => {
        SweetalertCustom.showAlertTimer('Operação realizada com sucesso.', {type: 'success'}).then(
          result => {
            if (result.dismiss) {
              this.router.navigate(['conta/operacoes']);
            }
          }
        );
      }
    );
  }
 
}
