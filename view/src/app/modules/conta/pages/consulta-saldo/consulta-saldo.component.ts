import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { ContaService } from 'src/app/core/services/conta.service';
import { SweetalertCustom } from 'src/app/shared/utils/sweetalert-custom';

@Component({
  selector: 'app-consulta-saldo',
  templateUrl: './consulta-saldo.component.html',
  styleUrls: ['./consulta-saldo.component.scss']
})
export class ConsultaSaldoComponent extends FormBase implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private contaService: ContaService,
    private router: Router
  ) { 
    super()
  }

  ngOnInit(): void {
    this.createFormGroup();
    this.validateMessageError();
  }

  createFormGroup(){
    this.form = this.formBuilder.group({
      agencia: ['', Validators.required],
      numeroConta: ['', Validators.required]
    });
  }

  validateMessageError(){
    this.createValidateFieldMessage({
      agencia: {
        required: "Agência é obrigatóra"
      },
      numeroConta: {
        required: "Número da Conta é obrigatório"
      }
    })
  }

  onSubmit(){
    let agencia = this.form.get('agencia').value;
    let numero = this.form.get('numeroConta').value;
    if(this.form.valid){
      this.contaService.consultarSaldo(agencia, numero).subscribe(
        saldo => {
          SweetalertCustom.showAlertConfirm(
            "O seu saldo atual é: R$" + saldo['body'], {type: 'info'}
          ).then(
            voltar => {
              this.router.navigate(["/conta/operacoes"])
            }
          )
        }
      )
    }
  }

}
