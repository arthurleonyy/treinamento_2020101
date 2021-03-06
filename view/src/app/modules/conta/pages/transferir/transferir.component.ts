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
              public router: Router
              ) {
    super();
  }

  ngOnInit(): void {
    this.criaGrupo()
    this.validateMensageError() 
  }

  validateMensageError() {
    this.createValidateFieldMessage({
      agenciaOrigem: {
        required: 'Agência obrigatória.',
        sameAccount: "Você não pode transferir para uma mesma conta."
      },
      numeroContaOrigem: {
        required: 'Número da conta obrigatório.',
        sameAccount: "Você não pode transferir para uma mesma conta."
      },
      agenciaDestino: {
        required: 'Agência obrigatória.',
        sameAccount: "Você não pode transferir para uma mesma conta."
      },
      numeroContaDestino: {
        required: 'Número da conta obrigatório.',
        sameAccount: "Você não pode transferir para uma mesma conta."
      },
      valor: {
        required: 'Valor obrigatório.',
        lessThanOne: 'Valor informado deve ser maior que zero.'
      }
    });
  }

  criaGrupo() {
    this.form = this.formBuilder.group({
      agenciaOrigem:      ['', [Validators.required]],
      numeroContaOrigem:  ['', [Validators.required]],
      agenciaDestino:      ['', [Validators.required]],
      numeroContaDestino:  ['', [Validators.required]],
      valor:        [0, [Validators.required, ValidatorsCustom.lessThanOne]],
    }, {
      validators: [ValidatorsCustom.SameAccount]
    });
  }


  
  onSubmit() {
    if(this.form.valid) {
      this.transferir(new TransferenciaDTO(this.form.value))
    }
    
  }

  private transferir(transferenciaInfo: TransferenciaDTO) {
    this.contaService.transferir(transferenciaInfo).subscribe(response => { SweetalertCustom.showAlertTimer('Operação realizada com sucesso.', {type: 'success'}).then(
      result => {
        if (result.dismiss) {
          this.router.navigate(['conta/operacoes']);
        }
      })})

  }
}
