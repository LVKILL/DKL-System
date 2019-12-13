import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReservaService {

  constructor(
    private http: HttpClient
  ) { }

  /**
   * salva reserva
   */
  public salvaReserva(id = null, clienteSelecionado, ambienteSelecionado, inicioDaLocacao, fimDaLocacao, armasLocadas, ativo = "true") {
    /**
     * {
     *   "cliente":{"id":"128"},
     *   "ambiente":{"id":"1002"},
     *   "inicioDaLocacao":"2019-12-12T12:12",
     *   "fimDaLocacao":"2019-12-12T12:13",
     *   "ativa":true,
     *   "armaLocadas":[
     *      {"arma":{"id":1003},"quantidade":1}
     *    ]
     * }
     * 
     * { 
     *   "cliente": {"id":"1001"},
     *   "ambiente":{"id":"1002"},
     *   "inicioDaLocacao":"1212-12-12T12:12",
     *   "fimDaLocacao":"1212-12-12T12:12",
     *   "ativa":true,
     *   "armasLocadas": [
     *      {"id":1001,"quantidade":1}
     *    ]}
     */
    let obj = {
        "cliente": {"id" : clienteSelecionado},
        "ambiente": {"id" : ambienteSelecionado},
        "inicioDaLocacao" : inicioDaLocacao,
        "fimDaLocacao" : fimDaLocacao,
        "ativa" : true,
        "armaLocadas" : []
    }

    for (let i = 0; i< armasLocadas.length; i++){
      obj.armaLocadas.push({"arma" : armasLocadas[i]});
    }

    console.log(obj);

    if(id === null){
      return this.http.post<any>('http://127.0.0.1:9090/api/reservas', obj , {});
    }else {
      return this.http.put<any>('http://127.0.0.1:9090/api/reservas/' + id, obj, {});
    }
    

  }
  /**
   * listar reservas
   */
  public listarReserva() {
    return this.http.get<any>('http://127.0.0.1:9090/api/reservas?busca=&pagina=0&tamanho=20');
  };

  /**
   * pega reserva pelo id
   * http://127.0.0.1:9090/api/reservas/109
   */
  public listaReservaPorId(id){
    return this.http.get<any>('http://127.0.0.1:9090/api/reservas/' + id);
  }
}


