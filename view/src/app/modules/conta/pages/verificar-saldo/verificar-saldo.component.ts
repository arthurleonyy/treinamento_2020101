import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { ContaService } from 'src/app/core/services/conta.service';

@Component({
  selector: 'app-verificar-saldo',
  templateUrl: './verificar-saldo.component.html',
  styleUrls: ['./verificar-saldo.component.scss']
})
export class VerificarSaldoComponent extends FormBase implements OnInit {

  thereIsSaldoInfo = false
  saldoValue = 0

  constructor(
              private formBuilder: FormBuilder,
              private contaService: ContaService,
              public router: Router
  ) {
    super();
  }

  ngOnInit(): void {
    this.createFormGroup();
    this.validateMensageError();
  }

  createFormGroup() {
    this.form = this.formBuilder.group({
                  agencia: ['', [Validators.required]],
                  conta:   ['', [Validators.required]],
                })
  }

  validateMensageError() {
    this.createValidateFieldMessage({
      agencia: {
        required: 'Agência obrigatória.'
      },
      conta: {
        required: 'Número da conta obrigatório.'
      }
    });
  }

  onSubmit() {
    this.saldo(this.form.value.agencia,this.form.value.conta);
  }
  saldo(agencia:string, conta:string) {
    this.contaService.saldo(agencia,conta).subscribe(response => {
      this.saldoValue = response.body;
      this.thereIsSaldoInfo = true;
      console.log(this.saldoValue)
    })
  }

}
