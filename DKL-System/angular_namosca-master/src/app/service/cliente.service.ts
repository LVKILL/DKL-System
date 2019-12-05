import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(
    private http : HttpClient
  ) { }
  /**
   * Retorna clientes
   */
  public getClients () {
    return this.http.get<any>('http://127.0.0.1:9090/api/clientes?busca=&pagina=0&tamanho=20');
  };

}
