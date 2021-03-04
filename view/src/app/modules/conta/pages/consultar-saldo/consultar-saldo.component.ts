import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { ConsultarDTO } from 'src/app/core/dtos/consultar.dto';
import { ContaService } from 'src/app/core/services/conta.service';
import { SweetalertCustom } from 'src/app/shared/utils/sweetalert-custom';

@Component({
  selector: 'app-consultar-saldo',
  templateUrl: './consultar-saldo.component.html',
  styleUrls: ['./consultar-saldo.component.scss']
})
export class ConsultarSaldoComponent extends FormBase implements OnInit {

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
      let conta = new ConsultarDTO(this.form.value);
      console.log("babau");
        this.consultar(conta);      
    }
  }

  private consultar(conta: ConsultarDTO) {
    
    this.contaService.consultar(conta).subscribe(response => {

      var resp = response;
      console.log(resp)
        
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
