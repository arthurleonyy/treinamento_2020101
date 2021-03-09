import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { ContaListDTO } from 'src/app/core/dtos/conta-list.dto';
import { ContaService } from 'src/app/core/services/conta.service';
import { ValidatorsCustom } from 'src/app/shared/utils/validators-custom';

@Component({
  selector: 'app-consultar-contas',
  templateUrl: './consultar-contas.component.html',
  styleUrls: ['./consultar-contas.component.scss']
})
export class ConsultarContasComponent extends FormBase implements OnInit {

  listaContas = new Array<ContaListDTO>();

  constructor(
    private formBuilder: FormBuilder,
    private contaService: ContaService,
    private router: Router
  ) { 
    super();
  }

  ngOnInit(): void {
    this.createFormGroup(),
    this.validateMessageError()
  }

  createFormGroup(){
    this.form = this.formBuilder.group({
      cpf: ['', [Validators.required, ValidatorsCustom.validCpf]]
    });
  }

  validateMessageError(){
    this.createValidateFieldMessage({
      cpf: {
        required: "CPF obrigatório",
        validCpf: "Digite um CPF válido"
      }
    });
  }

  onSubmit(){
    if(this.form.valid){
      const cpf = this.form.get('cpf').value;
      this.contaService.consultarContas(cpf).subscribe(
        (response) => {
          this.listaContas = response.body.map(
            item => {
              return new ContaListDTO({
                agencia: item.agencia,
                numeroConta: item.numero,
                cpf: item.cliente.cpf,
                cliente: item.cliente.nome,
                email: item.cliente.email
              })
            }
          );
        },
        (error) => {
          this.listaContas = new Array<ContaListDTO>();
        }
      )

    }
  }

}
