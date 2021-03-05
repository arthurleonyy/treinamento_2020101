import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { ContaDTO } from 'src/app/core/dtos/conta.dto';
import { ContaService } from 'src/app/core/services/conta.service';
import { SweetalertCustom } from 'src/app/shared/utils/sweetalert-custom';


@Component({
  selector: 'app-consulta-saldo',
  templateUrl: './consulta-saldo.component.html',
  styleUrls: ['./consulta-saldo.component.scss']
})
export class ConsultaSaldoComponent extends FormBase implements OnInit {

  conta: ContaDTO;
  valido;

  constructor(
    private formBuilder: FormBuilder,
    private contaService: ContaService,
    public router: Router
  ) {
    super();
  }

  ngOnInit() {
    this.createFormGroup();
    this.validateMensageError();
  }

  createFormGroup() {
    this.form = this.formBuilder.group({
      agencia:      ['', Validators.required],
      numeroConta:  ['', Validators.required],
    });
  }

  
  validateMensageError() {
    this.createValidateFieldMessage({
      agencia: {
        required: 'Agência obrigatória.',
      },
      numeroConta: {
        required: 'Número da conta obrigatório.',
      },
    });
  }

  onSubmit() {
    if (this.form.valid) {
      this.valido = false;
      let conta = new ContaDTO(this.form.value);
      
      this.consultarSaldo(conta);   
     
     }
  }

  private consultarSaldo(conta: ContaDTO) {
    this.contaService.consultarSaldo(conta).subscribe(
      response => {
        
          SweetalertCustom.showAlertTimer('Operação realizada com sucesso.', {type: 'success'}).then(
          result => {
            document.getElementById('buttonModal').click();
            this.valido = true;
            this.conta = new ContaDTO({
              agencia: conta.agencia,
              numeroConta: conta.numeroConta,
              valor: response.body
            });
          }
        );
      }
    );
  }

}
