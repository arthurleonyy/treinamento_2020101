import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { ExtratoDTO } from 'src/app/core/dtos/extrato.dto';
import { ContaService } from 'src/app/core/services/conta.service';
import { SweetalertCustom } from 'src/app/shared/utils/sweetalert-custom';

@Component({
  selector: 'app-extrato',
  templateUrl: './extrato.component.html',
  styleUrls: ['./extrato.component.scss']
})

export class ExtratoComponent extends FormBase implements OnInit {

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
      
    });
  }

  /**
   * Seta a mensagem de validação que irá ser exibida ao usuário
   */
  validateMensageError() {
    this.createValidateFieldMessage({
      agencia: {
        required: 'Agência obrigatória.'
      },
      numeroConta: {
        required: 'Número da conta obrigatório.'
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
    
    this.contaService.extrato(conta).subscribe(response => {

      var ext = JSON.stringify(response);
      

      SweetalertCustom.showAlertConfirm('Operação realizada com sucesso.', {type: 'sucess'}, "ok", `Extrato: \n ${ext}`).then(
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
