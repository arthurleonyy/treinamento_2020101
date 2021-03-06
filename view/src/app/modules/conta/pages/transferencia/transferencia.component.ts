import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { TransferenciaDTO } from 'src/app/core/dtos/transferencia.dto';
import { ContaService } from 'src/app/core/services/conta.service';
import { SweetalertCustom } from 'src/app/shared/utils/sweetalert-custom';
import { ValidatorsCustom } from 'src/app/shared/utils/validators-custom';

@Component({
  selector: 'app-transferencia',
  templateUrl: './transferencia.component.html',
  styleUrls: ['./transferencia.component.scss']
})
export class TransferenciaComponent extends FormBase implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private contaService: ContaService,
    private router: Router,
  ) { 
    super();
  }

  ngOnInit(): void {
    this.createFormGroup(),
    this.validateMessageError()
  }

  createFormGroup(){
    this.form = this.formBuilder.group({
      agenciaOrigem: ['', [Validators.required]],
      numeroContaOrigem: ['', [Validators.required]],
      agenciaDestino: ['', [Validators.required]],
      numeroContaDestino: ['', [Validators.required]],
      valor: [0, [Validators.required, ValidatorsCustom.lessThanOne]],
    })
  }

  validateMessageError(){
    this.createValidateFieldMessage({
      agenciaOrigem:{
        required: "Agência obrigatória"
      },
      numeroContaOrigem:{
        required: "Conta obrigatória"
      },
      agenciaDestino:{
        required: "Agência obrigatória"
      },
      numeroContaDestino:{
        required: "Conta obrigatória"
      },
      valor:{
        required: "Valor obrigatório",
        lessThanOne: "O valor precisa ser maior que R$ 0"
      },
      
    });
  }

  transferir(){
    console.log(this.form.value);
    if(this.form.valid){
      let conta = new TransferenciaDTO(this.form.value);
      this.contaService.transferir(this.form.value).subscribe(
        response => {
          SweetalertCustom.showAlertTimer("Transferência realizada com sucesso <br>", {type: "success"}, ).then(
            result => {
              this.router.navigate(["/conta/operacoes"])
            }
          )
        }
      );
    }
  }

}