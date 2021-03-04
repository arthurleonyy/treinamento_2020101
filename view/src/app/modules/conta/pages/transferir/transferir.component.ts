import { Component, OnInit } from '@angular/core';
import { FormBase } from 'src/app/core/classes/form-base';
import { SweetalertCustom } from 'src/app/shared/utils/sweetalert-custom';
import { ValidatorsCustom } from 'src/app/shared/utils/validators-custom';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ContaService } from 'src/app/core/services/conta.service';
import { TransferenciaDTO } from 'src/app/core/dtos/transferencia.dto';

@Component({
  selector: 'app-transferir',
  templateUrl: './transferir.component.html',
  styleUrls: ['./transferir.component.scss']
})
export class TransferirComponent extends FormBase implements OnInit {


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
        required: 'Agência obrigatória.'
      },
      numeroContaOrigem: {
        required: 'Número da conta obrigatório.'
      },
      agenciaDestino: {
        required: 'Agência obrigatória.'
      },
      numeroContaDestino: {
        required: 'Número da conta obrigatório.'
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
        this.transferencia(conta);      
    }
  }

  private transferencia(conta: TransferenciaDTO) {
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