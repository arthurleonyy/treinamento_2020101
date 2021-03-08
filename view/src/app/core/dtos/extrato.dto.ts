export class ExtratoDTO {
    id?: number;
    tipo?: string;
    descricao?: string;
    data?: string;
    valor?: number;

    constructor(obj?) {
        if (obj) {
            this.id = obj.id;
            this.tipo = obj.tipo;
            this.descricao = obj.descricao;
            this.data = obj.data;
            this.valor = obj.valor;
        }
    }
}
