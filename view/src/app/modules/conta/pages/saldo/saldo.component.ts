import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { SaldoDTO } from 'src/app/core/dtos/saldo.dto';
import { ContaService } from 'src/app/core/services/conta.service';

@Component({
  selector: 'app-saldo',
  templateUrl: './saldo.component.html',
  styleUrls: ['./saldo.component.scss']
})
export class SaldoComponent extends FormBase implements OnInit {

  saldodaConta = '';

  constructor(
    private formBuilder: FormBuilder,
    private contaService: ContaService,
    public router: Router,
    )
  {
    super();
  }

  ngOnInit(): void {
    this.validateMensageError();
    this.createFormGroup();
  }

  createFormGroup() {
    this.form = this.formBuilder.group({
      agencia:      ['', [Validators.required]],
      numeroConta:  ['', [Validators.required]],
      saldo:        [0],
    });
  }

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
      let saldo = new SaldoDTO(this.form.value);
      this.contaService.consultaSaldo(saldo).subscribe(
        response => {
          this.saldodaConta = parseFloat(response.body).toFixed(2) + ' R$';
        }
      );
    }
  }


}
