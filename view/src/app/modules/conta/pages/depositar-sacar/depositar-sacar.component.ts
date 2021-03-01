import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { FormBase } from 'src/app/core/classes/form-base';

@Component({
  selector: 'app-depositar-sacar',
  templateUrl: './depositar-sacar.component.html',
  styleUrls: ['./depositar-sacar.component.scss']
})
export class DepositarSacarComponent extends FormBase implements OnInit {

  constructor(private formBuilder: FormBuilder)
  {
    super();
  }

  ngOnInit(): void {
    this.validateMensageError();
    this.createFormGroup();
  }

  createFormGroup(){
    this.form = this.formBuilder.group({
      agencia: [''],
      numeroConta: [''],
      valor: [0]
    });
  }

  validateMensageError(){
    this.createValidateFieldMessage({});
  }

}
