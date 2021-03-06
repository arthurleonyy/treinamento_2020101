import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { ContaService } from 'src/app/core/services/conta.service';
import { SweetalertCustom } from 'src/app/shared/utils/sweetalert-custom';
import Swal from 'sweetalert2';



@Component({
  selector: 'app-consulta-de-saldo',
  templateUrl: './consulta-de-saldo.component.html',
  styleUrls: ['./consulta-de-saldo.component.scss']
})
export class ConsultaSaldoComponent extends FormBase implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private contaService: ContaService,
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
    if(this.form.valid){
      this.contaService.consultarSaldo(this.form.get('agencia').value, this.form.get('numeroConta').value).subscribe(
        (saldo) => {
          Swal.fire({
            title: 'Saldo atual: R$ ' + saldo.body,
            showClass: {
              popup: 'animate__animated animate__fadeInDown'
            },
            hideClass: {
              popup: 'animate__animated animate__fadeOutUp'
            }
          })
        }
      )
    }
  }

}