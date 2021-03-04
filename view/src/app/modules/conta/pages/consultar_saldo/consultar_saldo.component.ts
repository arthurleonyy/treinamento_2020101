import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { ConsultarSaldoDTO } from 'src/app/core/dtos/consultar_saldo';
import { ContaService } from 'src/app/core/services/conta.service';
import { SweetalertCustom } from 'src/app/shared/utils/sweetalert-custom';

@Component({
  selector: 'app-consultar-saldo',
  templateUrl: './consultar_saldo.component.html',
  styleUrls: ['./consultar_saldo.component.scss']
})

export class ConsultarSaldoComponent extends FormBase implements OnInit{

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
      let conta = new ConsultarSaldoDTO(this.form.value);
      console.log("lets go");
        this.consultar(conta);      
    }
  }

  private consultar(conta: ConsultarSaldoDTO) {
    this.contaService.consultarSaldo(conta).subscribe(response => {
      var resp = response;
      console.log(resp)
        
        SweetalertCustom.showAlertTimer('Consulta realizada com sucesso.', {type: 'success'}).then(
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
