import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormBase } from 'src/app/core/classes/form-base';
import { ContaDTO } from 'src/app/core/dtos/conta.dto';
import { ContaService } from 'src/app/core/services/conta.service';
import { SweetalertCustom } from 'src/app/shared/utils/sweetalert-custom';
import { ValidatorsCustom } from 'src/app/shared/utils/validators-custom';

@Component({
  selector: 'app-deposito-saque',
  templateUrl: './deposito-saque.component.html',
  styleUrls: ['./deposito-saque.component.scss']
})
export class DepositoSaqueComponent extends FormBase implements OnInit {

  nameScreen = ""

  constructor(
    private formBuilder: FormBuilder,
    private contaService: ContaService,
    private router: Router
  ) {
    super();
  }

  ngOnInit(): void {
    this.createFormGroup();
    this.validateMessageError();
    this.getScreen()
  }

  private getScreen(){
    if(this.router.url.includes("depositar")){
      this.nameScreen = "Depositar"
    } else if(this.router.url.includes("sacar")){
      this.nameScreen = "Sacar"
    }
  }

  createFormGroup(){
    this.form = this.formBuilder.group({
      agencia: ['', [Validators.required]],
      numeroConta: ['', [Validators.required]],
      valor: [0, [Validators.required, ValidatorsCustom.lessThanOne]]
    })
  }

  validateMessageError(){
    this.createValidateFieldMessage({
      agencia:{
        required: "Informe a agência"
      },
      numeroConta:{
        required: "Informe o número da conta"
      },
      valor:{
        required: "O campo de valor é obrigatório",
        lessThanOne: "O valor necessita ser maior que R$ 0"
      }
      
    });
  }

  onSubmit(){
    if(this.form.valid){
      let conta = new ContaDTO(this.form.value);

      if(this.router.url.includes("depositar")){
        this.depositar(conta);
      } else if(this.router.url.includes("sacar")){
        this.sacar(conta);
      }
    }
  }

  private depositar(conta: ContaDTO){
  
      this.contaService.depositar(conta).subscribe(
        response => {
          SweetalertCustom.showAlertTimer("Operação realizada com sucesso", {type: "success"}).then(
            result => {
              this.router.navigate(["conta/operacoes"])
            }
          )
          console.log(response);
        },
        
      );
  }
  
  private sacar(conta: ContaDTO){
      this.contaService.sacar(this.form.value).subscribe(
        response => {
          SweetalertCustom.showAlertTimer("Operação realizada com sucesso", {type: "success"}).then(
            result => {
              this.router.navigate(["conta/operacoes"])
            }
          )
          console.log(response);
        },
        
      );
  }

}
