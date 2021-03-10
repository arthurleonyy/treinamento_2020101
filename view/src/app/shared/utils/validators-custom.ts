import { group } from '@angular/animations';
import { AbstractControl, ValidatorFn, FormGroup } from '@angular/forms';
import * as moment from 'moment';
import 'moment/locale/pt-br';
import { isNullOrUndefined } from 'util';

export class ValidatorsCustom {

    static noWhitespaceValidator(control: AbstractControl) {
        let field = control.value;
        if (!field) { return { required: true }; }
        field = field.toString().trim();
        if (!field) { return { required: true }; }
        return null;
    }

    /**
     * Verifica se o CPF é valido
     * @param control Campo a ser validado
     */
    static validCpf(control: AbstractControl) {

        let cpf = control.value;
        if (!cpf) { return null; }
        cpf = cpf.toString().trim();
        if (!cpf) { return null; }

        let soma = 0;
        let resto;

        if (
            cpf === '00000000000' ||
            cpf === '11111111111' ||
            cpf === '22222222222' ||
            cpf === '33333333333' ||
            cpf === '44444444444' ||
            cpf === '55555555555' ||
            cpf === '66666666666' ||
            cpf === '77777777777' ||
            cpf === '88888888888' ||
            cpf === '99999999999') {
            return { cpfInvalid: true };
        }

        for (let i = 1; i <= 9; i++) {
            soma = soma + Number(cpf.substring(i - 1, i)) * (11 - i);
        }

        resto = (soma * 10) % 11;

        if ((resto === 10) || (resto === 11)) {
            resto = 0;
        }

        if (resto !== Number(cpf.substring(9, 10))) {
            return { cpfInvalid: true };
        }

        soma = 0;

        for (let i = 1; i <= 10; i++) {
            soma = soma + Number(cpf.substring(i - 1, i)) * (12 - i);
        }
        resto = (soma * 10) % 11;

        if ((resto === 10) || (resto === 11)) {
            resto = 0;
        }

        if (resto !== Number(cpf.substring(10, 11))) {
            return { cpfInvalid: true };
        }

        return null;
    }

    /**
     * Valida se o campo tem o númeto maximo informado
     * @param length Tamanho a ser utilizado na validação
     */
    static maxLength(length: number): ValidatorFn {

        return (control: AbstractControl): { [key: string]: boolean } | null => {
            const valueControl: string = control.value.trim();

            if (valueControl && valueControl.length > length) { return { maxLength: true }; }

            return null;
        };
    }

    /**
     * Valida se o campo tem o númeto minimo informado
     * @param length Tamanho a ser utilizado na validação
     */
    static minLength(length: number): ValidatorFn {
        return (control: AbstractControl): { [key: string]: boolean } | null => {
            if (!isNullOrUndefined(control.value) && !isNaN(control.value)) {
                const valueControl = control.value.toString().trim();
                if (valueControl && valueControl.length < length) {
                    return { minLength: true };
                }
            }
            return null;
        };
    }

    /**
     * Verifica se o e-mail é valido
     * @param control Campo a ser validado
     */
    static validEmail(control: AbstractControl) {
        const email = control.value.trim();

        // tslint:disable-next-line: max-line-length
        const regexP = /[a-zA-Z0-9!#$%&'*+=?^_`{|}~-]+(?:\.[a-zA-Z0-9!#$%&'*+=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?/g;

        if (email && !regexP.test(email)) {
            return { emailInvalid: true };
        }
        return null;
    }

    /**
     * Verifica se o e-mail é valido
     * @param control Campo a ser validado
     */
    static validatePasswordValid(control: AbstractControl) {
        const password = control.value.trim();
        const regexP = /^(?=\D*\d)(?=[^a-zA-Z]*[a-zA-Z]).{6,30}$/g;

        if (password && !regexP.test(password)) {
            return { passwordInvalid: true };
        }
        return null;
    }

    /**
     * Verifica se o array está vazio
     * @param control Campo a ser validado
     */
    static arrayLength(control: AbstractControl) {
        const array: any[] = control.value;
        if (array && array.length <= 0) { return { arrayEmpty: true }; }

        return null;
    }

    /**
     * Valida se a senha é igual da de confirmar senha
     * @param form Campo a ser validado
     */
    static validatePassword(form: FormGroup) {
        const novaSenha = form.get('novaSenha').value;
        const novaSenhaRepeat = form.get('novaSenhaRepeat').value;

        if (!novaSenhaRepeat || !novaSenha) {
            return null;
        }

        if (novaSenha.trim() !== novaSenhaRepeat.trim()) {
            form.get('novaSenha').setErrors({ passwordNotMatch: true });
            form.get('novaSenhaRepeat').setErrors({ passwordNotMatch: true });
            return null;
        }

        form.get('novaSenha').setErrors({ passwordNotMatch: null });
        form.get('novaSenhaRepeat').setErrors({ passwordNotMatch: null });
        form.get('novaSenha').updateValueAndValidity({ onlySelf: true });
        form.get('novaSenhaRepeat').updateValueAndValidity({ onlySelf: true });

        return null;
    }

    /**
     * Verifica se a data é inválida
     * @param control Campo a ser validado
     */
    static validDate(control: AbstractControl) {
        if (control.value) {
            if (!moment(control.value).isValid()) {
                return { validDate: true };
            }
        }

        return null;
    }

    /**
     * Verifica se é a data minina
     * @param control Campo a ser validado
     */
    static minDate(control: AbstractControl) {
        if (control.value) {
            const date = new Date(control.value);
            if (moment(date).isValid() && date.getFullYear() < (new Date(0).getFullYear() - 69)) {
                return { minDate: true };
            }
        }

        return null;
    }

    /**
     * Verifica se a data é superior a data atual
     * @param control Campo a ser validado
     */
    static higherDate(control: AbstractControl) {
        const currentDate = new Date();
        const date = new Date(control.value);

        if (moment(date).isValid() && currentDate < date) {
            return { higherDate: true };
        }

        return null;
    }

    /**
     * Valida o número do telefone/celular
     * @param control Campo a ser validado
     */
    static validPhone(control: AbstractControl) {
        const phone: string = control.value.trim();
        if (phone && (phone.length < 10 || phone.length > 11)) {
            return { phoneInvalid: true };
        }

        return null;
    }

    static lessThanOne(control: AbstractControl) {
        const value = Number(control.value);
        if (value < 1) {
            return { lessThanOne: true };
        } else {
            return null;
        }
    }

    static camposIguais(control1: string, control2: string) {
        return (group: AbstractControl): { [key: string] : boolean} | null => {
            const campo1 = group.get('numeroContaOrigem').value;
            const campo2 = group.get('numeroContaDestino').value;

            if(campo1 && campo2){
                if(campo1 === campo2){
                    group.get(control1).setErrors({ camposIguais: true });
                    group.get(control2).setErrors({ camposIguais: true });
                } else{
                    if(group.get(control1).errors && group.get(control1).errors.camposIguais){
                        delete group.get(control1).errors.camposIguais;
                        group.get(control1).updateValueAndValidity();
                        group.get(control2).updateValueAndValidity();
                    }
                    if(group.get(control2).errors && group.get(control2).errors.camposIguais){
                        delete group.get(control2).errors.camposIguais;
                        group.get(control1).updateValueAndValidity();
                        group.get(control2).updateValueAndValidity();
                    }
                }
            }
            return null;
        }
    }

    static validarData(control1: string, control2: string){
        return(group: AbstractControl): { [key: string] : boolean} | null => {
            const data1 = group.get('dataInicio').value;
            const data2 = group.get('dataFim').value;

            const partes = data1.split("/");
            const partes2 = data2.split("/");

            if(data1 && data2){
                var dataInicio: Date = new Date(partes[2], partes[1] - 1, partes[0]);
                var dataFim: Date = new Date(partes2[2], partes2[1] - 1, partes2[0]);
                
                if(dataInicio > dataFim){
                    group.get(control1).setErrors({ invalidStartDate: true });
                } else {
                    if(group.get(control1).errors && group.get(control1).errors.invalidStartDate){
                        delete group.get(control1).errors.invalidStartDate;
                        group.get(control1).updateValueAndValidity();
                    }
                }
            }


            /**
             * Validação de data geral
             */
            if((data1.length != 10) && (data1.length > 0)){
                group.get(control1).setErrors({ invalidDate: true });
            } else {
                if(group.get(control1).errors && group.get(control1).errors.invalidDate){
                    delete group.get(control1).errors.invalidDate;
                    group.get(control1).updateValueAndValidity();
                }
            }
            if((data2.length != 10) && (data2.length > 0)){
                group.get(control2).setErrors({ invalidDate: true });
            } else {
                if(group.get(control2).errors && group.get(control2).errors.invalidDate){
                    delete group.get(control2).errors.invalidDate;
                    group.get(control2).updateValueAndValidity();
                }
            }

            /**
             * Validação de data individual (dia, mês e ano)
             */
            // Validação do Dia
            if((partes[0] < 1) && (partes[0] > 31) && (data1.length > 1)){
                group.get(control1).setErrors({ invalidDay: true });
            } else{
                if(group.get(control1).errors && group.get(control1).errors.invalidDay){
                    delete group.get(control1).errors.invalidDay;
                    group.get(control1).updateValueAndValidity();
                }
            }

            if((partes2[0] < 1) && (partes2[0] > 31) && (data2.length > 1)){
                group.get(control2).setErrors({ invalidDay: true });
            } else{
                if(group.get(control2).errors && group.get(control2).errors.invalidDay){
                    delete group.get(control2).errors.invalidDay;
                    group.get(control2).updateValueAndValidity();
                }
            }

            // Validação do Mês
            if((partes[1] < 1) || (partes[1] > 12)){
                group.get(control1).setErrors({ invalidMonth: true });
            } else{
                if(group.get(control1).errors && group.get(control1).errors.invalidMonth){
                    delete group.get(control1).errors.invalidMonth;
                    group.get(control1).updateValueAndValidity();
                }
            }

            if((partes2[1] < 1) || (partes2[1] > 12)){
                group.get(control2).setErrors({ invalidMonth: true });
            } else{
                if(group.get(control2).errors && group.get(control2).errors.invalidMonth){
                    delete group.get(control2).errors.invalidMonth;
                    group.get(control2).updateValueAndValidity();
                }
            }
            
            // Validação de Ano
            if((data1.length > 4 && data1.length < 10) || (partes[2] < 1900)){
                group.get(control1).setErrors({ invalidYear: true });
            } else{
                if(group.get(control1).errors && group.get(control1).errors.invalidYear){
                    delete group.get(control1).errors.invalidYear;
                    group.get(control1).updateValueAndValidity();
                }
            }

            if((data2.length > 4 && data2.length < 10) || (partes2[2] < 1900)){
                group.get(control2).setErrors({ invalidYear: true });
            } else{
                if(group.get(control2).errors && group.get(control2).errors.invalidYear){
                    delete group.get(control2).errors.invalidYear;
                    group.get(control2).updateValueAndValidity();
                }
            }

            return null;
        }
    }
}
