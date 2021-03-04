import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { TransferirDTO } from 'src/app/core/dtos/transferir.dto';
import { ContaService } from 'src/app/core/services/conta.service';
import { SweetalertCustom } from 'src/app/shared/utils/sweetalert-custom';
import { ValidatorsCustom } from 'src/app/shared/utils/validators-custom';

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
   
  validateMensageError() {
    this.createValidateFieldMessage({
      agenciaOrigem: {
        required: "Agência obrigatória."
      },
      numeroContaOrigem: {
        required: "Número da conta obrigatório."
      },
      agenciaDestino: {
        required: "Agência obrigatória."
      },
      numeroContaDestino: {
        required: "Número da conta obrigatório."
      },
      valor: {
        required: "Valor obrigatório.",
        lessThanOne: "Valor informado deve ser maior que zero."
      }
    });
  }

  onSubmit() {
    if (this.form.valid) {
      let conta = new TransferirDTO(this.form.value);
  }
  }
    private transferir(conta: TransferirDTO) {
    this.contaService.transferir(conta).subscribe(
      response => {
        
        SweetalertCustom.showAlertTimer('Transação realizada com sucesso.', {type: 'success'}).then(
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
