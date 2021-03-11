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
    public router: Router
  ) {
    super()
  }

  ngOnInit() {
    this.createFormGroup();
    this.validateMessageError();
  }

  createFormGroup() {
    this.form = this.formBuilder.group({
      cpf: ['', [Validators.required, ValidatorsCustom.validateCpf]],
    });
  }

  validateMessageError() {
    this.createValidateFieldMessage({
      cpf: {
        required: 'CPF é obrigatório.',
        validateCpf: 'CPF inválido.'
      },
    });
  }

  onSubmit() {
    if (this.form.valid) {
      const cpf = this.form.get('cpf').value;
      this.contaService.getContas(cpf).subscribe(
        (response) => {
          this.listaContas = response.body.map(item => {
            return new ContaListDTO({
              agencia: item.agencia,
              numeroConta: item.numero,
              cpf: item.cliente.cpf,
              cliente: item.cliente.nome,
              email: item.cliente.email
            })
          });
        },
        (error) => {
          // Linha abaixo feita pois o back end está retornando 500 quando não há nenhum registro encontrado
          this.listaContas = new Array<ContaListDTO>();
        }
      );
    }
  }

}
