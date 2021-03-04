import { ContaDTO } from 'src/app/core/dtos/conta.dto';
import { FormBuilder, Validators } from '@angular/forms';
import { FormBase } from './../../../../core/classes/form-base';
import { Component, OnInit } from '@angular/core';
import { ContaService } from 'src/app/core/services/conta.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-consultar-saldo',
  templateUrl: './consultar-saldo.component.html',
  styleUrls: ['./consultar-saldo.component.scss']
})
export class ConsultarSaldoComponent extends FormBase implements OnInit {
  conta: ContaDTO;

  constructor( 
    private formBuilder: FormBuilder,
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
    this.form = this.formBuilder.group({
      agencia: ['', [Validators.required]],
      numeroConta: ['', [Validators.required]]
    });

  }
  
  validateMensageError() {
    this.createValidateFieldMessage({
      agencia: {
        required: 'Agência obrigatória.',
      },
      numeroConta: {
        required: 'Número da conta obrigatório.'
      },
    });
  }

  onSubmit() {
    if (this.form.valid) {
      let conta = new ContaDTO(this.form.value);
      this.contaService.saldo(conta).subscribe(
        (response) => {
          this.conta = new ContaDTO({
            agencia: conta.agencia,
            numeroConta: conta.numeroConta,
            valor: response.body

          });
        }
      );

    }

  }

}
