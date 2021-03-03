import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { TransferenciaDTO } from 'src/app/core/dtos/transferencia.dto';
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
    public router: Router,
  ) {
    super();
   }

  ngOnInit(): void {
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
        required: 'É obrigatório o número da agência de quem vai realizar a transfêrencia.'
      },
      agenciaDestino: {
        required: 'É obrigatório o número da agência de quem vai receber a transfêrencia.'
      },
      numeroContaOrigem: {
        required: 'É obrigatório o número da conta de quem vai realizar a transfêrencia.'
      },
      numeroContaDestino: {
        required: 'É obrigatório o número da conta de quem vai receber a transfêrencia.'
      },
      valor: {
        required: 'Valor obrigatório.',
        lessThanOne: 'Valor informado deve ser maior que zero.'
      }
    });
  }

  onSubmit() {
    if (this.form.valid) {
      let transferenciaEntreContas = new TransferenciaDTO(this.form.value);
      this.contaService.transferir(transferenciaEntreContas).subscribe(
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


}
